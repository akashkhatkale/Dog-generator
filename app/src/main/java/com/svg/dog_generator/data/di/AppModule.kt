package com.svg.dog_generator.data.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.svg.dog_generator.BuildConfig
import com.svg.dog_generator.data.api.DogAPI
import com.svg.dog_generator.data.local.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : DogAPI =
        retrofit.create(DogAPI::class.java)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context : Context): RequestManager =
        Glide.with(context)


    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DogDatabase::class.java,
        "dog_db"
    ).build()


    @Singleton
    @Provides
    fun provideYourDao(db: DogDatabase) = db.getDogDao()

}