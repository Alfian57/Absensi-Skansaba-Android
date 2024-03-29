package com.postingan.absenssiswasmkn1bantul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.postingan.absenssiswasmkn1bantul.Api.Response.LogoutResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;
import com.postingan.absenssiswasmkn1bantul.UI.HomeFragment;
import com.postingan.absenssiswasmkn1bantul.UI.LoginActivity;
import com.postingan.absenssiswasmkn1bantul.UI.ChangePasswordFragment;
import com.postingan.absenssiswasmkn1bantul.UI.AttendanceFragment;
import com.postingan.absenssiswasmkn1bantul.UI.ScheduleFragment;
import com.postingan.absenssiswasmkn1bantul.ViewModel.MainActivityViewModel;
import com.postingan.absenssiswasmkn1bantul.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainActivityViewModel mainActivityViewModel;
    User user;
    FragmentManager fragmentManager;
    Fragment fragment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        user = new User(MainActivity.this);

        mainActivityViewModel.getLogout().observe(this, new Observer<LogoutResponse>() {
            @Override
            public void onChanged(LogoutResponse logoutResponse) {
                if (logoutResponse != null){
                    if (logoutResponse.getMessage() != null){
                        user.setNullToken();
                        user.setId(null);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        Toast.makeText(MainActivity.this, logoutResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id){
                case R.id.home_bottom_nav:
                    fragment = new HomeFragment();
                    break;
                case R.id.present_bottom_nav:
                    fragment = new AttendanceFragment();
                    break;
                case R.id.password_bottom_nav:
                    fragment = new ChangePasswordFragment();
                    break;
                case R.id.schedule_bottom_nav:
                    fragment = new ScheduleFragment();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment).commit();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout_toolbar_nav) {
            mainActivityViewModel.logout(user.getToken());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}