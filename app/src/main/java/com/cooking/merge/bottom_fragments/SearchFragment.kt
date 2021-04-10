package com.cooking.merge.bottom_fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.MainActivity
import com.cooking.merge.R
import com.cooking.merge.SearchDetailsActivity
import com.cooking.merge.adapters.Permissions.CAMERA_PERMISSIONS
import com.cooking.merge.adapters.SearchitemsAdapter
import kotlinx.android.synthetic.main.fragment_search.*


class  SearchFragment : Fragment(){

    lateinit var adapter: SearchitemsAdapter
    lateinit var hotlist_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        //Open Camera
        val Launch_Camera_btn = view.findViewById<View>(R.id.Launch_Camera_btn) as Button
        Launch_Camera_btn.setOnClickListener {
            if ((activity as MainActivity).checkPermissions(CAMERA_PERMISSIONS[0])) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
            } else {
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotlist_rv = view.findViewById(R.id.hotlist_rv)
        gridLayoutManager = GridLayoutManager(
            hotlist_rv.context, 2,
            LinearLayoutManager.VERTICAL, false
        )
        hotlist_rv.layoutManager = gridLayoutManager
        hotlist_rv.setHasFixedSize(true)

        //將所有可搜尋的食材(存在 strings.xml)載入（此list不會顯示在螢幕上，只供我們在後台管理用的）
        val all_ingredients: Array<String> = resources.getStringArray(R.array.ingredients)

        //設定searchView輸入text的listener
        ingredients_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //當送出input, input的字串為query(資料型態為String?)
            override fun onQueryTextSubmit(query: String?): Boolean {

                //將使用者input的食材（們）依據"(空白鍵)"分別存入ingredients_array
                val ingredients_array = query?.split(" ")!!.toTypedArray()

                //設定一個outputList用來存入符合規定（找得到對應料理）的食材
                //將物件放入array中，其中一種方法就是使用MutableList
                //
                //val outputList: MutableList<String> = ArrayList()
                val outputList: MutableList<String> = ArrayList()
                for (i in ingredients_array.indices){

                    //判斷是否有在可搜尋的食材中
                    if (all_ingredients.contains(ingredients_array[i])){
//                        var ingredients_true = arrayOf<String>()
//                        ingredients_true += ingredients_array[i]

                        //若有，存入輸出array
                        //outputList.add(ingredients_array[i])
                        when(ingredients_array[i]){
                            "燕麥" -> getItemFoodList(outputList,resources.getStringArray(R.array.燕麥))
                            "牛奶" -> getItemFoodList(outputList,resources.getStringArray(R.array.牛奶))
                            "雞蛋"-> getItemFoodList(outputList,resources.getStringArray(R.array.蛋))
                            "蛋" -> getItemFoodList(outputList,resources.getStringArray(R.array.蛋))
                            "青蔥"-> getItemFoodList(outputList,resources.getStringArray(R.array.蔥))
                            "蔥" -> getItemFoodList(outputList,resources.getStringArray(R.array.蔥))
                            "洋蔥" -> getItemFoodList(outputList,resources.getStringArray(R.array.蔥))
                            "菇"  -> getItemFoodList(outputList,resources.getStringArray(R.array.菇))
                            "香菇"  -> getItemFoodList(outputList,resources.getStringArray(R.array.菇))
                            "菇類"  -> getItemFoodList(outputList,resources.getStringArray(R.array.菇))
                            "水果" -> getItemFoodList(outputList,resources.getStringArray(R.array.水果))
                            "果醬" -> getItemFoodList(outputList,resources.getStringArray(R.array.果醬))
                            "奶油" -> getItemFoodList(outputList,resources.getStringArray(R.array.奶油))
                            "吐司" -> getItemFoodList(outputList,resources.getStringArray(R.array.吐司))
                            "去骨雞腿" -> getItemFoodList(outputList,resources.getStringArray(R.array.去骨雞腿肉))
                            "去骨雞腿肉" -> getItemFoodList(outputList,resources.getStringArray(R.array.去骨雞腿肉))
                            "優格" -> getItemFoodList(outputList,resources.getStringArray(R.array.優格))
                            "雞胸" -> getItemFoodList(outputList,resources.getStringArray(R.array.雞胸))
                            "雞胸肉" -> getItemFoodList(outputList,resources.getStringArray(R.array.雞胸))
                            "雞腿" -> getItemFoodList(outputList,resources.getStringArray(R.array.雞胸))
                            "雞腿肉" -> getItemFoodList(outputList,resources.getStringArray(R.array.雞腿))
                        }

                    }
//
                }


                //透過Intent將輸出array傳到DetailsActivity，名稱為"passsearched"
                //若要將array透過intent傳送，需使用putStringArrayListExtra來實作
                val intent = Intent(context, SearchDetailsActivity::class.java)

                // distinct 可過濾在陣列中重複的字串
                intent.putStringArrayListExtra("passsearched", ArrayList(outputList.distinct()))
                //intent.putStringArrayListExtra("passsearched", ArrayList(outputList))

                startActivity(intent)

//                else{
//                    Toast.makeText(applicationContext,"無此食材料理",Toast.LENGTH_LONG).show()
//                }
                return false
            }

            //當query改變（主要作用於：刪掉變成”(空白)“時）一開始下方的recyclerView（也就是我們預設的熱門收尋）
            //需要重新出現
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        //getList() 為搜尋條下方（熱門搜尋）的function

        getList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.extras?.get("data") as Bitmap
            //pictureView.setImageBitmap(takenImage)
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1114
    }

    private fun getList() {
        //設定熱門搜尋的選項們
        val hotlist_items = arrayOf("快速晚餐","高麗菜","馬鈴薯","簡易家常菜","雞肉","超簡單甜點","家常菜 肉","減醣")

        //因為RecyclerView_Adapter所要的參數為ArrayList型態
        val hotList = ArrayList<String>()
        //因此需另外將Array中的string加入到ArrayList中
        for (i in hotlist_items) {
            hotList.add(i)
        }
        //將熱門搜尋RecyclerView的adapter設定為傳入hotList參數的R_A
        adapter = SearchitemsAdapter(hotList)
        hotlist_rv.adapter = adapter
    }

    private fun getItemFoodList(output: MutableList<String>, ingredients_map: Array<String>){
        output.addAll(ingredients_map)
    }

}