package yash.aditya;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "ICSA Hackathon";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String NAME="Name";
    private static final String NUMBER="Number";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setName(String Name)
    {
        editor.putString(NAME,Name);
        editor.commit();
    }

    public String getName()
    {
        return pref.getString(NAME, "Aditya Aggarwal");
    }

    public void setNumber(String Number)
    {
        editor.putString(NUMBER,Number);
        editor.commit();
    }

    public String getNumber()
    {
        return pref.getString(NUMBER, "9810514311");
    }



}