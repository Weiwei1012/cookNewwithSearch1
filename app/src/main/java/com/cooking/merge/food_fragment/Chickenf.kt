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


class Chickenf: Fragment(), OnFoodItemClickListener
 {
    private var recyclerView: RecyclerView? = null
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var foodList: ArrayList<FooditemsModel>
    lateinit var foodiesAdapters: FooditemsAdapter  //繼承FooditemsAdapter

    ////////////////////////////////////////////////array////////////////////////////////////////////////
    private val titles = arrayOf(
        "蔥香味噌雞", "三杯菇菇雞", "安東燉雞", "醬燒雞腿杏鮑菇", "迷迭香黑胡椒馬鈴薯雞", "豉汁彩椒雞球",
        "洋蔥雞丁", "泰式涼拌柚香雞絲", "豆芽雞絲",  "酥炸雞皮", "啤酒燒雞腿", "蔥燒雞腿", "香滷棒棒腿",
        "蜜汁雞翅", "蠔油雞翅", "美乃茄醬烤雞翅","咖哩生薑燒雞腿", "咖哩優格烤雞翅", "青檸可樂雞翅腿",
        "甜辣醬烤雞翅", "咖哩烤雞翅腿", "黑胡椒檸檬醬烤雞翅腿", "起司嫩雞塊", "咖哩美乃滋嫩雞塊",
        "檸香鹽麴醬烤雞腿排", "嫩煎香料雞胸", "塔香味噌鹽麴烤雞腿排", "紅糟醬烤雞腿排"
    )

    private val images = arrayOf(
        R.drawable.ch1, R.drawable.ch21,R.drawable.ch23, R.drawable.ch2, R.drawable.ch3,
        R.drawable.ch8, R.drawable.ch25, R.drawable.ch24, R.drawable.ch6,R.drawable.ch5,
        R.drawable.ch4, R.drawable.ch13, R.drawable.ch17, R.drawable.ch9, R.drawable.ch7,
        R.drawable.ch10, R.drawable.ch11, R.drawable.ch12, R.drawable.ch14, R.drawable.ch22,
        R.drawable.ch18, R.drawable.ch15, R.drawable.ch19, R.drawable.ch20, R.drawable.ch16,
        R.drawable.ch26, R.drawable.ch27, R.drawable.ch28
    )

    private val ingredients = arrayOf(
        "雞胸肉 約300克\n" + "洋蔥 1/4顆\n" + "青蔥 2枝",
        "去骨雞腿肉 2片\n" + "杏鮑菇 2根\n" + "鴻喜菇或雪白菇 1包\n" + "老薑 6片\n" + "蒜頭 6瓣\n" + "紅辣椒 半根\n" + "九層塔 1大把",
        "雞腿切塊 1斤\n" + "韓國寬冬粉 50克\n" + "青蔥 3根\n" + "洋蔥 半顆\n" + "紅蘿蔔 1根\n" + "馬鈴薯 1顆\n" + "薑末 0.5小匙\n" +
        "蒜末 3瓣\n" + "辣椒 適量\n" + "白芝麻 適量", "去骨雞腿肉 1支\n" + "杏鮑菇 3支\n" + "青蔥 1支\n" + "辣椒 1支",
        "雞腿切塊 4隻\n" + "馬鈴薯 6顆\n" + "蒜頭 3瓣\n" + "迷迭香 適量\n" + "橄欖油 1.5大匙",
        "去骨雞腿排 約350克\n" + "胡椒鹽 適量 \n" + "紅椒 1/4個\n" + "黃椒 1/4個\n" + "洋蔥 1/4個\n" + "蒜頭 3瓣\n" + "薑泥 1/2小匙",
        "雞胸肉 1份\n" +  "洋蔥 半顆\n" + "老薑絲 適量\n" + "蔥段 適量",
        "雞胸肉 2片\n" + "小黃瓜 1根\n" + "柚子果肉 2-3瓣\n" + "小番茄 4-5顆",
        "雞胸肉 1片\n" + "綠豆芽 300克\n" + "炒菜油 2大匙\n", "雞皮 適量", "大雞腿 6支",
        "去骨雞腿排 約350克\n" + "洋蔥 半顆\n" + "紅甜椒 1/4顆\n" + "葱 3根\n" + "蒜頭 3瓣\n" + "胡椒鹽 適量",
        "雞棒棒腿 5支 \n" + "乾香菇 5小朵\n" + "青蔥 2根\n" + "油豆腐 3大塊\n" + "薑 3片",
        "雞中翅 18支", "雞中翅 14-15支\n" + "葱 3根 \n" + "薑 6片\n" + "醬油 1大匙\n" + "麻油 0.5大匙",
        "雞中翅 14支", "去骨雞腿排 2片\n" + "洋蔥 半顆", "雞中翅 12~16隻", "雞翅/翅腿 12支\n" + "青葱 1根\n" + "蒜頭 2瓣\n" + "可樂 150ml",
        "雞中翅 20隻\n" + "蒜泥 3瓣", "雞翅/雞腿 12支", "雞中翅/翅小腿 10-12支", "雞胸肉 2片", "雞胸肉 1片",
        "去骨雞腿排 1片", "雞胸 2~3片\n" + "開水 200ml", "去骨雞腿排 2片", "去骨雞腿排 1片 \n" + "耐高溫植物油 適量"
    )

    private val sauses = arrayOf(
        "味噌 2匙\n" + "醬油 1匙\n" + "白胡椒粉 少許\n",
        "<調味料> \n" + "麻油 1大匙\n" + "醬油膏 2.5大匙\n" + "米酒 2大匙" + "\n <醃料>\n" + "胡椒鹽 適量\n" + "米酒 1小匙 \n" ,
        "黑麻油 1.5大匙\n" + "水 500ml\n" + "料理米酒 2大匙\n" + "蠔油 1大匙\n" + "醬油 4大匙\n" + "蜂蜜 1大匙\n",
        "<調味料> \n" + "素蠔油 1大匙\n" + "味醂 1小匙\n" + "\n <醃料>\n" + "素蠔油 1大匙 \n" + "低鹽醬油 1大匙\n" + "米酒 1大匙\n" + "砂糖 1小匙\n",
        "香菇素蠔油 2大匙\n" + "米酒 1大匙\n" + "砂糖 2匙\n" + "黑胡椒 1大匙\n" + "水 300~500c.c\n",
        "豆豉醬 1大匙\n" + "糖 1小匙\n" + "米酒 2大匙\n" + "蠔油 2小匙\n",
        "<調味料>\n" + "醬油 適量\n" + "油膏 適量\n" + "砂糖 適量\n" + "黑胡椒粒 適量\n" + "水 適量\n" + "\n<醃料>\n" + "醬油 少許\n" +
        "米酒 少許 \n" + "砂糖 少許\n" + "胡椒粉 少許\n" + "太白粉 兩匙\n",
        "<雞胸醃料>\n" + "鹽 1小匙\n" + "開水 200ml\n" + "\n <泰式醬料>\n" + "新鮮檸檬汁 半顆 \n" + "糖 2小匙\n" + "魚露 1大匙\n" + "蒜泥 1瓣\n" + "香菜碎 1株\n",
        "醬油 20c.c\n" + "味琳 20c.c\n" + "水 20c.c\n" + "白芝麻 少許", "鹽 適量\n" + "七味粉 適量\n",
        "醬油 50ml\n" + "二砂糖 30g\n" + "罐裝啤酒 1罐", "米酒 1大匙\n" + "蠔油 1大匙\n" + "番茄醬 0.5大匙\n" + "糖 1小撮\n",
        "醬油 4大匙\n" + "蠔油 2大匙\n" + "米酒 2大匙\n" + "糖 1小匙\n" + "五香粉 1/4小匙\n" + "八角 1個 \n" + "水 500ml\n",
        "蜂蜜 1.5大匙\n" + "蠔油 2大匙\n" + "醬油 1大匙\n" + "蒜頭 3瓣\n" + "研磨黑胡椒 轉15下\n",
        "蠔油 1大匙\n" + "米酒 0.5大匙\n" + "水 200ml\n" + "糖 0.5小匙\n" + "胡椒粉 1/4小匙\n",
        "<調味料>" + "胡椒鹽 適量\n" + "\n<醃料>\n" + "美乃滋 3大匙 \n" + "番茄醬 1.5大匙\n" + "研磨黑胡椒 適量\n" + "義大利香料 適量\n" + "蒜泥 2瓣\n",
        "<調味料>\n" + "米酒 1大匙 \n" + "醬油 1大匙\n" + "味醂 1大匙\n" + "番茄醬 1/2小匙\n" + "薑泥 2小匙\n" + "\n<醃料> \n" + "白胡椒鹽 適量\n" + "咖哩粉 1大匙\n",
        "<調味料>\n" + "白胡椒鹽 適量\n" + "\n<醃料>\n" + "咖哩粉 1大匙\n" + "番茄醬 2大匙\n" + "希臘式優格(有無糖皆可) 3大匙\n" + "蒜泥 2瓣\n" + "鹽 1/4小匙\n",
        "白胡椒鹽 適量\n" + "醬油 1大匙\n" + "新鮮檸檬汁 1-2小匙","甜辣醬 4大匙\n" + "蜂蜜 1大匙\n" + "研磨黑胡椒 適量\n" + "義式香料 適量\n",
        "鹽 1/4小匙\n" + "咖哩粉 2小匙\n" + "薑黃粉2小匙\n" + "孜然粉 1小匙\n" + "米酒 2大匙\n",
        "醬油 1.5大匙\n" + "蒜末 1瓣\n" + "耐高溫植物油 1大匙\n" + "檸檬汁 1/2大匙\n" + "研磨黑胡椒 1/2小匙\n" + "鹽 1/8小匙\n",
        "鹽 適量\n" + "研磨黑胡椒 適量\n" + "蛋 1顆\n" + "起司粉 3大匙\n" + "麵包粉 1大匙\n" + "玉米粉(或太白粉) 2小匙\n" + "香草鹽 適量\n",
        "米酒 1小匙\n" + "白胡椒鹽 適量\n" + "咖哩粉 1小匙\n" + "起司粉 1小匙\n" + "美乃滋 1/2大匙\n" + "義式香料 適量\n" + "玉米粉(可略) 1小匙\n",
        "<調味料>\n" + "耐高溫植物油 適量\n" + "\n<醃料> \n" + "鹽麴 1大匙\n" + "蒜泥 1瓣\n" + "研磨黑胡椒 適量\n" + "檸檬汁 1大匙\n" + "醬油 1小匙\n",
        "鹽 1小匙\n" + "研磨黑胡椒 適量\n" + "咖哩粉 適量\n" + "薑黃粉(可有可無) 適量", "九層塔 1把\n" + "信州味噌 2大匙\n" + "鹽麴 1大匙\n" + "味醂 1大匙\n" +
        "米酒 1大匙\n" + "醬油 2小匙\n" + "糖 2小匙\n",
        "紅糟醬 1大匙\n" + "味醂 1大匙\n" + "醬油 1小匙\n" + "蒜泥 1瓣\n"
    )

     private val links = arrayOf(
         "https://icook.tw/recipes/342555", "https://icook.tw/recipes/270334", "https://icook.tw/recipes/242709", "https://icook.tw/recipes/162577",
         "https://icook.tw/recipes/122300", "https://icook.tw/recipes/321648", "https://icook.tw/recipes/119735", "https://icook.tw/recipes/312336",
         "https://icook.tw/recipes/348068",  "https://icook.tw/recipes/117692", "https://icook.tw/recipes/121235", "https://icook.tw/recipes/300920",
         "https://icook.tw/recipes/293369", "https://icook.tw/recipes/320936", "https://icook.tw/recipes/329136", "https://icook.tw/recipes/315014",
         "https://icook.tw/recipes/307480", "https://icook.tw/recipes/302616", "https://icook.tw/recipes/298611", "https://icook.tw/recipes/260493",
         "https://icook.tw/recipes/288293", "https://icook.tw/recipes/295269", "https://icook.tw/recipes/281874", "https://icook.tw/recipes/276299",
         "https://icook.tw/recipes/294308", "https://icook.tw/recipes/310629", "https://icook.tw/recipes/308976", "https://icook.tw/recipes/301920"
     )

    private val key_id = arrayOf(
        "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
        "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41"
    )

    private val favStatus = arrayOf(
        "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
        "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"
    )
    ////////////////////////////////////////////////array////////////////////////////////////////////////

    //(第一發生)顯示easy_layout的介面
    override fun onCreateView(
     inflater: LayoutInflater, container: ViewGroup?,
     savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recycler_layout, container, false)
    }

    //(第二發生)initialize recyclerView and layout manager
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
        foodiesAdapters = FooditemsAdapter(requireContext(), this, foodList )   //adapter按照位置擺放foodlist裡的所有物品
        recyclerView?.adapter = foodiesAdapters

    }

    fun addfood(): ArrayList<FooditemsModel> {
        val addlist: ArrayList<FooditemsModel> = ArrayList()
        for (i in titles.indices)
        {
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
