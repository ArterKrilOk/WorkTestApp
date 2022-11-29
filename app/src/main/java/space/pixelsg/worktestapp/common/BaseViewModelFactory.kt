package space.pixelsg.worktestapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import space.pixelsg.worktestapp.app.App

class BaseViewModelFactory(
    private val app: App,
    vararg args: Any
) : ViewModelProvider.Factory {

    private val arguments = args

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(App::class.java, Array<Any>::class.java).newInstance(app, arguments)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return modelClass.getConstructor(App::class.java, Array<Any>::class.java).newInstance(app, arguments)
    }
}