package com.cooking.merge.welcome_page

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.cooking.merge.MainActivity
import com.cooking.merge.R
import kotlinx.android.synthetic.main.webview_page.*


class Welcomef : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)


        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)    //設定秒數
    }


}