package com.cooking.merge.food_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.R
import com.cooking.merge.adapters.FooditemsAdapter
import com.cooking.merge.adapters.OnFoodItemClickListener
import com.cooking.merge.models.FooditemsModel
import kotlinx.android.synthetic.main.recycler_layout.view.*


class Dessertf : Fragment() , OnFoodItemClickListener{
    private var recyclerView: RecyclerView? = null
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var foodList: ArrayList<FooditemsModel>
    lateinit var foodiesAdapters: FooditemsAdapter  //繼承FooditemsAdapter

    ////////////////////////////////////////////////array////////////////////////////////////////////////
    private val titles = arrayOf(
        "檸檬醋", "蔬果綠拿鐵", "金黃芝麻地瓜", "水果小冰棒", "微笑香蕉蛋糕",
        "蜂蜜檸檬梅漬小番茄", "法式蘋果克拉芙緹", "巧克力夏威夷豆餅乾", "髒髒派", "迷你優格牛奶司康"
    )

    private val images = arrayOf(
        R.drawable.dessert2, R.drawable.dessert3, R.drawable.dessert4,
        R.drawable.dessert5, R.drawable.dessert6, R.drawable.dessert7, R.drawable.dessert8,
        R.drawable.dessert9, R.drawable.dessert10, R.drawable.dessert11
    )

    private val ingredients = arrayOf(
        "檸檬 1斤",
        "蔬菜 適量\n" + "水果 適量\n" + "堅果(可有可無) 1小匙\n" + "開水 適量",
        "地瓜 1顆",
        "水果 適量\n" + "蘋果汁/乳酸飲料 適量\n" + "短冰棒棍/水果叉 適量",
        "<香蕉麵糊>\n" + "熟香蕉 2大根\n" + "蛋 1顆\n" + "蔗糖/紅糖 150克\n" + "無鹽奶油 110克\n" + "希臘式優格(無糖為佳) 60克\n" +
        "香草精 1小匙\n" + "低筋麵粉 125克\n" + "小蘇打粉 1小匙\n" + "鹽 1小撮",
        "小番茄 300克\n" + "酸梅 10~15顆\n" + "熱開水 100ml\n" + "蜂蜜 5大匙\n" + "新鮮檸檬汁 1顆",
        "大蘋果 1顆\n" + "奶油 10克" + "低筋麵粉 20克" + "蛋 1顆" + "牛奶 90克" + "糖粉(裝飾用) 適量" + "薄荷葉(裝飾用) 兩片",
        "無鹽奶油 70克\n" + "黑糖粉 55克\n" + "香草精 1/2小匙\n" + "蛋黃 1顆\n" + "中筋麵粉 110克\n" + "蘇打粉 1/4小匙\n" +
        "巧克力豆 50克\n" + "無調味夏威夷豆 50克",
        "冷凍起酥皮 3片\n" + "巧克力 50克",
        "無鹽奶油 60克\n" + "低筋麵粉 225克\n" + "全麥麵粉 25克\n" +
        "泡打粉 8克\n" + "原味優格 70克\n" + "鮮奶 30克\n" + "雞蛋 1顆 \n" + "蛋黃 1顆\n" + "蔓越莓乾/葡萄乾(可略) 50克\n" + "高筋麵粉 適量"
    )

    private val sauses = arrayOf(
        "冰糖 150公克\n" + "高粱醋 1斤", "不須調味料", "黑芝麻 1匙\n" + "鹽巴 少許\n" + "蜂蜜 適量", "不須調味料",
        "<奶油乳酪餡> \n" + "Cream Cheese 110克\n" + "蛋 1顆\n" + "白砂糖 50克\n" + "低筋麵粉 3大匙", "不須調味料",
        "三溫糖/紅糖/蔗糖 10克" + "香草糖/白砂糖/上白糖 20克", "白糖 45克\n" + "鹽 1/4小匙\n", "可可粉 適量",
        "白砂糖 40克\n" + "鹽 4克 \n"
    )

    private val links = arrayOf(
        "https://icook.tw/recipes/341359",
        "https://icook.tw/recipes/313727",
        "https://cookpad.com/tw/%E9%A3%9F%E8%AD%9C/2069981-%E9%87%91%E9%BB%83%E8%8A%9D%E9%BA%BB%E5%9C%B0%E7%93%9C%E9%9B%BB%E9%8D%8B%E6%96%99%E7%90%86%E5%B0%8F%E8%B3%87%E5%A5%B3%E5%81%A5%E5%BA%B7%E5%B0%8F%E9%BB%9E%E5%BF%83#",
        "https://icook.tw/recipes/257904",
        "https://icook.tw/recipes/255342",
        "https://icook.tw/recipes/252287",
        "https://icook.tw/recipes/249785",
        "https://icook.tw/recipes/247907",
        "https://icook.tw/recipes/243939",
        "https://icook.tw/recipes/243358"
    )

    private val key_id = arrayOf(
        "42", "43", "44", "45", "46", "47", "48", "49", "50", "51"
    )

    private val favStatus = arrayOf(
        "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"
    )
    ////////////////////////////////////////////////array////////////////////////////////////////////////

    //(第二發生)顯示dessert_layout的介面
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recycler_layout, container, false)
    }

    //(第三發生)initialize recyclerView and layout manager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.my_recycler_view

        // design the gridlayout & set recyclerview
        gridLayoutManager = GridLayoutManager(
            requireContext(), 3,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        // design the gridlayout & set recyclerview

        foodList = ArrayList()
        foodList = addfood()
        foodiesAdapters =
            FooditemsAdapter(requireContext(), this, foodList)   //adapter按照位置擺放foodlist裡的所有物品
        recyclerView?.adapter = foodiesAdapters

    }

    fun addfood(): ArrayList<FooditemsModel> {
        val addlist: ArrayList<FooditemsModel> = ArrayList()
        for (i in titles.indices) {
            val model = FooditemsModel(images[i], titles[i], ingredients[i], sauses[i], links[i], key_id[i], favStatus[i])
            addlist.add(model)
        }
        return addlist
    }

    override fun onItemClick(item: FooditemsModel, position: Int) {
        val intent = Intent(context, FoodDetailsf::class.java)
        intent.putExtra("FOODIMAGE", item.iconsChar.toString())
        intent.putExtra("FOODNAME", item.alphaChar)
        intent.putExtra("FOODINGREDIENT", item.ingredient)
        intent.putExtra("FOODSAUCE", item.sauce)
        intent.putExtra("FOODLINK", item.link)

        startActivity(intent)
    }

}
