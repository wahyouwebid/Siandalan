package id.siandalan.app

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SiandalanApp: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
        application = this
    }
    companion object {
        lateinit var application: SiandalanApp
        fun getContext(): Context {
            return application.applicationContext
        }
    }
}