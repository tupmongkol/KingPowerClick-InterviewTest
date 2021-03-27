package com.kingpowerclick.interviewtest

import android.app.Application
import com.androidnetworking.AndroidNetworking

class InterviewApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }
}