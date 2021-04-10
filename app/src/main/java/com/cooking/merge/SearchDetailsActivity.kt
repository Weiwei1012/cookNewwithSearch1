package com.cooking.merge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.adapters.SearchitemsAdapter

class SearchDetailsActivity : AppCompatActivity() {

    lateinit var adapter: SearchitemsAdapter
    lateinit var details_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_details)//製作搜尋結果的RecyclerView
        details_rv = findViewById(R.id.details_rv)
        gridLayoutManager = GridLayoutManager(
            details_rv.context, 1,
            LinearLayoutManager.VERTICAL, false
        )
        details_rv.layoutManager = gridLayoutManager
        details_rv.setHasFixedSize(true)

        //因為DetailsActivity會接收兩種Intent
        //  1. 熱門搜尋點擊事件（"passhot"）
        //  2. 使用者輸入搜尋字串結果（"passsearched"）

        val hotListString = getIntent().getStringExtra("passhot")

        //因此需判斷是接收到哪一種Intent
        //若非1 -> 做getListofSearch function
        if (hotListString == null){
            getListofSearch()
        }

        //若1 -> 一樣將點擊的品項所對應的料理array加入到名為hotList的ArrayList中並傳入Details_rv_adapter
        else{
            val hotList = ArrayList<String>()
            hotList.add(hotListString)
            adapter = SearchitemsAdapter(hotList)     //之後要改成另一個adapter
            details_rv.adapter = adapter
        }

    }

    private fun getListofSearch() {

        //從Intent所接收到的為2
        val search_results_items = getIntent().getStringArrayListExtra("passsearched")!!
        val resultList = ArrayList<String>()
        //將使用者所輸入的每一種食材所對應的料理array一一加入到resultList中並傳入Details_rv_adapter

        for (i in search_results_items) {   //未來可能要用雙重迴圈，因為一開始所傳進來的是對應的料理”陣列“
            resultList.add(i)                       //因此需再做一層回圈將所有品項分離出來
        }
        adapter = SearchitemsAdapter(resultList)      //之後要改成另一個adapter
        details_rv.adapter = adapter
    }
}

