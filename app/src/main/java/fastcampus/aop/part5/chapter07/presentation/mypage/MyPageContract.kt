package fastcampus.aop.part5.chapter07.presentation.mypage

import fastcampus.aop.part5.chapter07.domain.model.ReviewedMovie
import fastcampus.aop.part5.chapter07.domain.model.User
import fastcampus.aop.part5.chapter07.presentation.BasePresenter
import fastcampus.aop.part5.chapter07.presentation.BaseView

interface MyPageContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showNoDataDescription(message: String)

        fun showErrorDescription(message: String)

        fun showReviewedMovies(reviewedMovies: List<ReviewedMovie>)
    }

    interface Presenter : BasePresenter
}
