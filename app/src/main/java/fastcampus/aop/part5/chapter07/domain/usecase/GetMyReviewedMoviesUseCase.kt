package fastcampus.aop.part5.chapter07.domain.usecase

import fastcampus.aop.part5.chapter07.data.repository.MovieRepository
import fastcampus.aop.part5.chapter07.data.repository.ReviewRepository
import fastcampus.aop.part5.chapter07.data.repository.UserRepository
import fastcampus.aop.part5.chapter07.domain.model.ReviewedMovie
import fastcampus.aop.part5.chapter07.domain.model.User

class GetMyReviewedMoviesUseCase(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository,
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(): List<ReviewedMovie> {
        val user = userRepository.getUser()

        if (user == null) {
            userRepository.saveUser(User())
            return emptyList()
        }

        val reviews = reviewRepository.getAllUserReviews(user.id!!)
            .filter { it.movieId.isNullOrBlank().not() }

        if (reviews.isNullOrEmpty()) {
            return emptyList()
        }

        return movieRepository
            .getMovies(reviews.map { it.movieId!! })
            .mapNotNull { movie ->
                val relatedReview = reviews.find { it.movieId == movie.id }
                relatedReview?.let { ReviewedMovie(movie, it) }
            }
    }
}
