package fastcampus.aop.part5.chapter07.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fastcampus.aop.part5.chapter07.data.api.MovieApi
import fastcampus.aop.part5.chapter07.data.api.MovieFirestoreApi
import fastcampus.aop.part5.chapter07.data.api.ReviewApi
import fastcampus.aop.part5.chapter07.data.api.ReviewFirestoreApi
import fastcampus.aop.part5.chapter07.data.repository.MovieRepository
import fastcampus.aop.part5.chapter07.data.repository.MovieRepositoryImpl
import fastcampus.aop.part5.chapter07.data.repository.ReviewRepository
import fastcampus.aop.part5.chapter07.data.repository.ReviewRepositoryImpl
import fastcampus.aop.part5.chapter07.domain.usecase.GetAllMoviesUseCase
import fastcampus.aop.part5.chapter07.domain.usecase.GetRandomFeaturedMovieUseCase
import fastcampus.aop.part5.chapter07.presentation.home.HomeFragment
import fastcampus.aop.part5.chapter07.presentation.home.HomeContract
import fastcampus.aop.part5.chapter07.presentation.home.HomePresenter
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {
    single { Dispatchers.IO }
}

val dataModule = module {
    single { Firebase.firestore }

    single<MovieApi> { MovieFirestoreApi(get()) }
    single<ReviewApi> { ReviewFirestoreApi(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<ReviewRepository> { ReviewRepositoryImpl(get(), get()) }
}

val domainModule = module {
    factory { GetRandomFeaturedMovieUseCase(get(), get()) }
    factory { GetAllMoviesUseCase(get()) }
}

val presenterModule = module {
    scope<HomeFragment> {
        scoped<HomeContract.Presenter> { HomePresenter(getSource(), get(), get()) }
    }
}
