package ru.digitbreak.mobileapp.service;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import javax.inject.Inject;

public class SharedPreferenceService {

    private final SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferenceService(Context context){
        sharedPreferences = context.getSharedPreferences("db", Context.MODE_PRIVATE);
    }

    public boolean removeSavedValue(String field){
        return sharedPreferences.edit().remove(field).commit();
    }

    public String getSavedValue(String field, String defaultValue){
        return sharedPreferences.getString(field, defaultValue);
    }

    public boolean setSavedValue(String field,String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(field,value);
        return editor.commit();
    }

    public boolean setSavedValue(String field, Set<String> value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(field,value);
        return editor.commit();
    }
}
