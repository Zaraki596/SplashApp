package com.example.splashapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.splashapp.databinding.ActivityMainBinding
import com.example.splashapp.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}