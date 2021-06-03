package fastcampus.aop.part5.chapter07.data.api

import fastcampus.aop.part5.chapter07.domain.model.Movie

interface MovieApi {

    suspend fun getAllMovies(): List<Movie>
}
