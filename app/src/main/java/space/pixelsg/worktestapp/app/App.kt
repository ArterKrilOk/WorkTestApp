package space.pixelsg.worktestapp.app

import android.app.Application
import com.google.android.material.color.DynamicColors
import space.pixelsg.worktestapp.app.di.AppComponent
import space.pixelsg.worktestapp.app.di.AppModule
import space.pixelsg.worktestapp.app.di.DaggerAppComponent
import space.pixelsg.worktestapp.network.di.NetworkModule

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}