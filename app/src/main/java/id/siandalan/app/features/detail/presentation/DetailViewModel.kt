package id.siandalan.app.features.detail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.detail.domain.DetailUseCase
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: DetailUseCase
) : ViewModel() {

    val detail: MutableLiveData<BaseResultState<ResponseBody>> by lazy {
        MutableLiveData()
    }

    val revise: MutableLiveData<BaseResultState<ResponseBody>> by lazy {
        MutableLiveData()
    }

    fun getDetail(id: String?) {
        useCase.getDetail(id) {
            detail.postValue(it)
        }
    }

    fun postRevise(id: String?, textRevise: String) {
        useCase.postRevise(id, textRevise) {
            revise.postValue(it)
        }
    }

}