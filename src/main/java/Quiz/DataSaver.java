package Quiz;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class DataSaver {
    // Shared Preference Variables
    private static final String SHARED_PREF_ID = "com.jfinkelstudios.mobile.algebraquizapp";
    private static final String DAILY_STREAKS_KEY = "Daily_Streaks";
    private static final int EMPTY_VALUE = 0;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    // Class Instance Variables
    private int quizzesCompleted;
    private int totalPoints;
    private int timeSpent;
    private Context context;


    public DataSaver(Context context) {
        this.context = context;
    }


    public void saveData(int data) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt(DAILY_STREAKS_KEY, data);
        editor.apply();
    }

    public int loadData() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
        return (sharedPreferences.getInt(DAILY_STREAKS_KEY, EMPTY_VALUE));
    }

    public void cleaAllData() {
        editor.clear();
    }


}// END OF CLASS
