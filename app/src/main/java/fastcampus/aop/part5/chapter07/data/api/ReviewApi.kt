package fastcampus.aop.part5.chapter07.data.api

import fastcampus.aop.part5.chapter07.domain.model.Review

interface ReviewApi {

    suspend fun getLatestReview(movieId: String): Review?

    suspend fun getAllReviews(movieId: String): List<Review>
}
