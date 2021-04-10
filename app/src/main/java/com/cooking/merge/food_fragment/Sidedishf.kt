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

class Sidedishf : Fragment() , OnFoodItemClickListener
{
    private var recyclerView: RecyclerView? = null
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var foodList: ArrayList<FooditemsModel>
    lateinit var foodiesAdapters: FooditemsAdapter  //繼承FooditemsAdapter

    ////////////////////////////////////////////////array////////////////////////////////////////////////
    private val titles = arrayOf(
        "麻婆豆腐", "醬燒嫩豆腐", "日式滑蛋豆腐", "可樂肉醬", "德式香腸炒洋芋佐芥末籽醬", "醬燒南瓜",
        "香料鹽洋芋", "馬鈴薯鮪魚煎餅", "蔬菜煎餅", "金針菇煎餅", "月見蔥爆豆芽菜蛋餅", "蔥油餅", "金沙春筍",
        "螞蟻上樹", "豆干肉絲", "紅燒豆干", "蔥蒜香炒豆皮", "醬燒豬肉豆皮捲", "肉末蒸蛋", "茄汁馬鈴薯",
        "奶油香煎馬鈴薯", "古早味碰皮白滷", "黑胡椒炒豆芽", "韓式雜菜", "咖哩炒三色", "醬油金針煮", "肉絲筍片",
        "櫻花蝦炒蘿蔔絲", "日式涼拌小黃瓜豆腐", "涼拌豆皮蔬菜", "涼拌干絲", "涼拌黑木耳", "涼拌菇菇醬"
    )

    private val images = arrayOf(
        R.drawable.sidedish1,
        R.drawable.sidedish8,
        R.drawable.sidedish27,
        R.drawable.sidedish2,
        R.drawable.sidedish7,
        R.drawable.sidedish10,
        R.drawable.sidedish11,
        R.drawable.sidedish22,
        R.drawable.sidedish23,
        R.drawable.sidedish24,
        R.drawable.sidedish25,
        R.drawable.sidedish12,
        R.drawable.sidedish13,
        R.drawable.sidedish16,
        R.drawable.sidedish17,
        R.drawable.sidedish18,
        R.drawable.sidedish15,
        R.drawable.sidedish19,
        R.drawable.sidedish20,
        R.drawable.sidedish14,
        R.drawable.sidedish29,
        R.drawable.sidedish31,
        R.drawable.sidedish32,
        R.drawable.sidedish33,
        R.drawable.sidedish26,
        R.drawable.sidedish21,
        R.drawable.sidedish3,
        R.drawable.sidedish5,
        R.drawable.sidedish28,
        R.drawable.sidedish30,
        R.drawable.sidedish6,
        R.drawable.sidedish9,
        R.drawable.sidedish4
    )

