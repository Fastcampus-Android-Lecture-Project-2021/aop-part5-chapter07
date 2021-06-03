package fastcampus.aop.part5.chapter07.data.repository

import fastcampus.aop.part5.chapter07.data.api.ReviewApi
import fastcampus.aop.part5.chapter07.domain.model.Review
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ReviewRepositoryImpl(
    private val reviewApi: ReviewApi,
    private val dispatchers: CoroutineDispatcher
) : ReviewRepository {

    override suspend fun getLatestReview(movieId: String): Review? = withContext(dispatchers) {
        reviewApi.getLatestReview(movieId)
    }
}
