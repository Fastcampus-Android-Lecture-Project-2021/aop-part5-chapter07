package fastcampus.aop.part5.chapter07.data.repository

import fastcampus.aop.part5.chapter07.domain.model.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>

    suspend fun getMovies(movieIds: List<String>): List<Movie>
}
