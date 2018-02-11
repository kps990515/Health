package org.andriodtown.health;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 2018-02-04.
 */

public class SharedPreferenceManager {
    private static String LOGIN = "login";

    private static SharedPreferenceManager spf = null;
    private static SharedPreferences.Editor loginEditor;

    public static SharedPreferenceManager getInstance() {
        if (spf == null) {
            spf = new SharedPreferenceManager();
            return spf;
        } else {
            return spf;
        }
    }

    private Context context;
    private SharedPreferences loginSpf;

    private SharedPreferenceManager() {
        context = ApplicationInitializer.getAppContext();
        loginSpf = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        loginEditor = loginSpf.edit();
    }

    public void setId(String id){
        loginEditor.putString("ID",id);
    }

    public String getId(){
       return loginSpf.getString("ID","");
    }

    public void setPw(String pw){
        loginEditor.putString("PW", pw);
    }

    public String getPw(){
        return loginSpf.getString("PW","");
    }

    public void logout(){
        loginEditor.clear();
        loginEditor.commit();
    }
}









