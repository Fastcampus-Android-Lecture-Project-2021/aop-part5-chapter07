package fastcampus.aop.part5.chapter07.data.repository

import fastcampus.aop.part5.chapter07.data.api.MovieApi
import fastcampus.aop.part5.chapter07.domain.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val dispatchers: CoroutineDispatcher
) : MovieRepository {

    override suspend fun getAllMovies(): List<Movie> = withContext(dispatchers) {
        movieApi.getAllMovies()
    }

    override suspend fun getMovies(movieIds: List<String>): List<Movie> = withContext(dispatchers) {
        movieApi.getMovies(movieIds)
    }
}
