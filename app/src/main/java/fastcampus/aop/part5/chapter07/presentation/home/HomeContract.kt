package fastcampus.aop.part5.chapter07.presentation.home

import fastcampus.aop.part5.chapter07.domain.model.FeaturedMovie
import fastcampus.aop.part5.chapter07.domain.model.Movie
import fastcampus.aop.part5.chapter07.presentation.BasePresenter
import fastcampus.aop.part5.chapter07.presentation.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showMovies(
            featuredMovie: FeaturedMovie?,
            movies: List<Movie>
        )
    }

    interface Presenter : BasePresenter
}
