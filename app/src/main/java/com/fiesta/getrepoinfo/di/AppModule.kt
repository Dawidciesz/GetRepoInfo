package com.fiesta.detector.di

import android.app.Application
import androidx.room.Room
import com.fiesta.getrepoinfo.api.RepositoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RepositoryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RepositoryApi =
        retrofit.create(RepositoryApi::class.java)
}

//
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        app: Application,
//        callback: PoiDatabase.Callback
//    ) = Room.databaseBuilder(app, PoiDatabase::class.java, "poi_database")
//        .fallbackToDestructiveMigration()
//        .addCallback(callback)
//        .build()
//    @Provides
//    fun providePoiDao(db: PoiDatabase) = db.poiDao()
//
//    @ApplicationScope
//    @Provides
//    @Singleton
//    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
//


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope