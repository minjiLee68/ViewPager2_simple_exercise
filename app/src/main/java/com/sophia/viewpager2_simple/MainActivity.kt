package com.sophia.viewpager2_simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.sophia.viewpager2_simple.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMoviesViewPager()
    }

    private fun setupMoviesViewPager() {
        val moviesViewPager = binding.moviesViewPager
        moviesViewPager.clipToPadding = false
        moviesViewPager.clipChildren = false
        moviesViewPager.offscreenPageLimit = 3
        moviesViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85F + r * 0.15F
        }
        moviesViewPager.setPageTransformer(compositePageTransformer)
        movieAdapter = MoviesAdapter(getMovies())
        moviesViewPager.adapter = movieAdapter
        movieAdapter.submitList(getMovies())
    }

    private fun getMovies(): ArrayList<Movie> {
        val movies: ArrayList<Movie> = ArrayList()

        val beautyInside = Movie()
        beautyInside.poster = R.drawable.poster_1
        beautyInside.name = "기생충"
        beautyInside.category = "드라마"
        beautyInside.releaseDate = "2019-05-30"
        beautyInside.rating = 4.6F
        movies.add(beautyInside)

        val bornToBeBlue = Movie()
        bornToBeBlue.poster = R.drawable.poster_2
        bornToBeBlue.name = "본투비 블루"
        bornToBeBlue.category = "드라마"
        bornToBeBlue.releaseDate = "2016-06-09"
        bornToBeBlue.rating = 4.2F
        movies.add(bornToBeBlue)

        val theLobster = Movie()
        theLobster.poster = R.drawable.poster_3
        theLobster.name = "더 랍스터"
        theLobster.category = "멜로/드라마"
        theLobster.releaseDate = "2015-10-29"
        theLobster.rating = 4.2F
        movies.add(theLobster)

        val wonder = Movie()
        wonder.poster = R.drawable.poster_4
        wonder.name = "원더"
        wonder.category = "드라마"
        wonder.releaseDate = "2017-12-27"
        wonder.rating = 5F
        movies.add(wonder)

        val aboutTime = Movie()
        aboutTime.poster = R.drawable.poster_5
        aboutTime.name = "어바웃 타임"
        aboutTime.category = "멜로/로맨스"
        aboutTime.releaseDate = "2013-12-05"
        aboutTime.rating = 4.7F
        movies.add(aboutTime)

        return movies
    }
}