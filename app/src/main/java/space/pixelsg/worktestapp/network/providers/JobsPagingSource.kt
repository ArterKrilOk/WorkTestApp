package space.pixelsg.worktestapp.network.providers

import androidx.paging.PagingSource
import androidx.paging.PagingState
import space.pixelsg.worktestapp.network.models.Job
import java.lang.Exception

class JobsPagingSource(
    private val jobsProvider: JobsProvider
) : PagingSource<Int, Job>() {
    override fun getRefreshKey(state: PagingState<Int, Job>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Job> {
        return try {
            //To add pagination pass params.key to jobsProvider.listJobs()
            //and specify prevKey and nextKey in LoadResult.Page
            val results = jobsProvider.listJobs()
            LoadResult.Page(
                data = results,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}