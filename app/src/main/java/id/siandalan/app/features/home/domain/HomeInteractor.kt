package id.siandalan.app.features.home.domain

import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.home.domain.model.HomeItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val repository: HomeRepository,
    private val disposable: CompositeDisposable,
): HomeUseCase {

    override fun getDataHome(callback: (data: BaseResultState<HomeItem>) -> Unit) {
        repository.getDataHome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<HomeItem>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun deviceUpdate(callback: (data: BaseResultState<Any>) -> Unit) {
        repository.deviceUpdate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<Any>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun clearDisposable() {
        disposable.clear()
    }

}