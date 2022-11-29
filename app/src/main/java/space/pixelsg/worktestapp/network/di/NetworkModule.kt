package space.pixelsg.worktestapp.network.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.pixelsg.worktestapp.app.di.AppScope
import space.pixelsg.worktestapp.network.api.JobsApi
import javax.inject.Named

@Module
class NetworkModule {
    @Provides
    @AppScope
    @Named(JOBS_BASE_URL)
    fun provideJobsApiUrl(): String = "https://lifehack.studio/test_task/"

    @Provides
    @AppScope
    fun provideGson(): Gson = Gson()

    @Provides
    @AppScope
    fun provideRetrofit(
        @Named(JOBS_BASE_URL) url: String,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(url)
        .build()

    @Provides
    @AppScope
    fun provideJobsApi(retrofit: Retrofit): JobsApi = retrofit.create(JobsApi::class.java)


    companion object {
        const val JOBS_BASE_URL = "jobs-api-url"
    }
}