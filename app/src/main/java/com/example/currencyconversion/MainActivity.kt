package com.example.currencyconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyconversion.fragments.FirstFragment
import com.example.currencyconversion.fragments.SecondFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolder, FirstFragment())
            .commit()
    }

    fun goToSecondFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolder, SecondFragment())
            .commit()
    }
}