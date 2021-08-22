package com.tyhoo.mvvm.java.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.tyhoo.mvvm.java.R;
import com.tyhoo.mvvm.java.util.ActivityUtils;
import com.tyhoo.mvvm.java.util.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = findOrCreateViewFragment();

        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                mainFragment, R.id.content_frame);
    }

    @NonNull
    private MainFragment findOrCreateViewFragment() {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
        }

        return mainFragment;
    }

    public static MainViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance();

        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }
}