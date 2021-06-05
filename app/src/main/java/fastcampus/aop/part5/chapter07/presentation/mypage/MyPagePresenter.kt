package fastcampus.aop.part5.chapter07.presentation.mypage

import fastcampus.aop.part5.chapter07.domain.usecase.GetMyReviewedMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MyPagePresenter(
    private val view: MyPageContract.View,
    private val getMyReviewedMovies: GetMyReviewedMoviesUseCase
) : MyPageContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchReviewedMovies()
    }

    override fun onDestroyView() {}

    private fun fetchReviewedMovies() = scope.launch {
        try {
            view.showLoadingIndicator()

            val reviewedMovies = getMyReviewedMovies()
            if (reviewedMovies.isNullOrEmpty()) {
                view.showNoDataDescription("아직 리뷰한 영화가 없어요.\n홈 탭을 눌러 영화를 리뷰해보세요 🙌")
            } else {
                view.showReviewedMovies(reviewedMovies)
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            view.showErrorDescription("에러가 발생했어요 😢")
        } finally {
            view.hideLoadingIndicator()
        }
    }
}
