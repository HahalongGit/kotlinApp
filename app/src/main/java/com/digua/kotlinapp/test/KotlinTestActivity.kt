package com.digua.kotlinapp.test

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.digua.kotlinapp.R
import com.digua.kotlinapp.databinding.ActivityKotlinTestBinding

class KotlinTestActivity : AppCompatActivity() {

//    private var mTvNameText = TODO("not yet init");

    private lateinit var binding: ActivityKotlinTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var innerClass = new  InnerClass();
    }

    //内部类
    // 内部类如何使用
     class InnerClass{



        fun showLoading() {

        }
    }

}