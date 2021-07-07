package com.dicoding.estomihi.mymovies.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.estomihi.mymovies.ListAdapter.Companion.API_IMAGE_ENDPOINT
import com.dicoding.estomihi.mymovies.ListAdapter.Companion.ENDPOINT_POSTER_SIZE_W185
import com.dicoding.estomihi.mymovies.ListAdapter.Companion.ENDPOINT_POSTER_SIZE_W780
import com.dicoding.estomihi.mymovies.R
import com.dicoding.estomihi.mymovies.databinding.ActivityDetailBinding
import com.dicoding.estomihi.mymovies.fragment.MovieFragment.Companion.TYPE_MOVIE
import com.dicoding.estomihi.mymovies.fragment.TVShowFragment.Companion.TYPE_TV_SHOW
import com.dicoding.estomihi.mymovies.model.MovieEntity
import com.dicoding.estomihi.mymovies.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"

        const val TIME_DELAY = 2000
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.mainDetail.progressBar.visibility = View.VISIBLE
        if (type.equals(TYPE_MOVIE)) {
            supportActionBar?.title = resources.getString(R.string.details_movie, type)
            viewModel.getMovieDetail(id).observe(this, Observer {
                binding.mainDetail.progressBar.visibility = View.GONE
                displayContent(it)
            })
        } else if (type.equals(TYPE_TV_SHOW)) {
            supportActionBar?.title = resources.getString(R.string.details_movie, type)
            viewModel.getTvShowDetail(id).observe(this, Observer {
                binding.mainDetail.progressBar.visibility = View.GONE
                displayContent(it)
            })
        }

    }

    private fun displayContent(data: MovieEntity) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W780 + data.backdropPath)
                .fitCenter()
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(ivBackDrop)

            Glide.with(this@DetailActivity)
                .load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + data.posterPath)
                .fitCenter()
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(mainDetail.ivPoster)

            mainDetail.tvTitleDetail.text = data.title
            mainDetail.tvRating.text = data.voteAverage.toString()
            mainDetail.tvStoryline.text = data.overview


            var isStateFav = false
            mainDetail.btnFavorite.setOnClickListener {
                isStateFav = !isStateFav
                if (isStateFav) {
                    Toast.makeText(this@DetailActivity, "You Add ${data.title} To Favorite", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DetailActivity, "You Remove ${data.title} From Favorite", Toast.LENGTH_SHORT).show()
                }
            }

            lottieShare.setOnClickListener {
                lottieShare.playAnimation()
                Toast.makeText(this@DetailActivity, "You share ${data.title} link", Toast.LENGTH_SHORT).show()
                Run.after(TIME_DELAY.toLong()) {
                    val mimeType = "text/plain"
                    ShareCompat.IntentBuilder
                        .from(this@DetailActivity)
                        .setType(mimeType)
                        .setChooserTitle(resources.getString(R.string.share_text))
                        .setText(resources.getString(R.string.share_message, data.title))
                        .startChooser()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    class Run {
        companion object {
            fun after(delay: Long, process: () -> Unit) {
                Handler(Looper.getMainLooper()).postDelayed({
                    process()
                }, delay)
            }
        }
    }

}