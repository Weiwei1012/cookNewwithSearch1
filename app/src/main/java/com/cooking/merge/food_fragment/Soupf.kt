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


class Soupf : Fragment(), OnFoodItemClickListener{
    private var recyclerView: RecyclerView? = null
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var foodList: ArrayList<FooditemsModel>
    lateinit var foodiesAdapters: FooditemsAdapter  //繼承FooditemsAdapter

    ////////////////////////////////////////////////array////////////////////////////////////////////////
    private val titles = arrayOf(
        "菇菇蘿蔔雞湯", "金沙絲瓜湯", "海帶芽豆腐味噌湯", "鮮蔬豆腐味噌湯", "洋蔥湯",
        "麻油菇菇雞湯", "豚汁蔬菜湯", "蒜頭洋蔥雞湯", "韓式海帶芽排骨湯", "雞茸玉米羮"
    )

    private val images = arrayOf(
        R.drawable.soup1, R.drawable.soup2, R.drawable.soup3, R.drawable.soup4,
        R.drawable.soup5, R.drawable.soup6, R.drawable.soup7, R.drawable.soup8,
        R.drawable.soup9, R.drawable.soup10
    )

    private val ingredients = arrayOf(
        "雞胸肉 適量\n" + "金針菇 或任一菇類\n" + "蘿蔔 適量\n" + "黑菜脯(可加可不加) 適量",
        "絲瓜 1條\n" + "薑 切絲\n" + "鹹蛋 1顆",
        "中華雞蛋豆腐 1盒\n" + "海帶芽 1把\n" + "柴魚片 1把\n" + "蔥 3根",
        "板豆腐 1盒\n" + "高麗菜絲 150g\n" + "洋蔥絲 100g\n" + "青蔥絲 3根\n" + "柴魚片 50g\n",
        "洋蔥 2/3顆\n" + "雞蛋 1個\n" + "生薑 適量\n" + "大蒜 2片\n" + "太白粉(適量) 勾芡",
        "土雞腿切塊 600克\n" + "麻油 2-3大匙\n" + "老薑切片 12片\n" + "金針菇 1包\n" + "鴻喜菇 1包\n" + "枸杞 1把\n" + "高麗菜 適量\n" + "米酒 100ml\n" + "水 2公升",
        "豬五花薄片 200克\n" + "洋蔥 半顆\n" + "高麗菜 1/8顆\n" + "鴻喜菇 半包\n" + "金針菇 半包\n" + "紅蘿蔔 30克\n" + "白蘿蔔 50克\n" + "青葱 1根\n" + "信州味噌 3大匙\n" + "熱開水 1000ml",
        "仿土雞腿 1大支\n" + "薑 3片\n" + "中型洋蔥 1顆\n" + "蒜頭 12瓣\n" + "娃娃菜 2株",
        "排骨肉 1~1.5斤\n" + "韓國海帶芽 30克",
        "雞骨架 1付\n" + "玉米粉(太白粉亦可) 1大匙\n" + "開水 50ml\n" + "玉米醬 1罐\n" + "蛋 1顆\n" + "鹽 適量\n" + "火腿(切末) 1片"
    )

    private val sauses = arrayOf(
        "鹽巴 適量\n", "鹽巴 適量\n", "味噌 適量\n", "味噌 2大匙\n", "味醂 1小匙\n" + "醬油 1大匙\n" + "麻油 1小匙",
        "雞粉(鮮雞晶) 1大匙\n" + "鹽 適量\n", "味醂 1大匙\n" + "烹大師 1小匙\n", "鹽 適量\n" + "白胡椒粉 適量\n",
        "鹽 適量\n" + "黑麻油 1.5大匙\n" + "醬油 2大匙\n" + "蒜泥 1瓣\n" + "水 1.5~2公升\n", "葱(切段) 1根\n" + "薑 3片\n" + "水 1200ml"
    )

    private val links = arrayOf(
        "https://uvz90.pixnet.net/blog/post/358375766-%E3%80%90%E5%B0%8F%E8%B3%87%E5%BB%9A%E5%A8%98%E7%9C%81%E9%8C%A2%E5%81%A5%E5%BA%B7%E9%A4%90%E3%80%91%E4%B8%8A%E7%8F%AD%E6%97%8F%E5%A6%82%E4%BD%95%E6%BA%96%E5%82%99%E5%BF%AB%E9%80%9F ",
        "https://icook.tw/recipes/332917", "https://icook.tw/recipes/264401", "https://icook.tw/recipes/263550",
        "https://icook.tw/recipes/131364", "https://icook.tw/recipes/319684", "https://icook.tw/recipes/317160",
        "https://icook.tw/recipes/313189", "https://icook.tw/recipes/269724", "https://icook.tw/recipes/332794"
    )

    private val key_id = arrayOf(
          "127", "128", "129", "130", "131", "132", "133", "134", "135", "136"
          )

    private val favStatus = arrayOf(
          "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"
          )
    ////////////////////////////////////////////////array////////////////////////////////////////////////

    //(第一發生)顯示breakfast_layout的介面
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

        foodList = ArrayList()      //將foodList作為一個arraylist
        foodList = addfood()        //foodList存放圖片及名稱

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
