package space.pixelsg.worktestapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import space.pixelsg.worktestapp.app.App

open class BaseViewModel(app: App, vararg args: Any) : ViewModel() {
    protected val appComponent by lazy { app.appComponent }

    protected fun <T> Flow<T>.shareWhileSubscribed(timeout: Long = Long.MAX_VALUE) = this.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(replayExpirationMillis = timeout),
        1
    )
}