package com.cooking.merge.food_fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.cooking.merge.R
import com.cooking.merge.web.Webf
import kotlinx.android.synthetic.main.fooditems_details.*


class FoodDetailsf : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fooditems_details)

        //////返回鈕//////
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        //////返回鈕//////

        //////set data//////
        IV_detailsimage.setImageResource(intent.getStringExtra("FOODIMAGE")?.toInt()!!)
        TV_detailsname.text = intent.getStringExtra("FOODNAME")
        TV_food_need.text = intent.getStringExtra("FOODINGREDIENT")
        TV_sauce.text = intent.getStringExtra("FOODSAUCE")

        BTN_link.setOnClickListener() {
            val webintent = Intent(this, Webf::class.java)
            val weburl = intent.getStringExtra("FOODLINK")
            val webtitle = intent.getStringExtra("FOODNAME")
            webintent.putExtra("weblink",weburl)
            webintent.putExtra("webtitle",webtitle)
            startActivity(webintent)

//            val open_web_page =
//                Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("FOODLINK")))
//            startActivity(open_web_page)
        }
        //////set data//////

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



