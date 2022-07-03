package com.example.android.articlehome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.articlehome.R
import com.example.android.articlehome.databinding.ArticleItemBinding
import com.example.android.articlehome.models.ArticleModel


class ArticlesAdapter(private val clickListener:ArticlesListener) : ListAdapter<ArticleModel, ArticlesAdapter.ViewHolder>(AsteroidDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(clickListener,item)
    }

    class ViewHolder private constructor(private val binding: ArticleItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ArticlesListener, item: ArticleModel) {
            binding.article = item
            binding.executePendingBindings()
            binding.clickIt=clickListener
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding : ArticleItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context)
                    ,R.layout.article_item,parent,false)
               return ViewHolder(binding)
           }
        }

    }
    class AsteroidDiffCallback: DiffUtil.ItemCallback<ArticleModel>() {
        override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem==newItem
        }
    }
    class ArticlesListener(val clickListener:(article:ArticleModel)->Unit){
        fun onClick (article:ArticleModel)=clickListener(article)
    }

}