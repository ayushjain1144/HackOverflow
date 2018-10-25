package com.t.quakealert;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

//you can change categories in settings toast
public class PlayGame extends Activity {
    public static boolean gameOverByTimer;
    static int currentQuestionInFiftyFifty;
    //@param QuestionCounter is 0 indexed
    static int QuestionCounter, countDoubleTip = 1;
    static boolean isLifeLineDoubleTipUsed, isLifeLineDoubleTip, isLifeLineFiftyFifty, isLifeLineFlipTheQuestion,
            isLifeLineExpertAdvice, isLifeLineFiftyFiftyCurrent, GameOverStatus, GameContinued = true, maxLimit, GameOver;
    static String QUESTION = "", OPTION_A = "", OPTION_B = "", OPTION_C = "", OPTION_D = "", RIGHT_ANSWER = "", right_answer = "";
    public int soundIdStartInQuestion, soundIdShortApplause, soundIdLockSound, soundIdTicTock, soundIdDangerTicTock;
    String[] ScoresArray;
    Animation A;
    String PlayerName;
    PopupWindow popupWindow, popupWindowConfirm;

    DatabaseHelper mydb = new DatabaseHelper(this);

    TextView tv, tv1, tv2, tv3, tv4, tv_playername, tv_money, tv_msg_inplayGame, tv_lifeLine;
    ImageView img, img1, img2, img3, img4, crs1, crs2, crs3, crs4, llimg1, llimg2, llimg3, llimg4;
    SoundPool mSoundPool;
    MediaPlayer mediaPlayer;
    private int currentQuestionInDoubleTip;
    private int max_easy;
    private int easyQuestionCounter;
    private ArrayList<String> selectedCategories;
    private int[] selectedCategoriesNumber;
    private int[] selectedCategoriesSizes;
    private int categoryPosition;
    private int[] categoryPositionCounter;
    private CountDownTimer timer30Seconds;
    private boolean wasPressedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Next three lines for Full Screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttrib)
                    .build();
        } else {
            mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        // When Sound Pool load complete.
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("output", "sounds loaded for sound pool");
            }
        });
        soundIdStartInQuestion = mSoundPool.load(this, R.raw.start_in_question, 1);
        soundIdLockSound = mSoundPool.load(this, R.raw.option_lock_sound, 1);
        soundIdShortApplause = mSoundPool.load(this, R.raw.short_applause, 1);
        soundIdTicTock = mSoundPool.load(this, R.raw.tic_tock, 1);
        soundIdDangerTicTock = mSoundPool.load(this, R.raw.danger_tic_tock, 1);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //set volume key control
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        selectedCategoriesNumber = Util.convertStringArrayToInts(
                getSharedPreferences("myPrefs", MODE_PRIVATE).getString("selectedTopics", "[0]"));
        max_easy = mydb.getQuestionsCount("Easy");
        if (!isDatabasePresent() || max_easy == 0) {
            Util.loadQuestions(mydb, selectedCategoriesNumber);
        }
        int i = 0;
        Log.d("PG", "Easy is " + max_easy);
        selectedCategoriesSizes = new int[selectedCategoriesNumber.length];
        selectedCategories = new ArrayList<>();
        for (int topicNumber : selectedCategoriesNumber)
            selectedCategories.add(Util.topics[topicNumber]);
        for (String s : selectedCategories)
            selectedCategoriesSizes[i++] = mydb.getQuestionsCount(s);

        if (output.NewGame) {
            Toast.makeText(this, "NEW GAME LOADED...", Toast.LENGTH_SHORT).show();
            setDefaultSharedPreferences();//For Default
        } else {
            Toast.makeText(this, "SAVED GAME LOADED...", Toast.LENGTH_SHORT).show();
            setSharedPreferences();//for Continue
        }
        initiateObjects();
        timer30Seconds = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 2000)
                    mSoundPool.play(soundIdTicTock, 1f, 1f, 1, 0, 1f);
                else mSoundPool.play(soundIdDangerTicTock, 1f, 1f, 1, 0, 1f);
                Log.d("PG", "tic tock " + millisUntilFinished);
                tv_lifeLine.setText(String.format(Locale.ENGLISH, "%d", millisUntilFinished / 1000));
                A = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_button_play_big);
                tv_lifeLine.startAnimation(A);
            }

            public void onFinish() {
                gameOverByTimer = true;
                showConfirmDialogWrongAnswer();
            }
        };
        Log.d("PG", "played song");
        displayQuestions();
    }

    protected void onResume() {
        super.onResume();
        if (mediaPlayer == null)
            Log.d("PG", "nulllll");
        if (mediaPlayer == null && QuestionCounter >= 6) {
            mediaPlayer = MediaPlayer.create(this, R.raw.during_question);
            mediaPlayer.setVolume(0.6f, 0.6f);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        mSoundPool.autoResume();
        saveAndSetEverything();//for Continue
    }

    protected void onPause() {
        super.onPause();
        if (QuestionCounter<=6&&!wasPressedBack)
            Toast.makeText(this, "The timer is still on!", Toast.LENGTH_SHORT).show();
        mSoundPool.autoPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Toast.makeText(this, "GAME SAVED", Toast.LENGTH_SHORT).show();
        saveAndSetEverything();//for Continue
    }

    public void initiateObjects() {
        ScoresArray = new String[]{
                "5,000", "10,000", "20,000", "40,000",
                "80,000", "1,60,000", "3,20,000", "6,40,000",
                "12,50,000", "25 Lakhs", "50 Lakhs", "1 Crore", "3 Crores",
                "5 Crores",           //13
                "7 Crores",//+15
                "5000", "10000", "20000", "40000",
                "80000", "160000", "320000", "640000",
                "1250000", "2500000", "5000000", "10000000", "30000000",
                "50000000",           //13
                "70000000"};                    //15
        tv = findViewById(R.id.tv_question);
        tv1 = findViewById(R.id.answer_option_one);
        tv2 = findViewById(R.id.answer_option_two);
        tv3 = findViewById(R.id.answer_option_three);
        tv4 = findViewById(R.id.answer_option_four);
        tv_money = findViewById(R.id.tv_money);
        tv_lifeLine = findViewById(R.id.textView);

        llimg1 = findViewById(R.id.life_line_50_50);
        llimg2 = findViewById(R.id.life_line_flip_the_question);
        llimg3 = findViewById(R.id.life_line_expert_advice);
        llimg4 = findViewById(R.id.life_line_double_tip);

        img = findViewById(R.id.img_background_question);
        img1 = findViewById(R.id.img_background_option_one);
        img2 = findViewById(R.id.img_background_option_two);
        img3 = findViewById(R.id.img_background_option_three);
        img4 = findViewById(R.id.img_background_option_four);
    }

    public void displayQuestions() {
        saveAndSetEverything();//for Continue
        getQuestionsAccordingToLevel();
            /*
            Changes Question number and Rs. in Log
            For Images animation
            */
        if (QuestionCounter < 15) {
            mSoundPool.play(soundIdStartInQuestion, 0.9f, 0.9f, 1, 0, 1f);
            A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
            img.startAnimation(A);

            A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
            A.setStartOffset(500);
            img1.startAnimation(A);

            A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
            A.setStartOffset(500);
            img2.startAnimation(A);

            A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
            A.setStartOffset(500);
            img3.startAnimation(A);

            A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
            A.setStartOffset(500);
            img4.startAnimation(A);

            tv.setText("Q." + QUESTION + "?");
            A = AnimationUtils.loadAnimation(this, R.anim.accelerate);
            tv.startAnimation(A);

            tv1.setText(String.format("A.%s", OPTION_A));
            A = AnimationUtils.loadAnimation(this, R.anim.anim_accelerate_x);
            A.setStartOffset(500);
            tv1.startAnimation(A);

            tv2.setText(String.format("B.%s", OPTION_B));
            A = AnimationUtils.loadAnimation(this, R.anim.anim_accelerate_x);
            A.setStartOffset(500);
            tv2.startAnimation(A);

            tv3.setText(String.format("C.%s", OPTION_C));
            A = AnimationUtils.loadAnimation(this, R.anim.anim_accelerate_x);
            A.setStartOffset(500);
            tv3.startAnimation(A);

            tv4.setText(String.format("D.%s", OPTION_D));
            A = AnimationUtils.loadAnimation(this, R.anim.anim_accelerate_x);
            A.setStartOffset(500);
            tv4.startAnimation(A);

            A = AnimationUtils.loadAnimation(this, R.anim.anim_accelerate_x_overshoot);
            tv_money.startAnimation(A);
            tv_money.setText("Question" + (QuestionCounter + 1) + " = ₹" + ScoresArray[QuestionCounter]);

        } else {
            maxLimit = true;
            Util.makePhoneVibrate(this, 300);
            GameOverStatus = true;
            GameContinued = false;
            output.NewGame = false;
            output.Continue = false;
            output.GameNewGame = false;
            saveSharedPreferencesForHighScores(PlayerName, Long.parseLong(ScoresArray[29]));
            finish();
        }
    }

    public void saveSharedPreferences() {
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_APPEND);

        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString("Name", output.Name);
        prefsEditor.putString("categoryPositionCounter", Arrays.toString(categoryPositionCounter));
        prefsEditor.putInt("easyQuestionCounter", easyQuestionCounter);
        prefsEditor.putInt("categoryPosition", categoryPosition);
        prefsEditor.putInt("currentQuestionInDoubleTip", currentQuestionInDoubleTip);
        prefsEditor.putBoolean("isLifeLineDoubleTipUsed", isLifeLineDoubleTipUsed);
        prefsEditor.putBoolean("isLifeLineDoubleTip", isLifeLineDoubleTip);
        prefsEditor.putBoolean("isLifeLineFiftyFifty", isLifeLineFiftyFifty);
        prefsEditor.putBoolean("isLifeLineFlipTheQuestion", isLifeLineFlipTheQuestion);
        prefsEditor.putBoolean("isLifeLineExpertAdvice", isLifeLineExpertAdvice);
        prefsEditor.putBoolean("isLifeLineFiftyFiftyCurrent", isLifeLineFiftyFiftyCurrent);
        prefsEditor.putBoolean("GameOverStatus", GameOverStatus);
        prefsEditor.putBoolean("GameContinued", GameContinued);
        prefsEditor.putInt("countDoubleTip", countDoubleTip);
        prefsEditor.putInt("QuestionCounter", QuestionCounter);
        prefsEditor.putInt("currentQuestionInFiftyFifty", currentQuestionInFiftyFifty);
        prefsEditor.apply();
    }

    public void setSharedPreferences() {
        SharedPreferences myPrefs2 = this.getSharedPreferences("myPrefs", MODE_APPEND);

        crs1 = findViewById(R.id.cross_img_background_option_one);
        crs2 = findViewById(R.id.cross_img_background_option_two);
        crs3 = findViewById(R.id.cross_img_background_option_three);
        crs4 = findViewById(R.id.cross_img_background_option_four);
        tv_playername = findViewById(R.id.tv_playername);

        easyQuestionCounter = myPrefs2.getInt("easyQuestionCounter", 0);
        selectedCategoriesNumber = Util.convertStringArrayToInts(myPrefs2.getString("selectedTopics",
                Arrays.toString(new int[selectedCategoriesNumber.length])));
        categoryPositionCounter = Util.convertStringArrayToInts(myPrefs2.getString("categoryPositionCounter",
                Arrays.toString(new int[selectedCategoriesNumber.length])));
        categoryPosition = myPrefs2.getInt("categoryPosition", 0);
        isLifeLineDoubleTipUsed = myPrefs2.getBoolean("isLifeLineDoubleTipUsed", false);
        isLifeLineDoubleTip = myPrefs2.getBoolean("isLifeLineDoubleTip", false);
        isLifeLineFiftyFifty = myPrefs2.getBoolean("isLifeLineFiftyFifty", false);
        isLifeLineFlipTheQuestion = myPrefs2.getBoolean("isLifeLineFlipTheQuestion", false);
        isLifeLineExpertAdvice = myPrefs2.getBoolean("isLifeLineExpertAdvice", false);
        isLifeLineFiftyFiftyCurrent = myPrefs2.getBoolean("isLifeLineFiftyFiftyCurrent", false);
        countDoubleTip = myPrefs2.getInt("countDoubleTip", 1);
        QuestionCounter = myPrefs2.getInt("QuestionCounter", 0);
        currentQuestionInFiftyFifty = myPrefs2.getInt("currentQuestionInFiftyFifty", 1);
        currentQuestionInDoubleTip=myPrefs2.getInt("currentQuestionInDoubleTip", 1);
        PlayerName = myPrefs2.getString("Name", "Player 1");
        tv_playername.setText(PlayerName);
        if (isLifeLineFiftyFifty) {
            crs1.setImageResource(R.drawable.cross);
        }
        if (isLifeLineFlipTheQuestion) {
            crs2.setImageResource(R.drawable.cross);
        }
        if (isLifeLineExpertAdvice) {
            crs3.setImageResource(R.drawable.cross);
        }
        if (isLifeLineDoubleTip) {
            crs4.setImageResource(R.drawable.cross);
        }
    }
    private void saveAndSetEverything() {
        saveSharedPreferences();
        setSharedPreferences();
    }

    private void setDefaultSharedPreferences() {
        /*
        FOR DEFAULT
        saving default values for new game then calling getSharedPreferences() to commit
        */
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_APPEND);

        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean("isLifeLineDoubleTipUsed", false);
        prefsEditor.putBoolean("isLifeLineDoubleTip", false);
        prefsEditor.putBoolean("isLifeLineFiftyFifty", false);
        prefsEditor.putBoolean("isLifeLineFlipTheQuestion", false);
        prefsEditor.putBoolean("isLifeLineExpertAdvice", false);
        prefsEditor.putBoolean("isLifeLineFiftyFiftyCurrent", false);
        prefsEditor.putInt("countDoubleTip", 1);
        prefsEditor.putInt("QuestionCounter", 0);
        prefsEditor.apply();
        setSharedPreferences();
    }

    private void showConfirmDialogWrongAnswer() {
        long dmoney;
        if (QuestionCounter == 0)
            dmoney = 0;
        else
            dmoney = Long.parseLong(ScoresArray[QuestionCounter + 15]);
        Log.d("PG", "money=" + dmoney);
        if (mediaPlayer != null && mediaPlayer.isPlaying())
        mediaPlayer.stop();
        saveSharedPreferencesForHighScores(PlayerName, dmoney);
        Log.d("PG", "Player Name got " + PlayerName);
        switch (RIGHT_ANSWER) {
            case "a":
                right_answer = OPTION_A;
                break;
            case "b":
                right_answer = OPTION_B;
                break;
            case "c":
                right_answer = OPTION_C;
                break;
            case "d":
                right_answer = OPTION_D;
                break;
        }
        //display the right answer too
        Util.makePhoneVibrate(this, 500);
        GameOver = true;
        GameOverStatus = true;
        GameContinued = false;
        output.NewGame = false;
        output.Continue = false;
        output.GameNewGame = false;
        finish();
    }

    private void showConfirmDialog(String msg) {
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_ok, null);
        popupWindowConfirm = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowConfirm.setFocusable(true);
        popupWindowConfirm.update();
        TextView tv_msg = popupView.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        ImageView imgok = popupView.findViewById(R.id.button_ok);
        popupWindowConfirm.dismiss();
        imgok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowConfirm.dismiss();
            }
        });
        popupWindowConfirm.showAtLocation(tv_playername, Gravity.CENTER, 0, 0);
    }

    public void isAnswerCorrect(View v)  {
        final int id = v.getId();
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        String selectedAnswer="";
        if (id == R.id.answer_option_one) {
            selectedAnswer="(a) "+OPTION_A;
        } else if (id == R.id.answer_option_two) {
            selectedAnswer="(b) "+OPTION_B;
        } else if (id == R.id.answer_option_three) {
            selectedAnswer="(c) "+OPTION_C;
        } else if (id == R.id.answer_option_four) {
            selectedAnswer="(d) "+OPTION_D;
        }
        if (mediaPlayer != null)
        mediaPlayer.pause();
        mSoundPool.play(soundIdLockSound, 0.9f, 0.9f, 1, 0, 1f);
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_confirm, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv_msg = popupView.findViewById(R.id.tv_msg);
        tv_msg.setText(String.format("%s %s", getString(R.string.are_you_sure_warning), selectedAnswer));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d("PG", "Dismissed");
                if (mediaPlayer != null)
                mediaPlayer.start();
            }
        });
        popupWindow.setFocusable(true);
        ImageView imgno = popupView.findViewById(R.id.img_no);
        imgno.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                popupWindow.dismiss();
            }
        });
        ImageView imgyes = popupView.findViewById(R.id.img_yes);
        imgyes.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer30Seconds.cancel();
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                popupWindow.dismiss();
                if (isLifeLineDoubleTip) {
                    isLifeLineDoubleTipUsed = false;
                    if ((id == R.id.answer_option_one || id == R.id.img_background_option_one) && RIGHT_ANSWER.equals("a") ||
                            (id == R.id.answer_option_two || id == R.id.img_background_option_two) && RIGHT_ANSWER.equals("b") ||
                            (id == R.id.answer_option_three || id == R.id.img_background_option_three) && RIGHT_ANSWER.equals("c") ||
                            (id == R.id.answer_option_four || id == R.id.img_background_option_four) && RIGHT_ANSWER.equals("d")) {
                        isLifeLineDoubleTipUsed = true;
                        countDoubleTip = 2;
                /* Turn it green and notify it */
                        showRightAnswerMessage();
            /* For next Question? */
                        ++QuestionCounter;
                        displayQuestions();
                    } else {
                        if (countDoubleTip < 2) {
                    /* Show Dialog in place of Toast bottom and fade without options or with cross icon */
                            showConfirmDialog("Wrong Answer,Last Chance to Try!!!");
                            countDoubleTip++;
                        } else {
                    /* Show Dialog in place of Toast bottom and fade without options or with cross icon */
                    /* Note the High Scores and if appropriate list it in List */
                            ++QuestionCounter;
                            showConfirmDialogWrongAnswer();
                        }
                    }
                } else {
                    if ((id == R.id.answer_option_one || id == R.id.img_background_option_one) && RIGHT_ANSWER.equals("a") ||
                            (id == R.id.answer_option_two || id == R.id.img_background_option_two) && RIGHT_ANSWER.equals("b") ||
                            (id == R.id.answer_option_three || id == R.id.img_background_option_three) && RIGHT_ANSWER.equals("c") ||
                            (id == R.id.answer_option_four || id == R.id.img_background_option_four) && RIGHT_ANSWER.equals("d")) {
            /* Turn it green and notify it */
                        showRightAnswerMessage();
            /* For next Question? */
                        ++QuestionCounter;
                        displayQuestions();
                    } else {
                        // Note the High Scores and if appropriate list it in List
                        showConfirmDialogWrongAnswer();
                    }
                }
            }
        });
        popupWindow.showAtLocation(tv_playername, Gravity.CENTER, 0, 0);
    }

    public void showConfirmLifeLine(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        final int id = v.getId();
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_confirm, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv_msg = popupView.findViewById(R.id.tv_msg);
        if (id == R.id.life_line_50_50 && isLifeLineFiftyFifty) {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            Toast.makeText(this, "Life Line-50:50 is Used!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (id == R.id.life_line_expert_advice && isLifeLineExpertAdvice) {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            Toast.makeText(this, "Life Line-Expert Advice is Used!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (id == R.id.life_line_double_tip && isLifeLineDoubleTip) {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            Toast.makeText(this, "Life Line-Double Tip is Used!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (id == R.id.life_line_flip_the_question && isLifeLineFlipTheQuestion) {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            Toast.makeText(this, "Life Line-Flip The Question is Used!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (id == R.id.life_line_50_50)
            tv_msg.setText("Use Life Line 50 50?\n(Removes two Wrong Answers)");
        if (id == R.id.life_line_expert_advice)
            tv_msg.setText("Use Life Line Expert Advice?\n(Tells the Answer which should be Correct)");
        if (id == R.id.life_line_flip_the_question)
            tv_msg.setText("Use Life Line Flip The Question?\n(Displays the Next Question)");
        if (id == R.id.life_line_double_tip)
            tv_msg.setText(R.string.double_tip_warning);
        popupWindow.setFocusable(true);
        ImageView imgno = popupView.findViewById(R.id.img_no);
        imgno.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        ImageView imgyes = popupView.findViewById(R.id.img_yes);
        imgyes.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (id == R.id.life_line_50_50)
                    getLifeLineFiftyFifty(v);
                if (id == R.id.life_line_expert_advice)
                    getLifeLineExpertAdvice(v);
                if (id == R.id.life_line_flip_the_question)
                    getLifeLineFlipTheQuestion(v);
                if (id == R.id.life_line_double_tip)
                    getLifeLineDoubleTip(v);
            }
        });
        popupWindow.showAtLocation(tv_playername, Gravity.CENTER, 0, 0);
    }

    public void getLifeLineFiftyFifty(View v) {
        //Delete two wrong options
        if (isLifeLineDoubleTip && QuestionCounter == currentQuestionInDoubleTip) {
            Toast.makeText(this, "Can't use Double tip and fifty in same question.", Toast.LENGTH_SHORT).show();
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            return;
        }
        currentQuestionInFiftyFifty = QuestionCounter;
        isLifeLineFiftyFifty = true;
        isLifeLineFiftyFiftyCurrent = true;
        crs1.setImageResource(R.drawable.cross);
        A = AnimationUtils.loadAnimation(this, R.anim.anim_scale_in);
        A.setDuration(300);
        crs1.startAnimation(A);
            /* Show Dialog in place of Toast bottom and fade without options or with cross icon */
        Toast.makeText(this, "50:50 Life Line Activated!", Toast.LENGTH_SHORT).show();
        switch (RIGHT_ANSWER) {
            case "a":
                tv2.setText("");
                tv4.setText("");
                break;
            case "b":
                tv4.setText("");
                tv1.setText("");
                break;
            case "c":
                tv4.setText("");
                tv1.setText("");
                break;
            case "d":
                tv3.setText("");
                tv1.setText("");
                break;
        }
    }

    public void getLifeLineFlipTheQuestion(View v) {
        isLifeLineFlipTheQuestion = true;
        timer30Seconds.cancel();
        crs2.setImageResource(R.drawable.cross);
        A = AnimationUtils.loadAnimation(this, R.anim.anim_scale_in);
        A.setDuration(300);
        crs2.startAnimation(A);
        Toast.makeText(this, "Flip The Question Life Line Activated! QUESTION FLIPPED", Toast.LENGTH_SHORT).show();
        displayQuestions();
    }

    public void getLifeLineExpertAdvice(View v) {
        //tells the right answer
        isLifeLineExpertAdvice = true;
        crs3.setImageResource(R.drawable.cross);
        A = AnimationUtils.loadAnimation(this, R.anim.anim_scale_in);
        A.setDuration(300);
        crs3.startAnimation(A);
        Toast.makeText(this, "Expert Advice Life Line Activated!", Toast.LENGTH_SHORT).show();
        //Dialog is displayed with OK button
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_ok, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        TextView tv_msg = popupView.findViewById(R.id.tv_msg);
        tv_msg.setText("Maybe " + RIGHT_ANSWER.toUpperCase() + " is the Correct Answer" + ".");
        ImageView imgok = popupView.findViewById(R.id.button_ok);
        imgok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(tv_playername, Gravity.CENTER, 0, 0);
    }

    public void getLifeLineDoubleTip(View v) {
        isLifeLineDoubleTip = true;
        currentQuestionInDoubleTip = QuestionCounter;
        if (isLifeLineFiftyFiftyCurrent && QuestionCounter == currentQuestionInFiftyFifty) {
            A = AnimationUtils.loadAnimation(this, R.anim.animation_fade_out);
            llimg4.startAnimation(A);
        }
        //Double Tip allows double try for the right answers
        if (isLifeLineFiftyFiftyCurrent && QuestionCounter == currentQuestionInFiftyFifty)//working
        {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            Toast.makeText(this, "Can't use Double tip and fifty in same question.", Toast.LENGTH_SHORT).show();
            isLifeLineDoubleTip=false;

        } else {
            isLifeLineDoubleTipUsed = true;
            crs4.setImageResource(R.drawable.cross);
            A = AnimationUtils.loadAnimation(this, R.anim.anim_scale_in);
            A.setDuration(300);
            crs4.startAnimation(A);
            Toast.makeText(this, "Double Tip Life Line Activated!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showRightAnswerMessage() {
        if (!maxLimit) {
            mSoundPool.play(soundIdShortApplause, 0.6f, 0.6f, 1, 0, 1f);
        }
        tv_msg_inplayGame = findViewById(R.id.msg_in_playGame);
        tv_msg_inplayGame.setText(R.string.right_answer_loud);
        A = AnimationUtils.loadAnimation(this, R.anim.anim_accelerate_x_full);
        tv_msg_inplayGame.startAnimation(A);
    }

    public void saveSharedPreferencesForHighScores(String Player_Name, long q) {
        //TODO High scores 10000 then 0 then again 10000 but should be 10k 10k 0
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefsScores", MODE_APPEND);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        int size = High_Scores.Score.length, pos = 0;
            if (q > Integer.parseInt(High_Scores.Score[0])) {
                pos = 0;
            } else {
                for (int i = 0; i < size - 1; i++) {
                    if (Integer.parseInt(High_Scores.Score[i]) >= q && q > Integer.parseInt(High_Scores.Score[i + 1])) {
                        pos = i + 1;
                        break;
                    }
                    if (i == size - 1)
                        pos = size;
                }
            }
            for (int i = size - 1; i > pos; i--) {
                High_Scores.Score[i] = High_Scores.Score[i - 1];
                High_Scores.PName[i] = High_Scores.PName[i - 1];
            }
            High_Scores.Score[pos] = Long.toString(q);
            High_Scores.PName[pos] = Player_Name;
            High_Scores.Score[size - 1] = "0";

            for (int i = 0; i < size; i++) {
                sb1.append(High_Scores.PName[i]).append(",");
                sb.append(High_Scores.Score[i]).append(",");
            }
            SharedPreferences.Editor prefsEditor = myPrefs.edit();
            prefsEditor.putString("SCORES", sb.toString());
            prefsEditor.putString("PNAME", sb1.toString());
            prefsEditor.apply();
    }

    @Override
    public void onBackPressed() {
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_confirm, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv_msg = popupView.findViewById(R.id.tv_msg);
        tv_msg.setText(R.string.continue_game_later_warning);
        popupWindow.setFocusable(true);
        ImageView imgno = popupView.findViewById(R.id.img_no);
        imgno.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        ImageView imgyes = popupView.findViewById(R.id.img_yes);
        imgyes.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                mSoundPool.release();
                if (mediaPlayer != null)
                mediaPlayer.release();
                wasPressedBack=true;
                finish();
            }
        });
        popupWindow.showAtLocation(tv_playername, Gravity.CENTER, 0, 0);
    }

    public boolean isDatabasePresent() {
            File dbFile = this.getDatabasePath("QuestionsManager");
            return dbFile.exists();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer30Seconds.cancel();
        mydb.closeDB();
        mSoundPool.release();
        if (mediaPlayer != null)
        mediaPlayer.release();
    }

    public void getQuestionsAccordingToLevel() {
        String tableName;
        int counter;
        if (QuestionCounter < 6) {
            timer30Seconds.start();
            tableName = "Easy";
            easyQuestionCounter = easyQuestionCounter == max_easy ? 1 : ++easyQuestionCounter;
            Log.d("PG", "easy counter=" + easyQuestionCounter);
            counter = easyQuestionCounter;
        } else {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.during_question);
                mediaPlayer.setVolume(0.6f, 0.6f);
                mediaPlayer.setLooping(true);
                Log.d("PG", " initialized");
            }
            if (QuestionCounter >= 6) {
                mediaPlayer.start();
            }
            tv_lifeLine.setVisibility(View.GONE);
            categoryPosition = categoryPosition == selectedCategoriesNumber.length - 1 ? 0 : ++categoryPosition;
            categoryPositionCounter[categoryPosition] =
                    categoryPositionCounter[categoryPosition] == selectedCategoriesSizes[categoryPosition] ?
                            1 : ++categoryPositionCounter[categoryPosition];
            tableName = selectedCategories.get(categoryPosition);
            counter = categoryPositionCounter[categoryPosition];
            Log.d("PG", "selected sizes" + Arrays.toString(selectedCategoriesSizes));
            Log.d("PG", " category pos counter at " + categoryPosition + " " + Arrays.toString(categoryPositionCounter) + " tableName" + tableName);
        }
        mydb.getQuestions(tableName, counter);
    }
}