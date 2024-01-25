package com.tekehome.takehome.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tekehome.takehome.databinding.ActivityTakeHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TakeHomeActivity : AppCompatActivity() {
    private val _binding: ActivityTakeHomeBinding by lazy {
        ActivityTakeHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
    }
}
