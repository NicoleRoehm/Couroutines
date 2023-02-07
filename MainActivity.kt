package com.example.coroutines

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // TODO
//      theory()
        viewModel.image.observe(this){
            binding.imageText.text = it
        }
        viewModel.loading.observe(this){
            if(it){
                binding.loadingProgressBar.visibility = View.VISIBLE
            }else {
                binding.loadingProgressBar.visibility = View.INVISIBLE
            }
        }

        binding.loadFab.setOnClickListener {
            binding.imageText.text = ""
            viewModel.loadData()
        }

    }


    /**
     * THEORIE
     */
    fun theory() {

        // veraltet
//        var counter = 0
//
//        for (i in 1..50) {
//            Thread {
//                counter ++
//                println("Thread $i -> counter $counter")
//            }.start()
//        }

//        var counter = 0
//
//        for (i in 1..50) {
//            lifecycleScope.launch {
//                counter++
//                println("Coroutine $i -> counter $counter")
//            }
//        }

        val firstJob = lifecycleScope.launch {
            println("Job started")
            delay(2000)
            println("Job ended")
        }

        firstJob.cancel()


//        lifecycleScope.launch {
//            waitOne()
//        }


        // finden hintereinander statt
        lifecycleScope.launch {
            waitOne()
            waitTwo()
            waitThree()
        }


        // finden parallel statt
        lifecycleScope.launch {
            waitOne()
        }

        lifecycleScope.launch {
            waitTwo()
        }

        lifecycleScope.launch {
            waitThree()
        }




    }

    suspend fun waitOne() {
        println("wait1 started")
        delay(5000)
        println("wait1 ended")
    }

    suspend fun waitTwo() {
        println("wait2 started")
        delay(2000)
        println("wait2 ended")
    }

    suspend fun waitThree() {
        println("wait3 started")
        delay(3000)
        println("wait3 ended")
    }

}
