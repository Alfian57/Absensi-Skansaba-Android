package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.PresentResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentRepository {
    private ApiRequest apiRequest;
    private User user;
    MutableLiveData<PresentResponse> presentMutableLiveData;

    public PresentRepository(Application application) {
        this.apiRequest = ApiConfig.getClient(application).create(ApiRequest.class);
        this.user = new User(application);
        presentMutableLiveData = new MutableLiveData<>();
    }

    public void present(String key){
        Call<PresentResponse> call = apiRequest.Absen(user.getToken(), key);
        call.enqueue(new Callback<PresentResponse>() {
            @Override
            public void onResponse(@NonNull Call<PresentResponse> call,@NonNull Response<PresentResponse> response) {
                if (response.body() != null){
                    presentMutableLiveData.postValue(response.body());
                } else {
                    presentMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PresentResponse> call,@NonNull Throwable t) {
                Log.e("Present", t.toString());
                presentMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<PresentResponse> getPresent(){
        return presentMutableLiveData;
    }
}
