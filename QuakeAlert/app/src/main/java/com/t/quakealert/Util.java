package com.t.quakealert;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import static android.content.Context.VIBRATOR_SERVICE;


//TODO Make a copy of this file before randomization
class Util {
    static String[] topics = new String[]{"EarthQuake"};
    static int[] topicsSize = new int[]{15};
    static void loadQuestions(DatabaseHelper mydb, int[] selectedCategories) {
        Log.d("Util", "load Question started");
        //default if not topics selected
        loadEasyQuestions(mydb);
        for (int i : selectedCategories) {
            switch (i) {
                //EarthQuake
                case 0:
                    loadEarthquake(mydb);
                    break;
                //Cricket
            }
        }
        //mydb.insert(++counter_serial_number, "Computer Science", true, "", "", "", "", "", "c");

    }

    private static void loadEarthquake(DatabaseHelper mydb) {
        int counter_serial_number = 0;
        //easy gk questions
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("EarthQuake", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");

    }



    private static void loadEasyQuestions(DatabaseHelper mydb) {
        int counter_serial_number = 0;
        //        (           Sr.No.         , subject,difficulty,  QUESTIONS   , A  , B  , C  , D  , correct_option)
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");

        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");

        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");

        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");








    }



    static void makePhoneVibrate(Context context, int timeInMilliSeconds) {
        Vibrator vb = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        vb.vibrate(timeInMilliSeconds);
    }

    static int[] convertStringArrayToInts(String categoryPositionCounter) {
        String[] strings = categoryPositionCounter.replace("[", "").replace("]", "").split(", ");
        int result[] = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    static boolean[] convertStringToBoolean(String string) {
        int[] ints = convertStringArrayToInts(string);
        boolean[] result = new boolean[topics.length];
        for (int i : ints)
            result[i] = true;
        return result;
    }
}