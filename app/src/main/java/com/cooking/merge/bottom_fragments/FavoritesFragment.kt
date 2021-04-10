package com.cooking.merge.bottom_fragments

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
import com.cooking.merge.adapters.FavitemsAdapter
import com.cooking.merge.adapters.OnFavItemClickListener
import com.cooking.merge.adapters.OnFoodItemClickListener

import com.cooking.merge.favoriteDataBase.FavDataBase
import com.cooking.merge.food_fragment.FoodDetailsf
import com.cooking.merge.models.FavitemsModel
import com.cooking.merge.models.FooditemsModel
import kotlinx.android.synthetic.main.recycler_layout.view.*

class FavoritesFragment: Fragment()  , OnFavItemClickListener {
    private var recyclerView: RecyclerView? = null
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var favList: ArrayList<FavitemsModel>
    private lateinit var favAdapters: FavitemsAdapter
    private lateinit var favdb: FavDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        return inflater.inflate(R.layout.recycler_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        //favdb = context?.let { FavDataBase(it) }!!
        favdb = FavDataBase(requireContext())
        recyclerView = view.my_recycler_view

        ///// design the gridlayout & set recyclerview /////
        gridLayoutManager = GridLayoutManager(
            requireContext(), 3,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        ///// design the gridlayout & set recyclerview /////

        loadData()

        favAdapters = FavitemsAdapter(requireContext(), this, favList)   //adapter按照位置擺放foodlist裡的所有物品
        recyclerView?.adapter = favAdapters

    }

    private fun loadData()
    {
        favList = ArrayList()
        favList.clear()
        val db = favdb.readableDatabase
        val cursor = favdb.select_all_favorite_list()
        try {
            while (cursor!!.moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndex(favdb.ITEM_TITLE))
                val id = cursor.getString(cursor.getColumnIndex(favdb.KEY_ID))
                val image = cursor.getString(cursor.getColumnIndex(favdb.ITEM_IMAGE)).toInt()
                val addfav = FavitemsModel(image, title, id)
                favList.add(addfav)
            }
        } finally {
            if (cursor != null && cursor.isClosed) cursor.close()
            db.close()
        }

    }

    override fun onItemClick(item: FavitemsModel, position: Int) {

        val intent = Intent(context, FoodDetailsf::class.java)

        startActivity(intent)
    }

}






