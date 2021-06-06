package fastcampus.aop.part5.chapter07.presentation.reviews

import fastcampus.aop.part5.chapter07.domain.model.Movie
import fastcampus.aop.part5.chapter07.domain.model.MovieReviews
import fastcampus.aop.part5.chapter07.domain.model.Review
import fastcampus.aop.part5.chapter07.domain.model.User
import fastcampus.aop.part5.chapter07.presentation.BasePresenter
import fastcampus.aop.part5.chapter07.presentation.BaseView

interface MovieReviewsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showMovieInformation(movie: Movie)

        fun showReviews(reviews: MovieReviews)

        fun showErrorToast(message: String)
    }

    interface Presenter : BasePresenter {

        val movie: Movie

        fun requestAddReview(content: String, score: Float)

        fun requestRemoveReview(review: Review)
    }
}
