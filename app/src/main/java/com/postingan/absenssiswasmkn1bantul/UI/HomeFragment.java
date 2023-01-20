package com.postingan.absenssiswasmkn1bantul.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.postingan.absenssiswasmkn1bantul.Api.Response.LoginDetailResponse;
import com.postingan.absenssiswasmkn1bantul.Model.Student;
import com.postingan.absenssiswasmkn1bantul.R;
import com.postingan.absenssiswasmkn1bantul.ViewModel.HomeFragmentViewModel;
import com.postingan.absenssiswasmkn1bantul.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeFragmentViewModel homeFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        homeFragmentViewModel.userDetail().observe(getActivity(), new Observer<LoginDetailResponse>() {
            @Override
            public void onChanged(LoginDetailResponse loginDetailResponse) {
                if (loginDetailResponse != null){
                    if (loginDetailResponse.getData() != null){
                        Student student = loginDetailResponse.getData().getStudent();
                        binding.textNama.setText(student.getName());
                        binding.textNisn.setText(student.getNisn());
                        binding.textNis.setText(student.getNis());
                        binding.textTanggalLahir.setText(student.getDateOfBirth());
                        binding.textKelas.setText(student.getGrade());
                        binding.textAlamat.setText(student.getAddress());

                        if (student.getGender().equals("0")){
                            binding.textGender.setText("Laki-laki");
                        } else {
                            binding.textGender.setText("Perempuan");
                        }

                        if (student.getProfilePic() != null) {
                            Picasso.get()
                                    .load(getResources().getString(R.string.storageUrl) + student.getProfilePic())
                                    .into(binding.imageStudent);
                        }
                    }
                }
            }
        });

        binding.btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AttendanceHistoryActivity.class));
            }
        });

        return binding.getRoot();
    }

}