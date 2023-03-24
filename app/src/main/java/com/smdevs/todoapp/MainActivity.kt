package com.smdevs.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Manual SplashScreen
//        setTheme(R.style.Theme_TodoApp)
        //SplashScreen Api
        installSplashScreen()
        setContentView(R.layout.activity_main)
    }
}