package ru.digitbreak.mobileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import ru.digitbreak.mobileapp.service.SharedPreferenceService;
import ru.digitbreak.mobileapp.ui.fragment.LoginFragment;
import ru.digitbreak.mobileapp.ui.fragment.PageViewFragment;

public class MainActivity extends AppCompatActivity {
    @Inject
    SharedPreferenceService sharedPreferenceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        setContentView(R.layout.activity_main);
        if(!sharedPreferenceService.getSavedValue("token","").equals(""))
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, PageViewFragment.newInstance())
                    .commitNow();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, LoginFragment.newInstance(() ->
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, PageViewFragment.newInstance()).commitNow()))
                    .commitNow();
    }
}