package com.example.recipetask

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipetask.Model.RecipeAdapter
import com.example.recipetask.RecipeItem


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RecipeItem>?) {
    val adapter = recyclerView.adapter as RecipeAdapter
    adapter.submitList(data)
}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}


@BindingAdapter("recipeApiStatus")
fun bindStatus(statusImageView: ImageView, status: RecipeApiStatus?) {
    when (status) {
        RecipeApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        RecipeApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        RecipeApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
