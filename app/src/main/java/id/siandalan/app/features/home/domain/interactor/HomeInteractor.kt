package id.siandalan.app.features.home.domain.interactor

import id.siandalan.app.features.home.domain.repository.HomeRepository
import id.siandalan.app.features.home.domain.state.HomeResultState
import id.siandalan.app.features.home.domain.usecase.HomeUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeInteractor(
    private val repository: HomeRepository,
    private val disposable: CompositeDisposable,
): HomeUseCase {

    override fun getDataHome(callback: (data: HomeResultState) -> Unit) {
        repository.getDataHome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<HomeResultState> { HomeResultState.Success(it) }
            .onErrorReturn { HomeResultState.Error(it) }
            .startWithItem(HomeResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun clearDisposable() {
        disposable.clear()
    }

}