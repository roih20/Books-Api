package com.rodrigo.booksapi.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigo.booksapi.R
import com.rodrigo.booksapi.network.BookListModel
import com.rodrigo.booksapi.network.VolumeInfo
import retrofit2.Response

class BookAdapter:RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var bookListData = ArrayList<VolumeInfo>()

    fun setData(newArrayList: ArrayList<VolumeInfo>) {
        this.bookListData = newArrayList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.image_view_book)

        fun bind(data: VolumeInfo) {

            itemView.apply {
                findViewById<TextView>(R.id.text_view_title).text = data.volumeInfo.title
                findViewById<TextView>(R.id.text_view_publisher).text = data.volumeInfo.publisher
                findViewById<TextView>(R.id.text_view_description).text = data.volumeInfo.description
            }

            /*val url = data.volumeInfo?.imageLinks.smallThumbnail
            Glide.with(imageView)
                .load(url)
                .circleCrop()
                .into(imageView)*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bookListData?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return bookListData.size
    }
}