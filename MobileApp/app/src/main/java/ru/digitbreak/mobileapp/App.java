package ru.digitbreak.mobileapp;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.stetho.Stetho;

import ru.digitbreak.mobileapp.di.ApplicationComponent;
import ru.digitbreak.mobileapp.di.ApplicationModule;
import ru.digitbreak.mobileapp.di.DaggerApplicationComponent;

public class App extends Application {

    protected static App instance;

    private ApplicationComponent component = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();

    public static ApplicationComponent getComponent() {
        return instance.component;
    }

    public static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        configure();
        instance = this;
    }

    private void configure() {
        Stetho.initializeWithDefaults(this);
    }
}
