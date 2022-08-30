package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.PresentResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentRepository {
    private Context context;
    private ApiRequest apiRequest;
    private User user;

    public PresentRepository(Application application) {
        this.context = application.getApplicationContext();
        this.apiRequest = ApiConfig.getClient(context).create(ApiRequest.class);
        this.user = new User(context);
    }

    public void present(String key){
        Call<PresentResponse> call = apiRequest.Absen(user.getToken(), user.getId(), key);
        call.enqueue(new Callback<PresentResponse>() {
            @Override
            public void onResponse(@NonNull Call<PresentResponse> call,@NonNull Response<PresentResponse> response) {
                if (response.body() != null){
                    if(response.body().getMessage() != null){
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Gagal Melakukan Absen", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Gagal Melakukan Absen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PresentResponse> call,@NonNull Throwable t) {
                Log.e("Present", t.toString());
                Toast.makeText(context, "Terjadi Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
