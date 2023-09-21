package id.siandalan.app.features.request.domain

import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.request.domain.model.RequestItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RequestInteractor(
    private val repository: RequestRepository,
    private val disposable: CompositeDisposable,
): RequestUseCase {

    override fun getDataRequest(callback: (data: BaseResultState<RequestItem>) -> Unit) {
        repository.getDataRequest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<RequestItem>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun clearDisposable() {
        disposable.clear()
    }

}