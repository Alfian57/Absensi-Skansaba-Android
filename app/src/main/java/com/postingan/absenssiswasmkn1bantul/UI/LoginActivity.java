package com.postingan.absenssiswasmkn1bantul.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.postingan.absenssiswasmkn1bantul.R;
import com.postingan.absenssiswasmkn1bantul.ViewModel.LoginActivityViewModel;
import com.postingan.absenssiswasmkn1bantul.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    LoginActivityViewModel loginActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEmpty = false;

                if (binding.inputLoginNis.getText().length() == 0){
                    binding.inputLoginNis.setError("Field Masih Kosong");
                    isEmpty = true;
                }
                if (binding.inputLoginPass.getText().length() == 0){
                    binding.inputLoginPass.setError("Field Masih Kosong");
                    isEmpty = true;
                }
                if (!isEmpty){
                    loginActivityViewModel.login(String.valueOf(binding.inputLoginNis.getText()), String.valueOf(binding.inputLoginPass.getText()));
                }
            }
        });
    }
}