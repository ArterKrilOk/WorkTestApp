package space.pixelsg.worktestapp.common

import androidx.appcompat.app.AppCompatActivity
import space.pixelsg.worktestapp.app.App

abstract class BaseActivity<VM: BaseViewModel> : AppCompatActivity() {
    protected val app by lazy { application as App }
    protected val viewModel: VM by lazy { provideViewModel() }

    abstract fun provideViewModel(): VM
}