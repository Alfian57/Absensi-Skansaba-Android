package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.GetScheduleResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;
import com.postingan.absenssiswasmkn1bantul.Model.Schedule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {
    private ApiRequest apiRequest;
    private User user;
    MutableLiveData<String> scheduleMutableLiveData;

    public ScheduleRepository(Application application) {
        this.apiRequest = ApiConfig.getClient(application).create(ApiRequest.class);
        this.user = new User(application);
        scheduleMutableLiveData = new MutableLiveData<>();
    }

    public void schedules(String day){
        StringBuilder message = new StringBuilder();

        Call<GetScheduleResponse> call = apiRequest.MySchedule(user.getToken(), Integer.valueOf(user.getId()), day);
        call.enqueue(new Callback<GetScheduleResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetScheduleResponse> call,@NonNull Response<GetScheduleResponse> response) {
                if (response.body() != null){
                    if (response.body().getData() != null){
                        for (Schedule schedule:response.body().getData().getSchedules()) {
                            message.append("Mapel\t\t: ").append(schedule.getSubjectName()).append("\n");
                            message.append("Mulai\t\t\t: ").append(schedule.getTimeStart()).append("\n");
                            message.append("Selesai\t: ").append(schedule.getTimeFinish()).append("\n\n");
                            scheduleMutableLiveData.postValue(message.toString());
                        }
                        if (response.body().getData().getSchedules().size() == 0){
                            message.append("Jadwal Masih Kosong");
                            scheduleMutableLiveData.postValue(message.toString());
                        }
                    } else {
                        scheduleMutableLiveData.postValue(null);
                    }
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

    public MutableLiveData<String> getSchedule(){
        return scheduleMutableLiveData;
    }
}
