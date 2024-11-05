package uz.apexsoft.presentation

import android.app.Application
import uz.apexsoft.presentation.di.ApplicationComponent
import uz.apexsoft.presentation.di.ApplicationModule
import uz.apexsoft.presentation.di.DaggerApplicationComponent

class App : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(context = this))
            .build()
    }
}