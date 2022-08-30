package com.postingan.absenssiswasmkn1bantul.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.postingan.absenssiswasmkn1bantul.Adapter.DayAdapter;
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

        scheduleFragmentViewModel = new ViewModelProvider(this).get(ScheduleFragmentViewModel.class);
        scheduleFragmentViewModel.setContext(binding.getRoot().getContext());
        scheduleFragmentViewModel.scheduleRepositoryCreate();

        days = new ArrayList<>();
        days.add("Senin");
        days.add("Selasa");
        days.add("Rabu");
        days.add("Kamis");
        days.add("Jumat");

        binding.rvDay.setAdapter(new DayAdapter(binding.getRoot().getContext(), days, new DayAdapter.OnItemClickListener() {
            @Override
            public void onDetailClick(int position) {
                scheduleFragmentViewModel.getSchedule(days.get(position).toLowerCase());
            }
        }));

        binding.rvDay.setLayoutManager(new LinearLayoutManager(binding.rvDay.getContext(), RecyclerView.VERTICAL, false));


        return binding.getRoot();
    }
}