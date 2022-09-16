package com.postingan.absenssiswasmkn1bantul.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.postingan.absenssiswasmkn1bantul.Model.Student;
import com.postingan.absenssiswasmkn1bantul.R;
import com.postingan.absenssiswasmkn1bantul.ViewModel.PasswordFragmentViewModel;
import com.postingan.absenssiswasmkn1bantul.ViewModel.PresentFragmentViewModel;
import com.postingan.absenssiswasmkn1bantul.databinding.FragmentPasswordBinding;
import com.postingan.absenssiswasmkn1bantul.databinding.FragmentPresentBinding;
import com.squareup.picasso.Picasso;

public class PasswordFragment extends Fragment {
    PasswordFragmentViewModel passwordFragmentViewModel;
    FragmentPasswordBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPasswordBinding.inflate(inflater, container, false);

        passwordFragmentViewModel = new ViewModelProvider(this).get(PasswordFragmentViewModel.class);

        passwordFragmentViewModel.detail().observe(getActivity(), new Observer<Student>() {
            @Override
            public void onChanged(Student student) {
                if (student.getProfilePic() != null) {
                    Picasso.get()
                            .load(binding.getRoot().getContext().getResources().getString(R.string.storageUrl) + student.getProfilePic())
                            .into(binding.imageStudentPass);
                }
            }
        });

        passwordFragmentViewModel.getChangePassword().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Toast.makeText(binding.getRoot().getContext(), "Password Berhasil Diubah", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(binding.getRoot().getContext(), "Password Gagal Diubah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnUpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSuccess = true;

                if (binding.inputOldPassword.getText().length() == 0){
                    binding.inputOldPassword.setError("Field Masih Kosong");
                    isSuccess = false;
                }
                if (binding.inputNewPasssword.getText().length() == 0){
                    binding.inputNewPasssword.setError("Field Masih Kosong");
                    isSuccess = false;
                }
                if (binding.inputConfirmPassword.getText().length() == 0){
                    binding.inputConfirmPassword.setError("Field Masih Kosong");
                    isSuccess = false;
                }
                if (!binding.inputNewPasssword.getText().toString().equals(binding.inputConfirmPassword.getText().toString())){
                    Toast.makeText(binding.getRoot().getContext(), "Konfirmasi Password Salah", Toast.LENGTH_SHORT).show();
                    isSuccess = false;
                }

                if (isSuccess){
                    passwordFragmentViewModel.changePassword(String.valueOf(binding.inputOldPassword.getText()), String.valueOf(binding.inputNewPasssword.getText()));
                }
            }
        });

        return binding.getRoot();
    }
}