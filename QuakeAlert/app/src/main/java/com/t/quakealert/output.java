package com.t.quakealert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class output extends Activity {
    static Animation A;
    static boolean GameNewGame, Continue = false, NewGame;
    static String Name = "";
    boolean i;
    ImageView img_continue_game, kbc, button_play, button_high_scores, button_settings;
    TextView tv_continue_details, tv_alert;
    PopupWindow popupWindow;
    Intent intent_play_game;
    EditText et_playername;
    PopupWindow popupWindowPlayerName, popupWindowConfirm;
    SoundPool soundpool;
    private MediaPlayer mediaPlayer;
    private int soundIdEndHorn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Next three lines for Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        //set volume key control
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundpool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .build();
        } else {
            soundpool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }
        soundpool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                Log.d("output", "sound end horn loaded");
            }
        });
        soundIdEndHorn = soundpool.load(this, R.raw.end_horn, 1);

        setSharedPreferences();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //for win sound pause
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    protected void onResume() {
        super.onResume();
        if (PlayGame.maxLimit) {
            showConfirmDialog("You have won 7 Crores Successfully.\nGAME COMPLETED!!!.");
            mediaPlayer = MediaPlayer.create(this, R.raw.win);
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (PlayGame.GameOver) {
            if (PlayGame.gameOverByTimer)
                showConfirmDialog("The Right answer was (" + PlayGame.RIGHT_ANSWER + ") " + PlayGame.right_answer);
            else
                showConfirmDialog("Wrong Answer, Game Over." + "\nThe Right answer was (" + PlayGame.RIGHT_ANSWER + ") " + PlayGame.right_answer);
            soundpool.play(soundIdEndHorn, 0.2f, 0.2f, 1, 0, 1f);
            PlayGame.GameOver = false;
        }
        saveSharedPreferences();
        setSharedPreferences();
        kbc = findViewById(R.id.kbc_small);
        button_play = findViewById(R.id.button_play);
        button_high_scores = findViewById(R.id.button_high_scores);
        button_settings = findViewById(R.id.button_settings);

        img_continue_game = findViewById(R.id.img_continue_game);
        A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
        tv_continue_details = findViewById(R.id.tv_continue_details);
        img_continue_game.startAnimation(A);

        //Animation for kbc icon
        A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
        kbc.startAnimation(A);

        //Animation for play button
        A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
        A.setStartOffset(500);
        button_play.startAnimation(A);

        //Animation for high scores button
        A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
        A.setStartOffset(500);
        button_high_scores.startAnimation(A);

        //Animation for settings button
        A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
        A.setStartOffset(500);
        button_settings.startAnimation(A);
    }

    public void playGame(View v) {
        intent_play_game = new Intent(output.this, PlayGame.class);
        //stop win sound before starting following cases
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.pause();

        switch (v.getId()) {
            case R.id.img_continue_game:              //Continue game
                if (PlayGame.GameOverStatus && !PlayGame.GameContinued || Name.equals("Player 1")) {
                    img_continue_game.setImageResource(R.drawable.nothing);
                    button_play.setImageResource(R.drawable.play);
                    tv_continue_details.setText("");
                } else {
                    Continue = true;
                    NewGame = false;
                    saveSharedPreferences();
                    startActivity(intent_play_game);
                }
                break;
            case R.id.button_play:                   //New Game
                if (getSharedPreferences("myPrefs", MODE_PRIVATE).getString("Name", "Player 1").equals("Player 1"))
                {
                    Log.d("output","first Run");
                    showInstructionsConfirmDialog();
                } else {
                    getPlayerName();
                }
                break;
            case R.id.kbc_small:
                if (!i) {
                    A = AnimationUtils.loadAnimation(this, R.anim.rotate);
                    kbc.startAnimation(A);
                    i = true;
                } else {
                    A = AnimationUtils.loadAnimation(this, R.anim.rotate_reverse);
                    kbc.startAnimation(A);
                    i = false;
                }
                break;
            case R.id.tv_continue_details:
                if (!i) {
                    A = AnimationUtils.loadAnimation(this, R.anim.anim_accelerate_x_come);
                    tv_continue_details.startAnimation(A);
                    i = true;
                } else {
                    A = AnimationUtils.loadAnimation(this, R.anim.anim_accelerate_x_exit);
                    tv_continue_details.startAnimation(A);
                    i = false;
                }
                break;
            case R.id.button_high_scores:
                Intent intent = new Intent(this, High_Scores.class);
                startActivity(intent);
                break;
            case R.id.button_settings:
                Intent intent_settings = new Intent(this, Settings.class);
                startActivity(intent_settings);
                break;
        }
    }

    private void showRateAppDialog() {
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_rate_app, null);
        popupWindowConfirm = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowConfirm.setFocusable(true);
        popupWindowConfirm.update();
        ImageView imgok = popupView.findViewById(R.id.button_ok);
        final CheckBox checkbox = popupView.findViewById(R.id.checkbox_again);
        popupWindowConfirm.dismiss();
        imgok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked())
                    getSharedPreferences("myPrefs", MODE_PRIVATE).edit().putBoolean("askAgain", false).apply();
                startMarketIntent();
                Toast.makeText(output.this, "Thanks!", Toast.LENGTH_LONG).show();
                popupWindowConfirm.dismiss();
            }

            private void startMarketIntent() {
                Intent marketIntent = new Intent();
                marketIntent.setAction(Intent.ACTION_VIEW);
                marketIntent.setData(Uri.parse("market://details?id=com.raman.rupanshu.kbc"));
                startActivity(marketIntent);
            }
        });
        popupWindowConfirm.showAtLocation(kbc, Gravity.CENTER, 0, 0);
    }

    private void showInstructionsConfirmDialog() {
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_instructions, null);
        popupWindowPlayerName = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowPlayerName.setFocusable(true);
        popupWindowPlayerName.update();
        ImageView ok = popupView.findViewById(R.id.button_ok);
        ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowPlayerName.dismiss();
                getPlayerName();
            }
        });
        popupWindowPlayerName.showAtLocation(kbc, Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void getPlayerName() {
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_playername, null);
        popupWindowPlayerName = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowPlayerName.setFocusable(true);
        popupWindowPlayerName.update();
        et_playername = popupView.findViewById(R.id.et_playername);
        final InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        A = AnimationUtils.loadAnimation(this, R.anim.animation_button_play);
        A.setDuration(500);
        tv_alert = popupView.findViewById(R.id.name_alert);
        Button ok = popupView.findViewById(R.id.img_ok);
        ok.startAnimation(A);
        ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = et_playername.getText().toString();
                if (Name.isEmpty()) {
                    Name = et_playername.getText().toString();
                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    tv_alert.setText(R.string.name_empty_warning);
                } else {
                    imm.toggleSoftInputFromWindow(v.getWindowToken(), 0, 0);
                    Vibrator vb = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    vb.vibrate(50);
                    GameNewGame = true;
                    NewGame = true;
                    Continue = false;
                    PlayGame.GameContinued = true;
                    popupWindowPlayerName.dismiss();
                    startActivity(intent_play_game);
                }
            }
        });
        popupWindowPlayerName.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindowPlayerName.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindowPlayerName.showAtLocation(kbc, Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void showConfirmDialog(final String msg) {
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
        if (PlayGame.maxLimit)
        tv_msg.setText(msg);
        else
            tv_msg.setText(msg);
        ImageView imgok = popupView.findViewById(R.id.button_ok);
        popupWindowConfirm.dismiss();
        imgok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowConfirm.dismiss();
            }
        });
        popupWindowConfirm.showAtLocation(kbc, Gravity.CENTER, 0, 0);
    }

    public void saveSharedPreferences() {
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_APPEND);

        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString("Name", Name);
        prefsEditor.putBoolean("GameNewGame", GameNewGame);
        prefsEditor.putBoolean("GameOverStatus", PlayGame.GameOverStatus);
        prefsEditor.putBoolean("GameContinued", PlayGame.GameContinued);
        prefsEditor.apply();
        setSharedPreferences();
    }

    public void setSharedPreferences() {
        SharedPreferences myPrefs2 = this.getSharedPreferences("myPrefs", MODE_APPEND);

        img_continue_game = findViewById(R.id.img_continue_game);
        tv_continue_details = findViewById(R.id.tv_continue_details);
        button_play = findViewById(R.id.button_play);
        PlayGame.GameOverStatus = myPrefs2.getBoolean("GameOverStatus", true);
        PlayGame.GameContinued = myPrefs2.getBoolean("GameContinued", false);
        GameNewGame = myPrefs2.getBoolean("GameNewGame", true);
        Name = myPrefs2.getString("Name", "Player 1");
        if (PlayGame.maxLimit) {
            img_continue_game.setImageResource(R.drawable.nothing);
            button_play.setImageResource(R.drawable.play);
            tv_continue_details.setText("");
            PlayGame.maxLimit = false;
        }
        if (!PlayGame.GameOverStatus || !PlayGame.GameContinued) {//if game is not over or game not continued
            img_continue_game.setImageResource(R.drawable.nothing);
            button_play.setImageResource(R.drawable.play);
            tv_continue_details.setText("");
        } else {
            img_continue_game.setImageResource(R.drawable.continue_game);
            button_play.setImageResource(R.drawable.new_game);
            tv_continue_details.setText(String.format("%s %s", getString(R.string.continue_as_prefix), Name));
        }
    }

    public void onBackPressed() {
        if (popupWindowPlayerName != null)
            popupWindowPlayerName.dismiss();
        LayoutInflater layoutInflater
                = this.getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_confirm, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv_msg = popupView.findViewById(R.id.tv_msg);
        tv_msg.setText(R.string.quit_warning);
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
                finish();
            }
        });
        popupWindow.showAtLocation(kbc, Gravity.CENTER, 0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("output", "mp destroyed");
        if (mediaPlayer != null)
            mediaPlayer.release();
    }
}