    private val ingredients = arrayOf(
        "嫩豆腐 1盒\n" + "細絞肉 適量\n" + "蔥 1根\n" + "蒜頭 2粒",
        "豆腐 1盒\n" + "青蔥 1根\n" + "嫩薑 3片",
        "豆腐 1盒\n" + "雞蛋 3顆\n" + "洋蔥 1/8顆\n" + "鮮香菇 2朵\n" + "蔥 1根",
        "豬絞肉 300克\n" + "紅蔥頭 5瓣\n" + "蒜頭 5瓣\n" + "薑 1小塊\n" + "八角 1顆",
        "馬鈴薯 1顆\n" + "培根 2片\n" + "德式香腸 2條",
        "南瓜 約350克\n" + "薑 3片",
        "小型馬鈴薯 2~4顆\n" + "橄欖油 適量",
        "馬鈴薯 1顆\n" + "鮪魚罐頭 1份\n" + "雞蛋 2顆\n" + "蔥末 半支",
        "芹菜葉 50g\n" + "高麗菜 100g\n" + "紅蘿蔔 25g\n" + "蛋 1顆\n" + "低筋麵粉 1碗\n" + "水 1碗",
        "金針菇 200g\n" + "雞蛋 2個\n" + "中筋麵粉 30g\n" + "紅蘿蔔(切絲) 半條\n" + "青蔥 2根",
        "蛋餅皮 1片\n" + "豆芽菜 100g\n" + "大蒜 2瓣\n" + "青蔥 適量\n" + "雞蛋 1顆",
        "中筋麵粉 3杯\n" + "熱開水 1杯\n" + "冷開水 1/3杯\n" + "豬油 4大匙\n" + "葱末 適量",
        "春筍 1支\n" + "鹹鴨蛋 3個\n" + "蔥 2支\n" + "蒜末 2大匙",
        "冬粉 2把\n" + "豬絞肉 200g\n" + "蔥 2支\n" + "薑 15g\n" + "蒜 4瓣\n" + "大辣椒 1支",
        "五香豆干 10片\n" + "豬肉絲 150克\n" + "葱 2根\n" + "蒜頭 1瓣\n" + "辣椒絲 適量",
        "原味豆干 8個\n" + "大蒜片 1個\n" + "辣椒末 1根",
        "新鮮豆皮 6片\n" + "青蔥 3根\n" + "蒜頭 4瓣",
        "低脂瘦絞肉 400g\n" + "豆腐 1盒\n" + "嫩豆皮 5捲\n" + "老薑 15g",
        "蛋 2顆\n" + "絞肉 100g\n" + "薑末 1/4小匙\n" + "蔥花 1小把",
        "馬鈴薯 2顆",
        "馬鈴薯 2顆\n" + "奶油 20g",
        "白菜 2顆\n" + "蝦米 適量\n" + "炸豬皮 適量\n" + "豬骨 600克\n" + "蒜末 1小匙",
        "綠豆芽 1包\n" + "韭菜 適量\n" + "蒜頭 適量",
        "蒜泥 2瓣\n" + "菠菜(切段) 1把\n" + "洋蔥(切絲) 1/2顆\n" + "香菇(去蒂切絲) 5朵\n" + "黑木耳(切絲) 1-2張\n" + "中型紅蘿蔔(切絲) 1/3根\n" +
        "豬後腿肉絲 200-250g\n" + "韓式芝麻油 1小匙\n" + "韓式冬粉 250g\n" + "白芝麻 1大匙\n" + "醬油 3.5大匙\n" + "白胡椒 適量\n" +
        "蠔油 1/2大匙\n" + "細砂糖 2小匙\n",
        "毛豆仁 半碗\n" + "紅蘿蔔丁 半碗\n" + "玉米粒 半碗",
        "金針菇 2包",
        "綠竹筍 2支\n" + "豬肉絲 30克\n" + "蔥 1根\n" + "水 400ml",
        "白蘿蔔 半條\n" + "香菜 1~2株\n" + "櫻花蝦 1小把",
        "豆腐 1盒\n" + "蒜末 1大匙\n" + "小黃瓜 1/2條",
        "豆皮片 1包\n" + "小黃瓜 1條\n" + "紅蘿蔔 40g\n" + "黑木耳 140g\n" + "香菜 適量\n" + "蔥 2根",
        "干絲 300克\n" + "芹菜 2根\n" + "紅蘿蔔 1/4根",
        "黑木耳 200g\n" + "鳳梨 1圓片\n" + "嫩薑 5~8薄片\n" + "香菜 1大株\n" + "紅辣椒(可有可無) 適量",
        "金針菇(洗淨切小段) 1包\n" + "柴魚片 些許"
    )

