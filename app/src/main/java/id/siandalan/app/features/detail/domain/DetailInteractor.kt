package id.siandalan.app.features.detail.domain

import id.siandalan.app.common.base.BaseResultState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject


class DetailInteractor @Inject constructor(
    private val repository: DetailRepository,
    private val disposable: CompositeDisposable
): DetailUseCase {
    override fun getDetail(id: String?, callback: (BaseResultState<ResponseBody>) -> Unit) {
        repository.getDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<ResponseBody>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun postRevise(
        id: String?,
        catatanDraftSk: String?,
        callback: (BaseResultState<ResponseBody>) -> Unit
    ) {
        repository.postRevise(id, catatanDraftSk)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<ResponseBody>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun postTtd(
        id: String?,
        passphrase: String?,
        callback: (BaseResultState<ResponseBody>) -> Unit
    ) {
        repository.postTtd(id, passphrase)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<ResponseBody>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun postApprove(id: String?, callback: (BaseResultState<ResponseBody>) -> Unit) {
        repository.postApprove(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<ResponseBody>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun getModule(): String {
        return repository.getModule()
    }

    override fun clearDisposable() {
        disposable.clear()
    }

}