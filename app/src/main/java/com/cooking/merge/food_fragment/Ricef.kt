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


class Ricef: Fragment(), OnFoodItemClickListener
     {
         private var recyclerView: RecyclerView? = null
         lateinit var gridLayoutManager: GridLayoutManager
         lateinit var foodList: ArrayList<FooditemsModel>
         lateinit var foodiesAdapters: FooditemsAdapter  //繼承FooditemsAdapter

         ////////////////////////////////////////////////array////////////////////////////////////////////////
         private val titles = arrayOf(
            "味噌炊飯", "和風白菜雞蛋丼", "鮭魚五穀米炒飯", "麻油菇菇雞飯", "空心菜梗炒飯",
             "海鮮泡飯", "豆腐飯", "日式豆腐丼", "泰式排骨湯泡飯", "沙茶豬肉燴飯",
             "高蛋白便當", "香芹皮芋粥"
         )

         private val images = arrayOf(
             R.drawable.rice4,
             R.drawable.rice5,
             R.drawable.rice6,
             R.drawable.rice7,
             R.drawable.rice8,
             R.drawable.rice9,
             R.drawable.rice10,
             R.drawable.rice11,
             R.drawable.rice12,
             R.drawable.rice13,
             R.drawable.rice15,
             R.drawable.rice17
         )

         private val ingredients = arrayOf(
             "白米 0.5杯\n" + "雪白菇 0.5包\n" + "紅蘿蔔 0.25條 \n" + "板豆腐 0.25盒\n" + "水 0.5杯",
             "大白菜 5片\n" + "大蒜 2辦\n" + "雞蛋 1顆\n" + "柴魚片 2大匙\n",
             "鮭魚 半片 \n" + "毛豆 碗裝約7分滿\n" + "熟五穀米飯(冷飯) 2碗",
             "米 1.5米杯\n" + "薑片 10片\n" + "蒜末 1小匙\n" + "乾香菇 3朵\n" + "各種菇類(任意) 適量\n" + "三色蔬菜 1小碗\n" + "雞胸肉/去骨雞腿 1片",
             "空心菜梗 1把\n" + "雞蛋 1顆\n" + "蝦仁 3~4隻\n" + "豬絞肉 適量\n" + "冷飯 一人份\n" + "冷凍蔬菜 適量",
             "蝦仁 75克\n" + "透抽 75克\n" + "青葱 1根\n" + "紅蘿蔔 75克\n" + "蘑菇/鴻喜菇 75克\n" + "大白菜 150克\n" + "芹菜 1根\n"
             + "太白粉(洗蝦仁用) 1大匙\n" + "水 600ml\n" + "白飯 2碗",
             "豆腐 1盒\n" + "白飯 1碗",
             "家常/火鍋豆腐 1盒\n" + "洋蔥 1/4顆\n" + "大白菜葉 2片\n" + "雞蛋 2顆\n" + "葱花 適量",
             "排骨 1.5斤\n" + "杏鮑菇 3~4根\n" + "薑 3片",
             "梅花豬肉片 200克\n" + "洋蔥 1/4顆\n" + "蒜頭 1瓣\n" + "空心菜 半把\n" + "紅辣椒(可略) 半根",
             "板豆腐 2塊\n" + "新鮮玉米 2根\n" + "藜麥 2杯\n" + "毛豆 200克\n" + "紅蘿蔔 1根\n" + "花椰菜 1顆\n" + "洋蔥 1顆\n" + "薑絲 少許",
             "香菇 4~5朵\n" + "芋頭 1/4顆\n" + "西洋芹 3片\n" + "肉燥 少許\n" + "白飯 適量\n" + "皮蛋 2顆\n" + "熱水 3碗"
         )

         private val sauses = arrayOf(
             "味噌(亦可用味噌湯包) 10克\n", "鰹魚露/日式醬油 1大匙\n" + "鹽巴 少許 \n" + "味精 少許\n" + "七味粉(可有可無)\n",
             "海鹽 適量\n" + "黑胡椒 適量\n", "麻油 2小匙\n" + "米酒 1小匙\n" + "白胡椒粉 少許\n", "淡色醬油 10ml\n" + "鹽 適量\n",
             "白胡椒粉 1/4匙\n" + "鹽 0.5~1匙\n", "不須調味料\n", "醬油 2大匙\n" + "味醂 1/2大匙\n" + "烹大師 1/2小匙\n" + "糖 1/2小匙\n" + "水 120ml\n",
             "蠔油 2大匙\n" + "米酒 2大匙\n" + "白醬油 1大匙\n" + "大蒜粉 3大匙\n" + "白胡椒粉 1/2小匙\n" + "水 1600ml\n" + "鹽 適量\n",
             "白胡椒 適量\n" + "薑黃粉 適量\n" + "鹽 適量\n", "黑胡椒 適量\n" + "醬油 適量\n" + "薑黃粉 適量\n" + "油 適量\n" + "鹽巴 適量\n" + "花生醬(無糖佳) 適量\n ",
             "鹽 少許\n" + "胡椒粉 適量\n" + "香油 少許\n"
         )

         private val links = arrayOf(
             "https://icook.tw/recipes/340373", "https://icook.tw/recipes/323393", "https://icook.tw/recipes/263386", "https://icook.tw/recipes/122825",
             "https://icook.tw/recipes/122728", "https://icook.tw/recipes/343935", "https://icook.tw/recipes/322957", "https://icook.tw/recipes/319165",
             "https://icook.tw/recipes/271048", "https://icook.tw/recipes/243368", "https://www.instagram.com/p/CCLU5afjDN_/",
             "https://icook.tw/recipes/173949"
         )

          private val key_id = arrayOf(
               "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "92", "93"
          )

          private val favStatus = arrayOf(
               "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"
          )
         ////////////////////////////////////////////////array////////////////////////////////////////////////

         //(第一發生)顯示rice_layout的介面
         override fun onCreateView(
             inflater: LayoutInflater, container: ViewGroup?,
             savedInstanceState: Bundle?
         ): View? {
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

             foodList = ArrayList()      //將foodList作為一個arraylist
             foodList = addfood()        //foodList存放圖片及名稱

             foodiesAdapters =
                 FooditemsAdapter(requireContext(), this, foodList)   //adapter按照位置擺放foodlist裡的所有物品
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

         override fun onItemClick(item: FooditemsModel, position: Int)
         {
             val intent = Intent(context, FoodDetailsf::class.java)
             intent.putExtra("FOODIMAGE", item.iconsChar.toString())
             intent.putExtra("FOODNAME", item.alphaChar)
             intent.putExtra("FOODINGREDIENT", item.ingredient)
             intent.putExtra("FOODSAUCE", item.sauce)
             intent.putExtra("FOODLINK", item.link)

             startActivity(intent)
         }

}
