package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.GetScheduleResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {
    private final ApiRequest apiRequest;
    User user;
    MutableLiveData<GetScheduleResponse> scheduleMutableLiveData;

    public ScheduleRepository(Application application) {
        this.apiRequest = ApiConfig.getClient(application).create(ApiRequest.class);
        user = new User(application);
        scheduleMutableLiveData = new MutableLiveData<>();
    }

    public void schedules(String day){
        Call<GetScheduleResponse> call = apiRequest.MySchedule(user.getToken(), day);
        call.enqueue(new Callback<GetScheduleResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetScheduleResponse> call,@NonNull Response<GetScheduleResponse> response) {
                if (response.body() != null){
                    scheduleMutableLiveData.postValue(response.body());
                } else {
                    scheduleMutableLiveData.postValue(null);
                }

            }

            @Override
            public void onFailure(@NonNull Call<GetScheduleResponse> call,@NonNull Throwable t) {
                Log.e("schedule", t.toString());
                scheduleMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<GetScheduleResponse> getSchedule(){
        return scheduleMutableLiveData;
    }
}
