package co.fullstacklabs.androidkotlinchallenge

import androidx.multidex.MultiDexApplication
import co.fullstacklabs.androidkotlinchallenge.di.networkModule
import co.fullstacklabs.androidkotlinchallenge.di.repositoryModule
import co.fullstacklabs.androidkotlinchallenge.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(
                viewModule,
                networkModule,
                repositoryModule
            )
        }
    }
}