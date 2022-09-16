package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.LoginDetailResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.LogoutResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;
import com.postingan.absenssiswasmkn1bantul.MainActivity;
import com.postingan.absenssiswasmkn1bantul.UI.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private ApiRequest apiRequest;
    MutableLiveData<LoginDetailResponse> loginMutableLiveData;
    MutableLiveData<LogoutResponse> logoutMutableLiveData;

    public LoginRepository(Application application) {
        this.apiRequest = ApiConfig.getClient(application).create(ApiRequest.class);
        loginMutableLiveData = new MutableLiveData<>();
        logoutMutableLiveData = new MutableLiveData<>();
    }

    public void login(String nis, String password){
        Call<LoginDetailResponse> call = apiRequest.Login(nis, password);
        call.enqueue(new Callback<LoginDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginDetailResponse> call, @NonNull Response<LoginDetailResponse> response) {
                if (response.body() != null){
                    loginMutableLiveData.postValue(response.body());
                } else {
                    loginMutableLiveData.postValue(null);
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginDetailResponse> call, @NonNull Throwable t) {
                Log.e("login", t.toString());
                loginMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<LoginDetailResponse> getLogin(){
        return loginMutableLiveData;
    }

    public void logout(String token){
        Call<LogoutResponse> call = apiRequest.Logout(token);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call,@NonNull Response<LogoutResponse> response) {
                if (response.body() != null){
                    logoutMutableLiveData.postValue(response.body());
                } else {
                    logoutMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call,@NonNull Throwable t) {
                Log.e("logout", t.toString());
                logoutMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<LogoutResponse> getLogout(){
        return logoutMutableLiveData;
    }

}
