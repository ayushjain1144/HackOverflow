package com.t.quakealert;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class High_Scores extends Activity {
    static String[] Score = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"}, PName = new String[11];

    //keep one fake element at last to prevent OutOfBounds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Next three lines for Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high__scores);
        getSharedPreferences();
    }

    public void getSharedPreferences() {
        SharedPreferences myPrefs2 = this.getSharedPreferences("myPrefsScores", MODE_APPEND);

        TextView tv[] = {findViewById(R.id.tv_high_scores_1), findViewById(R.id.tv_high_scores_2), findViewById(R.id.tv_high_scores_3),
                findViewById(R.id.tv_high_scores_4), findViewById(R.id.tv_high_scores_5), findViewById(R.id.tv_high_scores_6),
                findViewById(R.id.tv_high_scores_7), findViewById(R.id.tv_high_scores_8), findViewById(R.id.tv_high_scores_9),
                findViewById(R.id.tv_high_scores_10)};

        String Scores = myPrefs2.getString("SCORES", "0");
        String PNames = myPrefs2.getString("PNAME", "0");
        Score = Scores.split(",");
        PName = PNames.split(",");
        for (int i = 0; i < Score.length - 1; i++) {
            if (PName[i].equals("null"))
                break;
            tv[i].setText("" + PName[i] + "                ₹" + Score[i]);
        }
    }
}