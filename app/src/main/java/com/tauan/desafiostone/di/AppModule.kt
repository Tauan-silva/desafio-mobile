package com.tauan.desafiostone.di

import android.app.Application
import com.google.gson.GsonBuilder
import com.tauan.desafiostone.common.Constants
import com.tauan.desafiostone.data.database.AppDatabase
import com.tauan.desafiostone.data.database.dao.CartDao
import com.tauan.desafiostone.data.database.dao.TransactionDao
import com.tauan.desafiostone.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient.Builder): Retrofit {
        val builder = GsonBuilder()
        builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
        val gson = builder.create()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideClientHttp(): OkHttpClient.Builder {
        val client = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        client.addInterceptor(interceptor)
        return client
    }

    @Provides
    @Singleton
    fun provideDatabaseInstance(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)!!
    }

    @Provides
    @Singleton
    fun provideCartDao(appDatabase: AppDatabase): CartDao {
        return appDatabase.cartDao()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: AppDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }
}