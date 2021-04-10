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


class Breakfastf : Fragment() , OnFoodItemClickListener{
    private var recyclerView: RecyclerView? = null
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var foodList: ArrayList<FooditemsModel>
    lateinit var foodiesAdapters: FooditemsAdapter  //繼承FooditemsAdapter

    ////////////////////////////////////////////////array////////////////////////////////////////////////
    private val titles = arrayOf(
        "燕麥優格杯", "草莓蛋吐司", "培根蛋早餐燕麥粥", "全麥鮪魚蛋吐司", "法式吐司",
        "蔥花蛋捲", "帕瑪森花椰菜煎餅", "酪梨豆腐青醬吐司", "五分鐘咖哩優格鮮蔬三明治",
        "3分鐘微波法式吐司", "5分鐘舒芙蕾起司蛋", "太陽蛋吐司佐辣味焦糖奶油醬",
        "起士菇菇炒蛋盒子", "起司煎蛋"
    )

    private val images = arrayOf(
        R.drawable.breakfast1, R.drawable.breakfast2, R.drawable.breakfast11,
        R.drawable.breakfast7, R.drawable.breakfast8, R.drawable.breakfast6,
        R.drawable.breakfast9, R.drawable.breakfast10, R.drawable.breakfast12,
        R.drawable.breakfast13, R.drawable.breakfast14, R.drawable.breakfast15,
        R.drawable.breakfast5, R.drawable.breakfast4
    )

    private val ingredients = arrayOf(
        ("燕麥、優格、水果(可加可不加)"), ("草莓果醬、雞蛋"),
        ("雞蛋 1顆\n" + "洋蔥 1/2顆\n" + "培根 1條\n" + "即食大燕麥片 40g\n" + "高湯塊 1/4顆\n" + "清水 350ml\n" + "青蔥(裝飾用) 少許"),
        ("全麥吐司 4片\n" + "水煮鮪魚罐頭 1/2罐\n" + "煎蛋 2份\n" + "小黃瓜 適量"),
        ("吐司 3片\n" + "雞蛋 2顆\n" + "牛奶 100cc\n" + "奶油 2份\n" + "橄欖油 少許\n" + "蜂蜜 少許"),
        ("蔥 2株\n" + "太白粉 1/4茶匙\n" + "雞蛋 5顆"),
        ("<麵糊部分>\n" + "中筋麵粉 250公克\n" + "帕瑪森乳酪粉 226公克\n" + "鹽 4小匙\n" +
                "黑胡椒 4小匙\n" + "蛋 4顆\n" + "水 4大匙\n" +
                "\n<蔬菜部份>\n" + "花椰菜（白花菜）2棵\n" + "蒜 4瓣\n" + "巴西里 4小匙"),
        ("九層塔(剁碎) 1小株\n" + "酪梨 1/4顆\n" + "豆腐 1/4盒\n" + "大蒜泥 1瓣份"),
        ("吐司 2片\n" + "小黃瓜丁 1/2根份\n" + "牛蕃茄丁 1/2顆份\n" + "奶油 10g\n" + "芫荽籽(可略) 1小匙"),
        ("麵包 2~3片\n" + "牛奶 100ml\n" + "雞蛋 1顆\n" + "砂糖 1小匙\n" + "肉桂粉 1/2小匙\n" + "喜歡的水果 1大匙"),
        "蛋 1顆\n", "吐司或麵包片 1片\n" + "太陽蛋 1顆\n" + "無糖優格（或希臘優格） 1大匙",
        ("鴻禧菇 1包\n" + "雞蛋 2顆\n" + "起士片 2片\n" + "市售蛋餅皮 2片\n" + "牛奶 30g"),
        "起司 2片\n" + "鮮奶 適量\n" + "雞蛋 3顆\n" + "餡料可自選"
    )

