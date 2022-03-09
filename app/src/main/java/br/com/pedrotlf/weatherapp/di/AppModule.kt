package br.com.pedrotlf.weatherapp.di

import br.com.pedrotlf.weatherapp.Constants
import br.com.pedrotlf.weatherapp.data.remote.MetaWeatherApi
import br.com.pedrotlf.weatherapp.data.repository.WeatherRepositoryImpl
import br.com.pedrotlf.weatherapp.domain.repository.WeatherRepository
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMetaWeatherApi(): MetaWeatherApi{
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS })
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(
                jacksonObjectMapper()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            )).client(client)
            .build()
            .create(MetaWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: MetaWeatherApi): WeatherRepository = WeatherRepositoryImpl(api)
}