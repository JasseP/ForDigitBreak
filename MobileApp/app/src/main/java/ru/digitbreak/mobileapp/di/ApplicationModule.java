package ru.digitbreak.mobileapp.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.digitbreak.mobileapp.App;
import ru.digitbreak.mobileapp.service.SharedPreferenceService;

@Module(includes = {
        NetworkModule.class
})
public class ApplicationModule {

    protected final App app;

    public ApplicationModule(App application) {app = application;}

    @Provides
    @AppScope
    App provideApplication() {return app;}

    @Provides
    @AppScope
    Context provideContext() {return app;}

    @Provides
    @AppScope
    SharedPreferenceService sharedPreferenceService(Context context) {return new SharedPreferenceService(context);}

}
