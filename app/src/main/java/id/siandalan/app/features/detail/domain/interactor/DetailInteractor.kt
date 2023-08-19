package id.siandalan.app.features.detail.domain.interactor

import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.detail.domain.repository.DetailRepository
import id.siandalan.app.features.detail.domain.usecase.DetailUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject


class DetailInteractor @Inject constructor(
    private val repository: DetailRepository,
    private val disposable: CompositeDisposable
): DetailUseCase {
    override fun getDetail(
        id: String?,
        callback: (BaseResultState<ResponseBody>) -> Unit
    ) {
        repository.getDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<ResponseBody>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun getToken(): String {
        return repository.getToken()
    }

    override fun clearDisposable() {
        disposable.clear()
    }

}