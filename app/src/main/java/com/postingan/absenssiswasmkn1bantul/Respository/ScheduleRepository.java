package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.GetScheduleResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;
import com.postingan.absenssiswasmkn1bantul.Model.Schedule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {
    private Context context;
    private ApiRequest apiRequest;
    private User user;

    public ScheduleRepository(Context context) {
        this.context = context;
        this.apiRequest = ApiConfig.getClient(context).create(ApiRequest.class);
        this.user = new User(context);
    }

    public void showSchedules(String day){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Jadwal Hari " + day);

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
                        }
                        if (response.body().getData().getSchedules().size() == 0){
                            message.append("Jadwal Masih Kosong");
                        }
                    }
                } else {
                    Toast.makeText(context, "Gagal Menampilkan Jadwal", Toast.LENGTH_SHORT).show();
                }

                builder.setMessage(String.valueOf(message));
                builder.setCancelable(true);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }

            @Override
            public void onFailure(@NonNull Call<GetScheduleResponse> call,@NonNull Throwable t) {
                Log.e("schedule", t.toString());
                Toast.makeText(context, "Terjadi Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
