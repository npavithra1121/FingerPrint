package com.example.fingerprint.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fingerprint.R
import com.example.fingerprint.fragment.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.container,LoginFragment()).commitNow()
        }
    }
}