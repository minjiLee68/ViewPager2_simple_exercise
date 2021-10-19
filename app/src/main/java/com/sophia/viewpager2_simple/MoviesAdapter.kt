package com.sophia.viewpager2_simple

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sophia.viewpager2_simple.databinding.ItemContainerMovieBinding

class MoviesAdapter(private var movies: ArrayList<Movie>) :
    ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(

        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.name == newItem.name && oldItem.poster == newItem.poster
                        && oldItem.releaseDate == newItem.releaseDate

        }

    ) {

    inner class MovieViewHolder(
        private val binding: ItemContainerMovieBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setMovie(movie: Movie) {
            binding.run {
                imagePoster.setImageResource(movie.poster!!)
                textName.text = movie.name
                textCategory.text = movie.category
                textReleaseDate.text = movie.releaseDate
                ratingBar.rating = movie.rating!!
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ItemContainerMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setMovie(movies[position])
    }

}