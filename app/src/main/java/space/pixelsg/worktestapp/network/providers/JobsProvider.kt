package space.pixelsg.worktestapp.network.providers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import space.pixelsg.worktestapp.app.di.AppScope
import space.pixelsg.worktestapp.network.api.JobsApi
import space.pixelsg.worktestapp.network.di.NetworkModule
import space.pixelsg.worktestapp.network.models.JobSummary
import javax.inject.Inject
import javax.inject.Named

@AppScope
class JobsProvider @Inject constructor(
    private val api: JobsApi,
    @Named(NetworkModule.JOBS_BASE_URL) private val baseUrl: String
) {
    /**
     * Returns remote lists of Jobs
     *
     * @param page is currently unused, added for future improvements
     * @param limit is currently unused, added for future improvements
     *
     * @return list of Jobs
     */
    suspend fun listJobs(page: Int = 1, limit: Int = 10) = api.getJobsList().map {
        it.apply {
            img = baseUrl + img
        }
    }


    /**
     * Returns job`s summary by id
     *
     * @param id job id
     *
     * @return job summary flow
     */
    fun getJobSummaryFlow(id: Int): Flow<JobSummary> = flow {
        val summary = api.getJobSummary(id).first()
        summary.img = baseUrl + summary.img
        emit(summary)
    }.flowOn(Dispatchers.IO)
}