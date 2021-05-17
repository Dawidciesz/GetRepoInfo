package com.fiesta.detector.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.fiesta.getrepoinfo.api.RepositoryApi
import com.fiesta.getrepoinfo.data.RepoData
import com.fiesta.getrepoinfo.data.RepoDataDatabase
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
    fun provideRepositorytApi(retrofit: Retrofit): RepositoryApi =
        retrofit.create(RepositoryApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : RepoDataDatabase =
        Room.databaseBuilder(app, RepoDataDatabase::class.java, "repo_database")
            .build()

    @Provides
    fun providePoiDao(db: RepoDataDatabase) = db.repoDataDao()

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}


