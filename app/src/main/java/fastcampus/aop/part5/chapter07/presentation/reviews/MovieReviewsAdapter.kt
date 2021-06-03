package fastcampus.aop.part5.chapter07.presentation.reviews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import fastcampus.aop.part5.chapter07.R
import fastcampus.aop.part5.chapter07.databinding.ItemMovieInfromationBinding
import fastcampus.aop.part5.chapter07.databinding.ItemReviewBinding
import fastcampus.aop.part5.chapter07.domain.model.Movie
import fastcampus.aop.part5.chapter07.domain.model.Review
import fastcampus.aop.part5.chapter07.extension.toAbbreviatedString
import fastcampus.aop.part5.chapter07.extension.toDecimalFormatString

class MovieReviewsAdapter(private val movie: Movie) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var reviews: List<Review> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> {
                MovieInformationViewHolder(
                    ItemMovieInfromationBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            ITEM_VIEW_TYPE_ITEM -> {
                ReviewViewHolder(parent)
            }
            else -> throw RuntimeException("알 수 없는 ViewType 입니다.")
        }

    override fun getItemCount(): Int = 1 + reviews.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit =
        when (holder) {
            is MovieInformationViewHolder -> {
                holder.bind(movie)
            }
            is ReviewViewHolder -> {
                holder.bind(reviews[position - 1])
            }
            else -> throw RuntimeException("알 수 없는 ViewHolder 입니다.")
        }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> ITEM_VIEW_TYPE_HEADER
            else -> ITEM_VIEW_TYPE_ITEM
        }

    class MovieInformationViewHolder(private val binding: ItemMovieInfromationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Movie) {
            Glide.with(binding.root)
                .load(item.posterUrl)
                .into(binding.posterImageView)

            item.let {
                binding.averageScoreTextView.text =
                    "평점 ${it.averageScore?.toDecimalFormatString("0.0")} (${it.numberOfScore?.toAbbreviatedString()})"
                binding.titleTextView.text = it.title
                binding.additionalInformationTextView.text = "${it.releaseYear}·${it.country}"
                binding.relationsTextView.text = "감독: ${it.director}\n출연진: ${it.actors}"
                binding.genreChipGroup.removeAllViews()
                it.genre?.split(" ")?.forEach { genre ->
                    binding.genreChipGroup.addView(
                        Chip(binding.root.context).apply {
                            isClickable = false
                            text = genre
                        }
                    )
                }
            }
        }
    }

    inner class ReviewViewHolder(
        parent: ViewGroup,
        private val binding: ItemReviewBinding = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Review) {
            item.let {
                binding.authorIdTextView.text = "${it.userId?.take(3)}***"
                binding.scoreTextView.text = it.score?.toDecimalFormatString("0.0")
                binding.contentsTextView.text = "\"${it.content}\""
            }
        }
    }

    companion object {
        const val ITEM_VIEW_TYPE_HEADER = 0
        const val ITEM_VIEW_TYPE_ITEM = 1
    }
}
