package fastcampus.aop.part5.chapter07.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.aop.part5.chapter07.databinding.ItemFeaturedMovieBinding
import fastcampus.aop.part5.chapter07.databinding.ItemMovieBinding
import fastcampus.aop.part5.chapter07.domain.model.FeaturedMovie
import fastcampus.aop.part5.chapter07.domain.model.Movie
import fastcampus.aop.part5.chapter07.extension.dip
import fastcampus.aop.part5.chapter07.extension.toAbbreviatedString
import fastcampus.aop.part5.chapter07.extension.toDecimalFormatString

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<DataItem> = emptyList()
    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_SECTION_HEADER -> {
                TitleItemViewHolder(parent.context)
            }
            ITEM_VIEW_TYPE_FEATURED -> {
                FeaturedMovieItemViewHolder(
                    ItemFeaturedMovieBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            ITEM_VIEW_TYPE_ITEM -> {
                MovieItemViewHolder(
                    ItemMovieBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> throw RuntimeException("알 수 없는 ViewType 입니다.")
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemValue = data[position].value
        when {
            holder is TitleItemViewHolder && itemValue is String -> {
                holder.bind(itemValue)
            }
            holder is FeaturedMovieItemViewHolder && itemValue is FeaturedMovie -> {
                holder.bind(itemValue)
            }
            holder is MovieItemViewHolder && itemValue is Movie -> {
                holder.bind(itemValue)
            }
            else -> throw RuntimeException("알 수 없는 ViewHolder 입니다.")
        }
    }

    override fun getItemViewType(position: Int): Int = when (data[position].value) {
        is String -> {
            ITEM_VIEW_TYPE_SECTION_HEADER
        }
        is FeaturedMovie -> {
            ITEM_VIEW_TYPE_FEATURED
        }
        else -> {
            ITEM_VIEW_TYPE_ITEM
        }
    }

    fun addData(featuredMovie: FeaturedMovie?, movies: List<Movie>) {
        val newData = mutableListOf<DataItem>()

        featuredMovie?.let {
            newData += DataItem("🔥 요즘 핫한 영화")
            newData += DataItem(it)
        }

        newData += DataItem("🍿 이 영화들은 보셨나요?")
        newData += movies.map { DataItem(it) }

        data = newData
    }

    inner class TitleItemViewHolder(context: Context) : RecyclerView.ViewHolder(
        TextView(context).apply {
            textSize = 20f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.BLACK)
            setPadding(dip(12f), dip(6f), dip(12f), dip(6f))
        }
    ) {

        fun bind(item: String) {
            (itemView as? TextView)?.text = item
        }
    }

    inner class FeaturedMovieItemViewHolder(private val binding: ItemFeaturedMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                (data[adapterPosition].value as? FeaturedMovie)?.movie?.let {
                    onMovieClickListener?.invoke(it)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: FeaturedMovie) {
            Glide.with(binding.root)
                .load(item.movie.posterUrl)
                .into(binding.posterImageView)

            binding.scoreCountTextView.text = item.movie.numberOfScore?.toAbbreviatedString()
            binding.averageScoreTextView.text = item.movie.averageScore?.toDecimalFormatString("0.0")

            item.latestReview?.let { review ->
                binding.latestReviewLabelTextView.text =
                    if (review.userId.isNullOrBlank()) {
                        "🌟 따끈따끈한 후기"
                    } else {
                        "- ${review.userId.take(3)}*** -"
                    }

                binding.latestReviewTextView.text = "\"${review.content}\""
            }
        }
    }

    inner class MovieItemViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                (data[adapterPosition].value as? Movie)?.let {
                    onMovieClickListener?.invoke(it)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            Glide.with(binding.root)
                .load(movie.posterUrl)
                .into(binding.posterImageView)

            movie.let {
                binding.titleTextView.text = it.title
                binding.additionalInformationTextView.text = "${it.releaseYear}·${it.country}"
            }
        }
    }

    data class DataItem(val value: Any)

    companion object {
        const val ITEM_VIEW_TYPE_SECTION_HEADER = 0
        const val ITEM_VIEW_TYPE_FEATURED = 1
        const val ITEM_VIEW_TYPE_ITEM = 2
    }
}
