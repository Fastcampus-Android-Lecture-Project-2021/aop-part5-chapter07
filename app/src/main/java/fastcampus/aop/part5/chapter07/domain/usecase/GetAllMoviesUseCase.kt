package fastcampus.aop.part5.chapter07.domain.usecase

import fastcampus.aop.part5.chapter07.data.repository.MovieRepository
import fastcampus.aop.part5.chapter07.domain.model.Movie

class GetAllMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): List<Movie> = movieRepository.getAllMovies()
}