    private val sauses = arrayOf(
        "辣豆瓣醬2 匙\n" + "醬油 3匙\n" + "味醂 1匙\n" + "白胡椒粉 少許\n" + "水 適量\n" + "香油 1湯匙\n" + "太白粉水 適量\n",
        "醬油 1大匙\n" + "豆瓣醬 1大匙\n" + "糖 1小匙\n" + "水 120ml\n" + "太白粉水 1/2匙\n" + "香油 適量\n",
        "油 1茶匙\n" + "七味粉 適量\n" + "日式昆布醬油 70ml\n" + "味醂 1又1/2匙\n" + "糖 1/2匙\n" + "水 180ml\n",
        "醬油膏 1大匙\n" + "米酒 2大匙\n" + "鹽 1/2小匙\n" + "醬油 1大匙\n" + "冰糖 1小匙\n" + "可樂 170ml\n" + "水 350ml\n",
        "蒜頭碎 1瓣\n" + "芥末籽醬 1小匙\n" + "研磨黑胡椒 適量\n" + "鹽 適量\n" + "巴西利末(可略) 適量\n" + "橄欖油 適量\n",
        "白醬油/日式醬油 1大匙\n" + "味醂 1大匙\n" + "水 50ml\n",
        "研磨黑胡椒 適量\n" + "研磨鹽 適量\n" + "各式乾燥香草 適量\n" + "紅椒粉 適量\n" + "起士粉 適量\n",
        "鹽 適量\n" + "黑胡椒 適量\n", "鹽 少許\n" + "白胡椒粉 少許\n", "鹽 少許\n" + "黑胡椒粉 少許\n" + "麻油 少許\n",
        "鹽 適量\n" + "黑胡椒 適量\n" + "醬油 適量\n", "鹽 2小匙\n", "糖 半匙\n" + "油 2大匙\n" + "米酒 2大匙\n",
        "豆瓣醬 1湯匙\n" + "醬油 1湯匙\n" + "糖 1匙\n" + "白胡椒 1匙\n" + "蔬菜高湯/食用水 適量\n",
        "<調味料> \n" + "白醬油 1.5匙\n" + "胡椒鹽 適量\n" + "香油 1小匙\n" + "\n<醃料> \n" + "米酒 1大匙\n" +
        "醬油 1大匙\n" + "糖 1/4小匙\n" + "白胡椒粉 適量\n" + "太白粉 1小匙\n" + "油(下鍋前加) 適量\n",
        "冰糖 0.5小匙\n" + "鹽 0.5小匙\n" + "醬油 1大匙\n" + "白醋 1小匙\n", "胡椒鹽 適量\n" + "鹽 1小匙\n",
        "鹽 1/2小匙\n" + "白胡椒粉 1/4小匙\n" + "醬油 1大匙\n" + "味醂 1/2大匙\n" + "水 3大匙\n",
        "麻油/香油 1小匙\n" + "白胡椒粉 少許\n" + "醬油 3大匙\n" + "糖1 /2小匙\n" + "水 1.5米杯\n",
        "番茄醬 2匙\n" + "義大利香料 少許",
        "鹽 1/2小匙\n" + "黑胡椒 1小匙\n" + "起士粉 1小匙\n", "鹽巴 少許\n" + "香油 適量\n" + "水 800c.c\n",
        "鹽巴 適量\n" + "黑胡椒 適量\n" + "鰹魚粉/昆布粉 適量\n", "醬油 1小匙\n" + "白胡椒粉 適量\n" + "韓式芝麻油 少許\n",
        "咖哩塊 1/2大匙\n" + "鹽 少許\n" + "黑胡椒 少許\n", "味醂 100c.c\n" + "日式柴魚醬油 100c.c",
        "鹽 1小匙\n" + "醬油膏 1小匙\n" + "米酒 1小匙\n" + "太白粉(可略) 1/2小匙\n" + "炒菜油 1小匙\n",
        "<調味料> \n" + "烹大師(可略) 適量\n" + "鮮味炒手(可略) 適量\n" + "鹽 適量\n" + "\n<醃料> \n" +
        "鹽(1/4小匙) ", "蘋果泥 2大匙\n" + "日式干貝醬油 2大匙\n" + "七味粉 1/2茶匙\n" + "蘋果醋 1大匙\n" + "糖 1茶匙\n" + "香油 少許\n" + "熟白芝麻 1/2茶匙\n",
        "鹽巴 少許\n" + "香油 適量\n" + "醬油膏 適量\n" + "胡椒粉 少許",
        "醬油膏 1大匙\n" + "白胡椒鹽 少許\n" + "蒜末 2瓣\n" + "鮮雞晶 1小匙\n" + "鹽 1小匙\n" + "香油 1大匙\n",
        "糖 1大匙\n" + "新鮮檸檬汁 1大匙\n" + "醬油膏 2大匙\n" + "香油 1大匙\n",
        "醬油 30c.c.\n" + "味霖 30c.c.\n" + "水 500c.c.\n" + "鹽巴 0.5匙\n"
    )

    private val links = arrayOf(
        "https://icook.tw/recipes/254174", "https://icook.tw/recipes/253252", "https://icook.tw/recipes/247316", "https://icook.tw/recipes/250882",
        "https://icook.tw/recipes/279306", "https://icook.tw/recipes/292564", "https://icook.tw/recipes/325250", "https://icook.tw/recipes/245138",
        "https://icook.tw/recipes/232265", "https://icook.tw/recipes/249180", "https://icook.tw/recipes/235067", "https://icook.tw/recipes/331403",
        "https://icook.tw/recipes/121444", "https://icook.tw/recipes/173425", "https://icook.tw/recipes/327034", "https://icook.tw/recipes/168118",
        "https://icook.tw/recipes/152087", "https://icook.tw/recipes/199652", "https://icook.tw/recipes/188409", "https://icook.tw/recipes/122028",
        "https://icook.tw/recipes/205366", "https://icook.tw/recipes/276297", "https://icook.tw/recipes/310211", "https://icook.tw/recipes/264415",
        "https://icook.tw/recipes/208026", "https://icook.tw/recipes/207623", "https://icook.tw/recipes/255243", "https://icook.tw/recipes/271956",
        "https://icook.tw/recipes/251678", "https://icook.tw/recipes/263554", "https://icook.tw/recipes/274579", "https://icook.tw/recipes/306543",
        "https://cookpad.com/tw/%E9%A3%9F%E8%AD%9C/13686421-%E8%B6%85%E7%B0%A1%E6%98%93%E7%9C%81%E9%8C%A2%E6%96%99%E7%90%86%E6%B6%BC%E6%8B%8C%E8%8F%87%E8%8F%87%E9%86%AC"
    )

      private val key_id = arrayOf(
          "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104",
          "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115",
          "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126"
          )

      private val favStatus = arrayOf(
          "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
          "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
          "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"
          )
    ////////////////////////////////////////////////array////////////////////////////////////////////////

    //(第一發生)顯示sidedish_layout的介面
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

        foodiesAdapters = FooditemsAdapter(requireContext(), this, foodList)   //adapter按照位置擺放foodlist裡的所有物品
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
