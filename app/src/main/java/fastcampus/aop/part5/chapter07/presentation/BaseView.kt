package fastcampus.aop.part5.chapter07.presentation

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT
}
