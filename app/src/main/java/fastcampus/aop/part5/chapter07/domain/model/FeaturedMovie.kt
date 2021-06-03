package fastcampus.aop.part5.chapter07.domain.model

data class FeaturedMovie(
    val movie: Movie,
    val latestReview: Review?
)
