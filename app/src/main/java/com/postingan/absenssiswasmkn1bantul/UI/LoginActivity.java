package com.postingan.absenssiswasmkn1bantul.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.postingan.absenssiswasmkn1bantul.Api.Response.LoginDetailResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;
import com.postingan.absenssiswasmkn1bantul.MainActivity;
import com.postingan.absenssiswasmkn1bantul.R;
import com.postingan.absenssiswasmkn1bantul.ViewModel.LoginActivityViewModel;
import com.postingan.absenssiswasmkn1bantul.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    User user;
    LoginActivityViewModel loginActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        user = new User(LoginActivity.this);

        loginActivityViewModel.getlogin().observe(this, new Observer<LoginDetailResponse>() {
            @Override
            public void onChanged(LoginDetailResponse loginDetailResponse) {
                if (loginDetailResponse != null){
                    if (loginDetailResponse.getData() != null){
                        if (loginDetailResponse.getData().getAccessToken() != null){
                            user.setToken(loginDetailResponse.getData().getAccessToken());
                            user.setId(loginDetailResponse.getData().getStudent().getId());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Gagal Melakukan Login", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Gagal Melakukan Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                    loginActivityViewModel.login(
                            String.valueOf(binding.inputLoginNis.getText()),
                            String.valueOf(binding.inputLoginPass.getText())
                    );
                }
            }
        });
    }
}