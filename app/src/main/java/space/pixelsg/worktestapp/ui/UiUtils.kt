package space.pixelsg.worktestapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope

object UiUtils {

    fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launchWhenStarted(block)
    }

    fun LifecycleOwner.repeatOnCreated(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launchWhenCreated(block)
    }

    fun LifecycleOwner.repeatOnResumed(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launchWhenResumed(block)
    }
}