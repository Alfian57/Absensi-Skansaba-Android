package com.postingan.absenssiswasmkn1bantul.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.postingan.absenssiswasmkn1bantul.Adapter.AttendanceAdapter;
import com.postingan.absenssiswasmkn1bantul.Api.Response.GetAttendanceResponse;
import com.postingan.absenssiswasmkn1bantul.Model.Attendance;
import com.postingan.absenssiswasmkn1bantul.ViewModel.AttendanceActivityViewModel;
import com.postingan.absenssiswasmkn1bantul.databinding.ActivityAttendanceBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceActivity extends AppCompatActivity {
    ActivityAttendanceBinding binding;
    AttendanceActivityViewModel attendanceActivityViewModel;
    DatePickerFragment dp;
    Integer currentMonth;
    Integer currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DateFormat month = new SimpleDateFormat("MM");
        DateFormat year = new SimpleDateFormat("yyyy");
        Date date = new Date();

        currentMonth = Integer.parseInt(month.format(date));
        currentYear = Integer.parseInt(year.format(date));

        dp = new DatePickerFragment();
        dp.setMonthValue(currentMonth);
        dp.setYearValue(currentYear);

        attendanceActivityViewModel = new ViewModelProvider(this).get(AttendanceActivityViewModel.class);

        attendanceActivityViewModel.getMyAttendance(currentMonth, currentYear);

        attendanceActivityViewModel.getMyAttendance().observe(this, new Observer<GetAttendanceResponse>() {
            @Override
            public void onChanged(GetAttendanceResponse getAttendanceResponse) {
                if (getAttendanceResponse != null){
                    if (getAttendanceResponse.getData().size() != 0){
                        binding.txtAttendanceNull.setVisibility(View.GONE);
                    } else {
                        binding.txtAttendanceNull.setVisibility(View.VISIBLE);
                    }
                    binding.rvAttendance.setAdapter(new AttendanceAdapter(getAttendanceResponse.getData()));
                    binding.rvAttendance.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this, RecyclerView.VERTICAL, false));
                } else {
                    Toast.makeText(AttendanceActivity.this, "Gagal Mendapatkan Rekap", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        attendanceActivityViewModel.getMyAttendance(month, year);
                        dp.setMonthValue(month);
                        dp.setYearValue(year);
                    }
                });
                dp.show(getSupportFragmentManager(), "MonthYearPickerDialog");
            }
        });
    }
}