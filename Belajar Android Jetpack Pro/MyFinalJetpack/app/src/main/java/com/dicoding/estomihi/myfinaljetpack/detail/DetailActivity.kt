package com.dicoding.estomihi.myfinaljetpack.detail

import android.graphics.Color
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
import com.dicoding.estomihi.myfinaljetpack.R
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpackbinding.ActivityDetailBinding
import com.dicoding.estomihi.myfinaljetpack.detail.DetailViewModel
import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieAdapter.Companion.API_IMAGE_ENDPOINT
import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieAdapter.Companion.ENDPOINT_POSTER_SIZE_W185
import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieAdapter.Companion.ENDPOINT_POSTER_SIZE_W780
import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieFragment.Companion.TYPE_MOVIE
import com.dicoding.estomihi.myfinaljetpack.home.tvshow.TvShowFragment.Companion.TYPE_TV_SHOW
import com.dicoding.estomihi.myfinaljetpack.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"

        const val TIME_DELAY = 2000
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel : DetailViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (type.equals(TYPE_MOVIE)) {
            supportActionBar?.title = resources.getString(R.string.details_movie, type)
            viewModel.getMovieDetail(id).observe(this, Observer {
                displayContent(it, null)
            })
        } else if (type.equals(TYPE_TV_SHOW)) {
            supportActionBar?.title = resources.getString(R.string.details_movie, type)
            viewModel.getTvShowDetail(id).observe(this, Observer {
                binding.mainDetail.progressBar.visibility = View.GONE
                displayContent(null, it)
            })
        }

    }

    private fun displayContent(movie: MovieEntity?, tvShow: TvShowEntity?) {
        binding.apply {

            val urlPoster = movie?.posterPath ?: tvShow?.posterPath
            val urlBackdrop = movie?.backdropPath ?: tvShow?.backdropPath
            val statusFavorite = movie?.isFavorite ?: tvShow?.isFavorite

            Glide.with(this@DetailActivity)
                    .load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W780 + urlBackdrop)
                    .fitCenter()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(ivBackDrop)

            Glide.with(this@DetailActivity)
                    .load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + urlPoster)
                    .fitCenter()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(mainDetail.ivPoster)

            mainDetail.tvTitleDetail.text = movie?.title ?: tvShow?.title
            mainDetail.tvRating.text = (movie?.voteAverage ?: tvShow?.voteAverage).toString()
            mainDetail.tvStoryline.text = movie?.overview ?: tvShow?.overview


            statusFavorite?.let { status ->
                setFavoriteState(status)
            }

            mainDetail.btnFavorite.setOnClickListener {
                setFavorite(movie, tvShow)
            }

            lottieShare.setOnClickListener {
                val title = movie?.title ?: tvShow?.title
                lottieShare.playAnimation()
                Toast.makeText(this@DetailActivity, "You share $title link", Toast.LENGTH_SHORT).show()
                Run.after(TIME_DELAY.toLong()) {
                    val mimeType = "text/plain"
                    ShareCompat.IntentBuilder
                            .from(this@DetailActivity)
                            .setType(mimeType)
                            .setChooserTitle(resources.getString(R.string.share_text))
                            .setText(resources.getString(R.string.share_message, title))
                            .startChooser()
                }
            }
        }
    }

    private fun setFavoriteState(status: Boolean) {
        binding.mainDetail.btnFavorite.isChecked = status
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvShowEntity?) {
        if (movie != null) {
            if (movie.isFavorite){
                Toast.makeText(this@DetailActivity, "You Removed ${movie.title} from Favorite", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this@DetailActivity, "You add ${movie.title} to Favorite", Toast.LENGTH_SHORT).show()
            }
            viewModel.setFavoriteMovie(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    Toast.makeText(this@DetailActivity, "You Removed ${tvShow.title} from Favorite", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this@DetailActivity, "You add ${tvShow.title} to Favorite", Toast.LENGTH_SHORT).show()
                }
                viewModel.setFavoriteTvShow(tvShow)
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