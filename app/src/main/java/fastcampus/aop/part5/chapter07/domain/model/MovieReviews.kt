package fastcampus.aop.part5.chapter07.domain.model

data class MovieReviews(
    val myReview: Review?,
    val othersReview: List<Review>
)
