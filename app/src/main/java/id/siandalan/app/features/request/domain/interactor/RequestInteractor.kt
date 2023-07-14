package id.siandalan.app.features.request.domain.interactor

import id.siandalan.app.features.request.domain.repository.RequestRepository
import id.siandalan.app.features.request.domain.state.RequestResultState
import id.siandalan.app.features.request.domain.usecase.RequestUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RequestInteractor(
    private val repository: RequestRepository,
    private val disposable: CompositeDisposable,
): RequestUseCase {

    override fun getDataRequest(callback: (data: RequestResultState) -> Unit) {
        repository.getDataRequest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<RequestResultState> { RequestResultState.Success(it) }
            .onErrorReturn { RequestResultState.Error(it) }
            .startWithItem(RequestResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun clearDisposable() {
        disposable.clear()
    }

}