    private val sauses = arrayOf(
        "不須調味料", "不須調味料", "鹽 適量\n" + "黑胡椒 適量", "美乃滋 適量", "不須調味料",
        "鹽巴 1/2茶匙 \n" + "白胡椒粉 1/4茶匙", "不須額外的調味料", "鹽 少許\n" + "黑胡椒 少許\n" + "無調味堅果(壓碎) 3顆",
        "<咖哩優格醬>\n" + "希臘式優格 2大匙\n" + "小Ｏ坊純素咖哩粉 1/2小匙\n" + "大蒜粉 1/4小匙\n" + "鹽 1/4小匙\n" + "黑胡椒 1/4小匙",
        "不需要調味料", "起司粉 1大匙\n" + "黑胡椒粉 1小撮",
        "<辣味焦糖奶油醬>\n" + "蜂蜜或糖 1小匙\n" + "奶油 10g\n" + "乾辣椒片 1/4小匙\n" + "白醋 1/2小匙\n",
        ("鹽 適量\n" + "黑胡椒 適量"), ("不須調味料")
    )

    private val links = arrayOf(
        "http://blog.morningshop.tw/post/healthy-breakfast", "http://blog.morningshop.tw/post/healthy-breakfast", "https://icook.tw/recipes/313622",
        "https://icook.tw/recipes/339624", "https://icook.tw/recipes/334694", "https://icook.tw/recipes/340861",
        "https://icook.tw/recipes/330340", "https://icook.tw/recipes/322587", "https://icook.tw/recipes/299252",
        "https://icook.tw/recipes/278519", "https://icook.tw/recipes/269552", "https://icook.tw/recipes/269552",
        "https://icook.tw/recipes/308198", "https://icook.tw/recipes/340891"
    )

    private val key_id = arrayOf(
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"
    )

    private val favStatus = arrayOf(
        "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"
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

    //(第二發生)initialize recyclerView and layout manager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.my_recycler_view

        ///// design the gridlayout & set recyclerview /////
        gridLayoutManager = GridLayoutManager(
            requireContext(), 3,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        ///// design the gridlayout & set recyclerview /////


        foodList = addfood()        //foodList存放圖片及名稱
        foodiesAdapters = FooditemsAdapter(requireContext(), this, foodList)   //adapter按照位置擺放foodlist裡的所有物品
        recyclerView?.adapter = foodiesAdapters

    }

    fun addfood(): ArrayList<FooditemsModel> {
        //array的方法 (way1)
        val addlist: ArrayList<FooditemsModel> = ArrayList()
        for (i in titles.indices)
        {
            val model = FooditemsModel(images[i], titles[i], ingredients[i], sauses[i], links[i], key_id[i], favStatus[i])
            addlist.add(model)
        }
        return addlist
    }
        /*(way2)
        foodList.add(FooditemsModel(R.drawable.breakfast1, "燕麥優格杯"))
        foodList.add(FooditemsModel(R.drawable.breakfast2, "草莓蛋吐司"))
        foodList.add(FooditemsModel(R.drawable.breakfast11, "培根蛋早餐燕麥粥"))
        foodList.add(FooditemsModel(R.drawable.breakfast7, "全麥鮪魚蛋吐司"))
        foodList.add(FooditemsModel(R.drawable.breakfast8, "法式吐司"))
        foodList.add(FooditemsModel(R.drawable.breakfast6, "蔥花蛋捲"))
        foodList.add(FooditemsModel(R.drawable.breakfast9, "帕瑪森花椰菜煎餅"))
        foodList.add(FooditemsModel(R.drawable.breakfast10, "酪梨豆腐青醬吐司"))
        foodList.add(FooditemsModel(R.drawable.breakfast12, "五分鐘咖哩優格鮮蔬三明治"))
        foodList.add(FooditemsModel(R.drawable.breakfast13, "3分鐘微波法式吐司"))
        foodList.add(FooditemsModel(R.drawable.breakfast14, "5分鐘舒芙蕾起司蛋"))
        foodList.add(FooditemsModel(R.drawable.breakfast15, "太陽蛋吐司佐辣味焦糖奶油醬"))
        foodList.add(FooditemsModel(R.drawable.breakfast5, "起士菇菇炒蛋盒子"))
        foodList.add(FooditemsModel(R.drawable.breakfast3, "煎蛋吐司"))
        foodList.add(FooditemsModel(R.drawable.breakfast4, "起司煎蛋"))
        return foodList
        */


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


