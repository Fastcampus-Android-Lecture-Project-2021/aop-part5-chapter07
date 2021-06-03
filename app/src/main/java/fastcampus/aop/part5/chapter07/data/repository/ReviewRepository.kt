package fastcampus.aop.part5.chapter07.data.repository

import fastcampus.aop.part5.chapter07.domain.model.Review

interface ReviewRepository {

    suspend fun getLatestReview(movieId: String): Review?

    suspend fun getAllReviews(movieId: String): List<Review>
}
