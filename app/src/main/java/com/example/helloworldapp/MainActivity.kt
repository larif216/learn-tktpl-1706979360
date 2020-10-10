package com.example.helloworldapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworldapp.fragments.HeroListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, HeroListFragment())
                .commit()
    }
}