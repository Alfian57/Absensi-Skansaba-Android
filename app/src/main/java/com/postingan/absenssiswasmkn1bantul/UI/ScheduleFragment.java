package com.postingan.absenssiswasmkn1bantul.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.postingan.absenssiswasmkn1bantul.Adapter.DayAdapter;
import com.postingan.absenssiswasmkn1bantul.Api.Response.GetScheduleResponse;
import com.postingan.absenssiswasmkn1bantul.Model.Schedule;
import com.postingan.absenssiswasmkn1bantul.ViewModel.ScheduleFragmentViewModel;
import com.postingan.absenssiswasmkn1bantul.databinding.FragmentScheduleBinding;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {
    FragmentScheduleBinding binding;
    ScheduleFragmentViewModel scheduleFragmentViewModel;
    List<String> days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScheduleBinding.inflate(inflater, container, false);

        days = new ArrayList<>();
        days.add("Senin");
        days.add("Selasa");
        days.add("Rabu");
        days.add("Kamis");
        days.add("Jumat");

        scheduleFragmentViewModel = new ViewModelProvider(this).get(ScheduleFragmentViewModel.class);

        scheduleFragmentViewModel.getSchedule().observe(getActivity(), new Observer<GetScheduleResponse>() {
            @Override
            public void onChanged(GetScheduleResponse getScheduleResponse) {
                StringBuilder message = new StringBuilder();

                if (getScheduleResponse != null){
                    if (getScheduleResponse.getData() != null) {
                        if (getScheduleResponse.getData().getSchedules() != null) {
                            for (Schedule schedule:getScheduleResponse.getData().getSchedules()) {
                                message.append("Mapel\t\t: ").append(schedule.getSubjectName()).append("\n");
                                message.append("Mulai\t\t\t: ").append(schedule.getTimeStart()).append("\n");
                                message.append("Selesai\t: ").append(schedule.getTimeFinish()).append("\n\n");
                            }
                            if (getScheduleResponse.getData().getSchedules().size() == 0){
                                message.append("Jadwal Masih Kosong");
                            }

                            AlertDialog.Builder builder = new AlertDialog.Builder(binding.getRoot().getContext());
                            builder.setTitle("Jadwal");
                            builder.setMessage(message);
                            builder.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }

                    if (getScheduleResponse.getMessage() != null){
                        Toast.makeText(binding.getRoot().getContext(), getScheduleResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(binding.getRoot().getContext(), "Gagal Menampilkan Jadwal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(binding.getRoot().getContext(), "Gagal Menampilkan Jadwal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.rvDay.setAdapter(new DayAdapter(binding.getRoot().getContext(), days, new DayAdapter.OnItemClickListener() {
            @Override
            public void onDetailClick(int position) {
                scheduleFragmentViewModel.schedule(days.get(position).toLowerCase());
            }
        }));

        binding.rvDay.setLayoutManager(new LinearLayoutManager(binding.rvDay.getContext(), RecyclerView.VERTICAL, false));

        return binding.getRoot();
    }
}