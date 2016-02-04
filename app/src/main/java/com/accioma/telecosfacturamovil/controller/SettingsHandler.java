package com.accioma.telecosfacturamovil.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.accioma.telecosfacturamovil.R;
import com.accioma.telecosfacturamovil.activity.SettingsFormActivity;


/**
 * Created by marcelo on 2/2/16.
 */
public class SettingsHandler {
   public static final String PREFS_FILE_NAME = "com_accioma_facturamovil_PREFERENCES";

    public static void saveSettings(SettingsFormActivity activity){
        SharedPreferences.Editor editor = activity.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString("username", activity.getUsername());
        editor.putString("password", activity.getPassword());
        editor.putString("host", activity.getHost() );
        editor.commit();
    }
}
