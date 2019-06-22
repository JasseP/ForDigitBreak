package ru.digitbreak.mobileapp.di;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.digitbreak.mobileapp.service.network.AuthInterceptor;
import ru.digitbreak.mobileapp.service.network.AuthRepo;
import ru.digitbreak.mobileapp.service.network.AuthState;
import ru.digitbreak.mobileapp.service.network.api.AuthApi;
import ru.digitbreak.mobileapp.service.network.api.UserApi;

@Module
public class NetworkModule {

    private final String SERVER_URL = "http://192.168.1.12:8888/";

    @Provides
    @AppScope
    AuthApi provideAuthApi() {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .client(new OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .addNetworkInterceptor(new StethoInterceptor())
                        .addInterceptor(new LoggingInterceptor.Builder()
                                .log(Platform.INFO)
                                .request("REQUEST")
                                .response("RESPONSE")
                                .build())
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        return retrofit.create(AuthApi.class);
    }

    @Provides
    @AppScope
    UserApi provideUserApi(AuthInterceptor authInterceptor, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(SERVER_URL + "/user-service/")
                .client(new OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .addInterceptor(authInterceptor)
                        .addNetworkInterceptor(new StethoInterceptor())
                        .addInterceptor(new LoggingInterceptor.Builder()
                                .log(Platform.INFO)
                                .request("REQUEST")
                                .response("RESPONSE")
                                .build())
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(UserApi.class);
    }

    @Provides
    @AppScope
    AuthState authState(AuthRepo authRepository) {
        return authRepository;
    }


    @Provides
    @AppScope
    Gson provideGson() {
        return new GsonBuilder()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                        return expose != null && !expose.serialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                        return expose != null && !expose.deserialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .create();
    }
}
