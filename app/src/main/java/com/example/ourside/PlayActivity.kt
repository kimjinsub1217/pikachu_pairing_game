package com.example.ourside

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourside.databinding.ActivityMainBinding
import com.example.ourside.databinding.ActivityPlayBinding
import kotlin.concurrent.thread

class PlayActivity : AppCompatActivity() {
    val PokemonList2 = listOf(
        Pokemon(R.drawable.pikachu1),
        Pokemon(R.drawable.pikachu3)
    )
    private val binding: ActivityPlayBinding by lazy {
        ActivityPlayBinding.inflate(layoutInflater)
    }

    private val myRecyclerViewAdapter: Adapter by lazy {
        Adapter()
    }
    private lateinit var mPlayer: MediaPlayer
    var soundPool: SoundPool? = null
    var soundID = 0
    var timeTotal = 60
    var started = false
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mPlayer = MediaPlayer.create(this, R.raw.bgm)
        mPlayer.start()
        mPlayer.isLooping = true

        soundPool = SoundPool(5, AudioManager.STREAM_MUSIC, 0) //작성
        soundID = soundPool!!.load(this, R.raw.pika, 1)
        start()
        score()




        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@PlayActivity, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
            adapter = myRecyclerViewAdapter


        }
        binding.recyclerView.itemAnimator = null

        binding.leftButton.setOnClickListener {
            soundPool!!.play(soundID, 1f, 1f, 0, 0, 1f)
            if (myRecyclerViewAdapter.currentList[myRecyclerViewAdapter.currentList.size - 1] == PokemonList2[0]) {

                score += 10
                myRecyclerViewAdapter.add()
            } else {
                Toast.makeText(this, "틀렸어요", Toast.LENGTH_SHORT).show()
                score -= 5
            }


        }

        binding.rightButton.setOnClickListener {
            soundPool!!.play(soundID, 1f, 1f, 0, 0, 1f)
            if (myRecyclerViewAdapter.currentList[myRecyclerViewAdapter.currentList.size - 1] == PokemonList2[1]) {
                myRecyclerViewAdapter.add()
                started = true
                score += 10

            } else {
                Toast.makeText(this, "틀렸어요", Toast.LENGTH_SHORT).show()
                score -= 5
            }

        }



        myRecyclerViewAdapter.submitList(PokemonList.shuffled())

    }

    fun start() {
        started = true
        thread(start = true) {
            while (true) {
                Thread.sleep(1000)
                if (!started) break
                timeTotal = timeTotal - 1
                runOnUiThread {
                    binding.timeTextView.text = "$timeTotal"
                    if (timeTotal == 50) {
                        Toast.makeText(this, "시간끝", Toast.LENGTH_SHORT).show()
                        started = false
                        timeTotal = 60
                        binding.timeTextView.text = "60"
                        val intent = Intent(this@PlayActivity, ScoreActivity::class.java)
                        intent.putExtra("data",binding.playScoreTextView.text.toString())
                        startActivity(intent)
                    }
                }
            }
        }
    }

    fun score() {
        thread(start = true) {
            while (true) {
                if (!started) break
                Thread.sleep(1000)
                runOnUiThread {
                    binding.playScoreTextView.text = "$score"
                }
            }
        }
    }


    //    백그라운드로 가면 음악 멈춤
    override fun onStop() {
        super.onStop()
        mPlayer.pause()

    }

    //    Pause() 상태에서 일시 정지
    override fun onPause() {
        super.onPause()
        mPlayer.pause()

    }

    //    메모리 해제
    override fun onDestroy() {
        super.onDestroy()
        mPlayer.release()

    }


}