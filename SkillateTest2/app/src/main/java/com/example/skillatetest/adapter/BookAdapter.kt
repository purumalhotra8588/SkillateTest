package com.example.skillatetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.skillatetest.R
import com.example.skillatetest.data.Book

class BookAdapter(
    val context: Context,
    var jsonString: List<Book>,
    var listener: OnItemClickListener,
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    companion object {
        var mClickListener: BtnClickListener? = null
    }

    open interface BtnClickListener {
        fun onBtnClick(position: Int)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvHits: TextView = view.findViewById(R.id.tvHits)
        val skuThumbnail: ImageView = view.findViewById(R.id.skuThumbnail)
        val ivStatus: ImageView = view.findViewById(R.id.ivStatus)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_book, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentBook = jsonString[position]
        viewHolder.tvTitle.text = currentBook.title
        viewHolder.tvHits.text = "${currentBook.hits} Hits"


        Glide.with(context)
            .load(currentBook.image)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(false)
            .into(viewHolder.skuThumbnail)

        // Set click listeners using the extension function
        viewHolder.itemView.setOnClickListener {
            listener.onItemClick(it, position, currentBook)
        }

        val favoriteImageResource = if (currentBook.isFavorite) {
            R.drawable.baseline_favorite_24 // Your filled favorite image resource
        } else {
            R.drawable.baseline_favorite_border_24 // Your outline favorite image resource
        }
        viewHolder.ivStatus.setImageResource(favoriteImageResource)
        viewHolder.ivStatus.setOnClickListener {
            currentBook.isFavorite = !currentBook.isFavorite // Toggle the favorite state
            val updatedImageResource = if (currentBook.isFavorite) {
                R.drawable.baseline_favorite_24
            } else {
                R.drawable.baseline_favorite_border_24
            }
            viewHolder.ivStatus.setImageResource(updatedImageResource)

            // Notify the adapter about the data change
            notifyItemChanged(position)
        }


    }

    fun updateData(newBookList: List<Book>) {
        jsonString = newBookList
        notifyDataSetChanged()
    }


    override fun getItemCount() = jsonString.size

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit):
            T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}
