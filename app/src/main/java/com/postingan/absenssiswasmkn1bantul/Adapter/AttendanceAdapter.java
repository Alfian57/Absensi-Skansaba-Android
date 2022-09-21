package com.postingan.absenssiswasmkn1bantul.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.postingan.absenssiswasmkn1bantul.Model.Attendance;
import com.postingan.absenssiswasmkn1bantul.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AttendanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Attendance> data;

    public AttendanceAdapter(List<Attendance> data){
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_row, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AttendanceViewHolder){
            AttendanceViewHolder attendanceViewHolder = (AttendanceViewHolder) holder;

            attendanceViewHolder.txtPresentDate.setText(changeDateFormat(
                    "yyyy-MM-dd",
                    "dd MMMM yyyy",
                    data.get(position).getPresentDate()
            ));
            attendanceViewHolder.txtDesc.setText(data.get(position).getDesc());
            attendanceViewHolder.txtPresentTime.setText((data.get(position).getPresentTime()));

            if (data.get(position).getDesc().equals("alpha")){
                attendanceViewHolder.txtDesc.setTextColor(Color.RED);
            } else if (data.get(position).getDesc().equals("masuk")) {
                attendanceViewHolder.txtDesc.setTextColor(Color.BLUE);
            } else if (data.get(position).getDesc().equals("sakit") || data.get(position).getDesc().equals("ijin")) {
                attendanceViewHolder.txtDesc.setTextColor(Color.YELLOW);
            }
        }
    }

    private String changeDateFormat(String currentFormat,String requiredFormat,String dateString){
        String result="";
        if (dateString.equals("")){
            return result;
        }
        SimpleDateFormat formatterOld = new SimpleDateFormat(currentFormat, Locale.getDefault());
        SimpleDateFormat formatterNew = new SimpleDateFormat(requiredFormat, Locale.getDefault());
        Date date= null;
        try {
            date = formatterOld.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            result = formatterNew.format(date);
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView txtPresentDate, txtDesc, txtPresentTime;

        public AttendanceViewHolder(View view) {
            super(view);

            txtPresentDate = view.findViewById(R.id.txtPresentDate);
            txtPresentTime = view.findViewById(R.id.txtPresentTime);
            txtDesc = view.findViewById(R.id.txtDesc);
        }
    }
}
