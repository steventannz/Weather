package steven.tan.weather.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import steven.tan.weather.R;

/**
 * Created by steventan on 26/07/17.
 */
@Module
public class RetrofitModule {

    private static final String APP_ID_QUERY_PARARM = "APPID";

    @Provides
    @Singleton
    static Retrofit provideRetrofit(@Named("base_url") String baseUrl, OkHttpClient client,
                                    Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    static Interceptor provideInterceptor(@Named("api_key") final String apiKey) {
        return chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter(APP_ID_QUERY_PARARM, apiKey)
                    .build();

            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    @Provides
    @Singleton
    static Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    static Gson provideGson(JsonDeserializer<Date> deserializer) {
        return new GsonBuilder().registerTypeAdapter(Date.class, deserializer).create();
    }

    @Provides
    @Singleton
    static JsonDeserializer<Date> provideDateDeserializer() {
        return (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
    }

    @Provides
    @Singleton
    static CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Named("base_url")
    @Singleton
    static String provideBaseUrl(Context context) {
        return context.getString(R.string.base_url);
    }

    @Provides
    @Named("api_key")
    @Singleton
    static String provideApiKey(Context context) {
        return context.getString(R.string.api_key);
    }
}
