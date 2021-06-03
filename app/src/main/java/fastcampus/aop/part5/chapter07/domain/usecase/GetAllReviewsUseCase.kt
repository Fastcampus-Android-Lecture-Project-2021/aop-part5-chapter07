package fastcampus.aop.part5.chapter07.domain.usecase

import fastcampus.aop.part5.chapter07.data.repository.ReviewRepository
import fastcampus.aop.part5.chapter07.domain.model.Review

class GetAllReviewsUseCase(private val reviewRepository: ReviewRepository) {

    suspend operator fun invoke(movieId: String): List<Review> =
        reviewRepository.getAllReviews(movieId)

}
