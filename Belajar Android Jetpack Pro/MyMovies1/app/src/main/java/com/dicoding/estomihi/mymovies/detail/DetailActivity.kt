package com.dicoding.estomihi.mymovies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.estomihi.mymovies.R
import com.dicoding.estomihi.mymovies.databinding.ActivityDetailBinding
import com.dicoding.estomihi.mymovies.fragment.MovieFragment.Companion.TYPE_MOVIE
import com.dicoding.estomihi.mymovies.fragment.TVShowFragment.Companion.TYPE_TV_SHOW
import com.dicoding.estomihi.mymovies.model.MovieEntity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"

        const val TIME_DELAY = 2000
    }

    private lateinit var binding : ActivityDetailBinding
    private lateinit var itemResult: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        if (type.equals(TYPE_MOVIE)) {
            id?.let { viewModel.setSelectedMovie(it) }
            itemResult = viewModel.getDetailMovie()
        } else if (type.equals(TYPE_TV_SHOW)) {
            id?.let { viewModel.setSelectedTvShow(it) }
            itemResult = viewModel.getDetailTvShow()
        }

        binding.apply {
            tvTitleMtv.text = resources.getString(R.string.details_movie, type)
            tvTitleDetail.text = itemResult.title
            tvNameDirector.text = resources.getString(R.string.director, itemResult.nameDirector)
            tvRating.text = itemResult.rating.toString()
            tvStoryline.text = itemResult.descStory

            Glide.with(this@DetailActivity)
                .load(itemResult.imgPoster)
                .fitCenter()
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(ivPoster)

            imgBack.setOnClickListener {
                onSupportNavigateUp()
            }

            var isStateFav = false

            btnFavorite.setOnClickListener {
                isStateFav = !isStateFav
                if (isStateFav) {
                    Toast.makeText(this@DetailActivity, "You Add ${itemResult.title} To Favorite", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DetailActivity, "You Remove ${itemResult.title} From Favorite", Toast.LENGTH_SHORT).show()
                }

            }

            lottieShare.setOnClickListener {
                lottieShare.playAnimation()
                Toast.makeText(this@DetailActivity, "You share ${itemResult.title} link", Toast.LENGTH_SHORT).show()
                Run.after(TIME_DELAY.toLong()) {
                    val mimeType = "text/plain"
                    ShareCompat.IntentBuilder
                        .from(this@DetailActivity)
                        .setType(mimeType)
                        .setChooserTitle(resources.getString(R.string.share_text))
                        .setText(resources.getString(R.string.share_message, itemResult.title))
                        .startChooser()
                }
            }
        }

    }

    class Run {
        companion object {
            fun after(delay: Long, process: () -> Unit) {
                Handler().postDelayed({
                    process()
                }, delay)
            }
        }
    }
}