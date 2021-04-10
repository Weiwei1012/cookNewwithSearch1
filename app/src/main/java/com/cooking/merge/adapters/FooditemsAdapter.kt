package com.cooking.merge.adapters

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.R
import com.cooking.merge.databinding.CardviewLayoutBinding
import com.cooking.merge.favoriteDataBase.FavDataBase
import com.cooking.merge.models.FooditemsModel
import kotlinx.android.synthetic.main.cardview_layout.view.*

class FooditemsAdapter(
    private var context: Context,
    var clickListener: OnFoodItemClickListener,
    private var fooditems: ArrayList<FooditemsModel>
) : RecyclerView.Adapter<FooditemsAdapter.FoodItemHolder>() {

    lateinit var binding: CardviewLayoutBinding
    lateinit var favdb: FavDataBase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemHolder {
        favdb = FavDataBase(context)

        ///////create table on first///////
        //SharedPreferences : 儲存簡單資料，好讓APP在下一次執行時可讀取到這些上一次儲存下來的資料。
        //MODE_PRIVATE : 只允許本應用程式內存取，這是最常用參數。
        //getSharedPreferences()方法，產生一個檔名為test.xml的設定儲存檔，並只供本專案(app)可讀取，物件名稱為prefs
        //put基本資料型態(key, value)：boolean, float, int, long, String, Set<String>

        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStart", true)
        if (firstStart) {
            createTableOnFirstStart()
        }
        ///////create table on first///////

        binding = CardviewLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return FoodItemHolder(binding.root)

    }

    override fun getItemCount(): Int {
        return fooditems.size
    }

    override fun onBindViewHolder(holder: FoodItemHolder, position: Int) {
        holder.icons.setImageResource(fooditems[position].iconsChar)
        holder.title.text = fooditems[position].alphaChar
        readCursorData(fooditems[position], holder)
        holder.init(fooditems[position], clickListener)

    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class FoodItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icons: ImageView = itemView.icons_image
        var title: TextView = itemView.title
        val favBtn: Button = itemView.cardview_fav

        init {
            favBtn.setOnClickListener { favonClick(it) }
        }

        private fun favonClick(itemView: View) {
            val foodies: FooditemsModel = fooditems.get(adapterPosition)
            if (foodies.favStatus.equals("0")) {
                foodies.favStatus = "1"
                favdb.insertIntoTheDatabase(
                    foodies.alphaChar,
                    foodies.iconsChar,
                    foodies.key_id,
                    foodies.favStatus
                )
                favBtn.setBackgroundResource(R.drawable.fav_red)

            } else {
                foodies.favStatus = "0"
                favdb.remove_fav(foodies.key_id)
                favBtn.setBackgroundResource(R.drawable.fav_shadow)

            }
        }

        //////// 點進內頁的clicklistener func ////////
        fun init(item: FooditemsModel, action: OnFoodItemClickListener) {
            title.text = item.alphaChar
            icons.setImageResource(item.iconsChar)

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
        //////// 點進內頁的clicklistener func ////////

    }


    private fun readCursorData(
        fooditemsmodel: FooditemsModel,
        viewHolder: FooditemsAdapter.FoodItemHolder
    ) {
        val cursor: Cursor? = favdb.read_all_data(fooditemsmodel.key_id)
        val db: SQLiteDatabase? = favdb.readableDatabase

        try {
            while (cursor!!.moveToNext()) {
                val itemfavstatus =
                    cursor.getString(cursor.getColumnIndex(favdb.FAVORITE_STATUS))
                fooditemsmodel.favStatus = itemfavstatus

                //check fav status
                if (itemfavstatus != null && itemfavstatus == "1") {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.fav_red)
                } else if (itemfavstatus != null && itemfavstatus == "0") {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.fav_shadow)
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed) cursor.close()
            db?.close()
        }
    }

    private fun createTableOnFirstStart() {
        favdb.insertEmpty()
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()                       //edit()：取得 Editor 物件
        editor.putBoolean(
            "firstStart",
            false
        )          //put基本資料型態(key, value)：boolean, float, int, long, String, Set<String>
        editor.apply()                                  //apply()：修改記憶體中的暫存資料，並以非同步式寫入檔案
    }

}


interface OnFoodItemClickListener {
    fun onItemClick(item: FooditemsModel, position: Int)
}



