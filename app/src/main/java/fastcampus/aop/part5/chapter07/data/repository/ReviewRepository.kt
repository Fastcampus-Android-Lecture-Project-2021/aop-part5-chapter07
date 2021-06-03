package fastcampus.aop.part5.chapter07.data.repository

import fastcampus.aop.part5.chapter07.domain.model.Review

interface ReviewRepository {

    suspend fun getLatestReview(movieId: String): Review?
}
