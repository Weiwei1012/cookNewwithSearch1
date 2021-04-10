package com.cooking.merge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.databinding.FragmentFavoriteBinding
import com.cooking.merge.favoriteDataBase.FavDataBase
import com.cooking.merge.models.FavitemsModel
import com.cooking.merge.models.FooditemsModel
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavitemsAdapter(
    private var context: Context,
    var favclickListener: OnFavItemClickListener,
    private var favitems: ArrayList<FavitemsModel>
) : RecyclerView.Adapter<FavitemsAdapter.FavViewHolder>() {

    lateinit var binding: FragmentFavoriteBinding
    lateinit var favdb: FavDataBase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        favdb = FavDataBase(context)
        binding = FragmentFavoriteBinding.inflate(LayoutInflater.from(context), parent, false)
        return FavViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return favitems.size
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.favIMV.setImageResource(favitems[position].fav_image)
        holder.favTV.text = favitems[position].fav_title

        holder.init(favitems[position], favclickListener)

    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var favIMV: ImageView = itemView.IMV_fav
        var favTV: TextView = itemView.TV_fav
        private val favBtn: Button = itemView.BTN_fav

        init {
            favBtn.setOnClickListener { removefav(it) }
        }

        //remove from fav after click
        private fun removefav(itemView: View) {
            val position = adapterPosition
            val favM: FavitemsModel = favitems[adapterPosition]
            favdb.remove_fav(favM.key_id)

            favitems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, favitems.size)
        }
        //////// 點進內頁的clicklistener func ////////
        fun init(item: FavitemsModel, action: OnFavItemClickListener) {
            favTV.text = item.fav_title
            favIMV.setImageResource(item.fav_image)

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
        //////// 點進內頁的clicklistener func ////////

    }

}
interface OnFavItemClickListener {
    fun onItemClick(item: FavitemsModel, position: Int)
}
