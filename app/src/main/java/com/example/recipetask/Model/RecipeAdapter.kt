package com.example.recipetask.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipetask.RecipeItem
import com.example.recipetask.databinding.RecipeItemBinding

class RecipeAdapter( val onClickListener:OnClickListener) :
    ListAdapter<RecipeItem, RecipeAdapter.RecipeItemViewHolder>(RecipeItemViewHolder.DiffCallback) {

    class RecipeItemViewHolder(private var binding: RecipeItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipeItem: RecipeItem) {
            binding.recipeitem =recipeItem
            binding.executePendingBindings()
        }
    companion object DiffCallback : DiffUtil.ItemCallback<RecipeItem>() {

        override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem.id == newItem.id
        }

}
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecipeItemViewHolder {
        return RecipeItemViewHolder(RecipeItemBinding.inflate(LayoutInflater.from(parent.context)))    }


    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val recipeItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(recipeItem)
        }
        holder.bind(recipeItem)
    }

    class OnClickListener(val clickListener: (recipeItem: RecipeItem) -> Unit) {
        fun onClick(recipeItem: RecipeItem) = clickListener(recipeItem)
    }
}
