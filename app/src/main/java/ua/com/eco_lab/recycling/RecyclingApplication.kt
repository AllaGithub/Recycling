package ua.com.eco_lab.recycling

import android.app.Application
import com.facebook.stetho.Stetho

class RecyclingApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}