package com.t.quakealert;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import static android.content.Context.VIBRATOR_SERVICE;


//TODO Make a copy of this file before randomization
class Util {
    static String[] topics = new String[]{"EarthQuake"};
    static int[] topicsSize = new int[]{9};
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

        mydb.insert("EarthQuake", ++counter_serial_number, "While an earthquake is taking place you should:", "Stop, Drop, and Roll", "Drop, Cover, and Hold On", "Start running around", "Wait for the tremors to stop", "b");

        mydb.insert("EarthQuake", ++counter_serial_number, "How much water should you have in your home emergency kit?", "1 gallon per person per day", "1 gallon per every 2 people per day", "3 gallons per person per day", "3 gallons per family per day", "a");

        mydb.insert("EarthQuake", ++counter_serial_number, "To where should you evacuate if near a large body of water?", "The closest shelter", "The closest shelter", "The closest shelter", "Your car", "c");

        mydb.insert("EarthQuake", ++counter_serial_number, "If you see a large fire you should:", "Leave immediately", "Call for help and wait for fire department to arrive", "Try to put it out", "Call fire department and leave immediately afterwards.", "d");

        mydb.insert("EarthQuake", ++counter_serial_number, "How long after a major earthquake can aftershocks continue to happen?", "Hours", "Days", "Weeks", "Months", "c");

        mydb.insert("EarthQuake", ++counter_serial_number, "From where shouldn’t you retrieve water if yours is tainted?", "Water heaters", "Toilet tank", "Canned vegetables", "Radiators", "d");

        mydb.insert("EarthQuake", ++counter_serial_number, "How long can you keep frozen foods in the freezer if the door is closed?", "Hours", "One night", "A few days", "A week", "c");

        mydb.insert("EarthQuake", ++counter_serial_number, "How often should you replace perishable items in your emergency kit such as water, food, meds, and batteries?", "Every 4 months", "Every 6 months", "Every year", "Every 2 yearss", "c");
    }



    private static void loadEasyQuestions(DatabaseHelper mydb) {
        int counter_serial_number = 0;
        //        (           Sr.No.         , subject,difficulty,  QUESTIONS   , A  , B  , C  , D  , correct_option)
        mydb.insert("Easy", ++counter_serial_number, "When an earthquake strikes, you should:", "Run outside to avoid falling building debris", "Take cover under a heavy piece of furniture", "Lean against an inside wall or stand under an inside doorway", "B and/or C", "d");

        mydb.insert("Easy", ++counter_serial_number, "While an earthquake is taking place you should:", "Stop, Drop, and Roll", "Drop, Cover, and Hold On", "Start running around", "Wait for the tremors to stop", "b");

        mydb.insert("Easy", ++counter_serial_number, "How much water should you have in your home emergency kit?", "1 gallon per person per day", "1 gallon per every 2 people per day", "3 gallons per person per day", "3 gallons per family per day", "a");

        mydb.insert("Easy", ++counter_serial_number, "To where should you evacuate if near a large body of water?", "The closest shelter", "The closest shelter", "The closest shelter", "Your car", "c");

        mydb.insert("Easy", ++counter_serial_number, "If you see a large fire you should:", "Leave immediately", "Call for help and wait for fire department to arrive", "Try to put it out", "Call fire department and leave immediately afterwards.", "d");

        mydb.insert("Easy", ++counter_serial_number, "How long after a major earthquake can aftershocks continue to happen?", "Hours", "Days", "Weeks", "Months", "c");

        mydb.insert("Easy", ++counter_serial_number, "From where shouldn’t you retrieve water if yours is tainted?", "Water heaters", "Toilet tank", "Canned vegetables", "Radiators", "d");

        mydb.insert("Easy", ++counter_serial_number, "How long can you keep frozen foods in the freezer if the door is closed?", "Hours", "One night", "A few days", "A week", "c");

        mydb.insert("Easy", ++counter_serial_number, "How often should you replace perishable items in your emergency kit such as water, food, meds, and batteries?", "Every 4 months", "Every 6 months", "Every year", "Every 2 yearss", "c");




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