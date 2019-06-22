package ru.digitbreak.mobileapp.di;

import dagger.Component;
import ru.digitbreak.mobileapp.MainActivity;
import ru.digitbreak.mobileapp.ui.fragment.LoginFragment;
import ru.digitbreak.mobileapp.ui.main.PlaceholderFragment;
import ru.digitbreak.mobileapp.ui.fragment.PageViewFragment;

@AppScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(PlaceholderFragment placeholderFragment);

    void inject(PageViewFragment pageViewFragment);

    void inject(LoginFragment loginFragment);
}
