package space.pixelsg.worktestapp.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import space.pixelsg.worktestapp.network.models.Job
import space.pixelsg.worktestapp.network.models.JobSummary

interface JobsApi {
    @GET("test.php")
    suspend fun getJobsList(): List<Job>

    @GET("test.php")
    suspend fun getJobSummary(@Query("id") id: Int): List<JobSummary>
}