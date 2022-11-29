package space.pixelsg.worktestapp.app.di

import dagger.Component
import space.pixelsg.worktestapp.network.di.NetworkModule
import space.pixelsg.worktestapp.network.providers.JobsProvider

@AppScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {
    val jobsProvider: JobsProvider
}