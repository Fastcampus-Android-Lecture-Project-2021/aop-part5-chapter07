package fastcampus.aop.part5.chapter07.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import fastcampus.aop.part5.chapter07.domain.model.Review
import kotlinx.coroutines.tasks.await

class ReviewFirestoreApi(
    private val firestore: FirebaseFirestore
) : ReviewApi {

    override suspend fun getLatestReview(movieId: String): Review? =
        firestore.collection("reviews")
            .whereEqualTo("movieId", movieId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()
            .map { it.toObject<Review>() }
            .firstOrNull()

    override suspend fun getAllMovieReviews(movieId: String): List<Review> =
        firestore.collection("reviews")
            .whereEqualTo("movieId", movieId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .await()
            .map { it.toObject<Review>() }

    override suspend fun getAllUserReviews(userId: String): List<Review> =
        firestore.collection("reviews")
            .whereEqualTo("userId", userId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .await()
            .map { it.toObject<Review>() }
}
