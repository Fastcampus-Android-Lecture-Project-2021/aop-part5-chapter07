package fastcampus.aop.part5.chapter07.presentation.reviews

import fastcampus.aop.part5.chapter07.domain.model.Movie
import fastcampus.aop.part5.chapter07.domain.usecase.GetAllMovieReviewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MovieReviewsPresenter(
    override val movie: Movie,
    private val view: MovieReviewsContract.View,
    private val getAllReviews: GetAllMovieReviewsUseCase
) : MovieReviewsContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        view.showMovieInformation(movie)
        fetchReviews()
    }

    override fun onDestroyView() {}

    private fun fetchReviews() = scope.launch {
        try {
            view.showLoadingIndicator()
            view.showReviews(getAllReviews(movie.id!!))
        } catch (exception: Exception) {
            exception.printStackTrace()
            view.showErrorDescription("에러가 발생했어요 😢")
        } finally {
            view.hideLoadingIndicator()
        }
    }
}
