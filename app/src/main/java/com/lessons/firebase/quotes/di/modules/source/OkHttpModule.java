package com.lessons.firebase.quotes.di.modules.source;

import android.content.Context;
import android.util.Log;

import com.lessons.firebase.quotes.di.modules.AppModule;
import com.lessons.firebase.quotes.di.scopes.AppScope;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.lessons.firebase.quotes.utils.Constants.FAVQS_API_KEY;
import static com.lessons.firebase.quotes.utils.Constants.TAG_HTTP;

@AppScope
@Module(includes = {AppModule.class})
public class OkHttpModule {

    @AppScope
    @Named("http")
    @Provides
    public OkHttpClient okHttpClientPhoto(Cache cache, @Named("http_interceptor")HttpLoggingInterceptor httpInterceptor){
        return new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addInterceptor(httpInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    @AppScope
    @Named("token")
    @Provides
    public OkHttpClient okHttpClientQuote(Cache cache,  @Named("token_interceptor") Interceptor interceptor){
        return new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @AppScope
    @Provides
    public Cache cache(File file){
        int size = 10* 1000 * 1000; //10mb
        return new Cache(file, size);
    }

    @AppScope
    @Provides
    public File file( Context context){
        File file = new File(context.getCacheDir(), "httpCache");
        file.mkdirs();
        return file;
    }

    @AppScope
    @Named("http_interceptor")
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG_HTTP, " HttpLoggingInterceptor " + message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


    @AppScope
    @Named("token_interceptor")
    @Provides
    public Interceptor tokenInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "Token " + FAVQS_API_KEY)
                        .build();
                return chain.proceed(request);
            }
        };
        return interceptor;
    }


}
