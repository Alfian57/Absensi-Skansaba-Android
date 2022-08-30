package com.postingan.absenssiswasmkn1bantul.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public User(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setToken(String token){
        editor.putString("TOKEN", "Bearer " + token);
        editor.commit();
        editor.apply();
    }

    public void setNullToken(){
        editor.putString("TOKEN", null);
        editor.commit();
        editor.apply();
    }

    public String getToken(){
        if (sharedPreferences.contains("TOKEN")){
            return sharedPreferences.getString("TOKEN", null);
        }
        return null;
    }

    public void setId(Integer id){
        editor.putString("ID", String.valueOf(id));
        editor.commit();
        editor.apply();
    }

    public String getId(){
        if (sharedPreferences.contains("ID")){
            return sharedPreferences.getString("ID", null);
        }
        return null;
    }
}
