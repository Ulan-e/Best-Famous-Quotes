package com.ulan.app.quotes.di.modules.source;

import android.content.Context;
import android.util.Log;

import com.ulan.app.quotes.di.modules.AppModule;
import com.ulan.app.quotes.di.qualifires.HttpInterceptor;
import com.ulan.app.quotes.di.qualifires.PhotosOkHttp;
import com.ulan.app.quotes.di.qualifires.TokenInterceptor;
import com.ulan.app.quotes.di.scopes.AppScope;
import com.ulan.app.quotes.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.ulan.app.quotes.utils.Constants.FAVQS_API_KEY;
import static com.ulan.app.quotes.utils.Constants.TAG_HTTP;

@AppScope
@Module(includes = {AppModule.class})
public class OkHttpModule {

    @AppScope
    @PhotosOkHttp
    @Provides
    public OkHttpClient okHttpClientPhoto(Cache cache, @HttpInterceptor HttpLoggingInterceptor httpInterceptor){
        return new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addInterceptor(httpInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    @AppScope
    @TokenInterceptor
    @Provides
    public OkHttpClient okHttpClientQuote(Cache cache,  @TokenInterceptor Interceptor interceptor){
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
        File file = new File(context.getCacheDir(), Constants.CACHE_FILES);
        file.mkdirs();
        return file;
    }

    @AppScope
    @HttpInterceptor
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
    @TokenInterceptor
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
