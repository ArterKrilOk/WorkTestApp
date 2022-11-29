package space.pixelsg.worktestapp.ui.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import space.pixelsg.worktestapp.app.App
import space.pixelsg.worktestapp.common.BaseViewModel
import space.pixelsg.worktestapp.network.providers.JobsPagingSource
import space.pixelsg.worktestapp.ui.list.adapter.Item

class ListViewModel(app: App, vararg args: Any) : BaseViewModel(app, args) {
    val pagingItems = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
    ) {
        JobsPagingSource(appComponent.jobsProvider)
    }.flow.cachedIn(viewModelScope).map { pagingData ->
        pagingData.map {
            Item(
                id = it.id,
                name = it.name,
                imageUrl = it.img
            )
        }
    }.shareWhileSubscribed()
}