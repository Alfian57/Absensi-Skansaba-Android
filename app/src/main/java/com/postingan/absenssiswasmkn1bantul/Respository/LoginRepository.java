package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
    private Context context;
    private ApiRequest apiRequest;
    User user;

    public LoginRepository(Application application) {
        this.context = application.getApplicationContext();
        this.apiRequest = ApiConfig.getClient(context).create(ApiRequest.class);
        this.user = new User(context);
    }

    public void apiLogin(String nis, String password){
        Call<LoginDetailResponse> call = apiRequest.Login(nis, password);
        call.enqueue(new Callback<LoginDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginDetailResponse> call, @NonNull Response<LoginDetailResponse> response) {
                if (response.body() != null){
                    if (response.body().getData() != null){
                        if (response.body().getData().getStudent() != null){
                            user.setId(response.body().getData().getStudent().getId());
                            user.setToken(response.body().getData().getAccessToken());
                            Intent i = new Intent(context, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                        else {
                            if (response.body().getMessage() != null){
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Gagal Melakukan Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        if (response.body().getMessage() != null){
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Gagal Melakukan Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginDetailResponse> call, @NonNull Throwable t) {
                Log.e("login", t.toString());
                Toast.makeText(context, "Terjadi Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void apiLogout(){
        Call<LogoutResponse> call = apiRequest.Logout(user.getToken());
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call,@NonNull Response<LogoutResponse> response) {
                if (response.body() != null){
                    if (response.body().getMessage() != null) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        user.setNullToken();
                        user.setId(null);
                        Intent i = new Intent(context, LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call,@NonNull Throwable t) {
                Log.e("logout", t.toString());
            }
        });
    }
}
