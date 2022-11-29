package space.pixelsg.worktestapp.ui.summary

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import space.pixelsg.worktestapp.app.App
import space.pixelsg.worktestapp.common.BaseViewModel

class SummaryViewModel(app: App, vararg args: Any) : BaseViewModel(app, args) {
    private val jobID = args[0] as Int

    val state = MutableStateFlow(LoadState.LOADING)

    init {
        if (jobID == EMPTY_JOB_ID) viewModelScope.launch {
            state.emit(LoadState.ERROR)
        }
    }

    val jobSummary = appComponent.jobsProvider
        .getJobSummaryFlow(jobID)
        .onEach {
            state.emit(LoadState.LOADED)
        }
        .catch {
            state.emit(LoadState.ERROR)
        }
        .shareWhileSubscribed()

    enum class LoadState {
        LOADING,
        LOADED,
        ERROR
    }

    companion object {
        const val EMPTY_JOB_ID = -1
    }
}