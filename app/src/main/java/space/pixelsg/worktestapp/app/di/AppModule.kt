package space.pixelsg.worktestapp.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule(private val context: Context) {
    @Provides
    @AppScope
    @Named("app-context")
    fun provideAppContext(): Context = context
}