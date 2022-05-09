package com.example.ourside

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ourside.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    private val binding: ActivityScoreBinding by lazy{
        ActivityScoreBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.ScoreTextView.text=intent.getStringExtra("data")
        binding.startPageButton.setOnClickListener {
            val intent = Intent(this@ScoreActivity, MainActivity::class.java)
            startActivity(intent)

        }

    }
}