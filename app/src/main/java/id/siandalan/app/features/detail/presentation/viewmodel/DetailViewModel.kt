package id.siandalan.app.features.detail.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.detail.domain.usecase.DetailUseCase
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: DetailUseCase
) : ViewModel() {

    val detail: MutableLiveData<BaseResultState<ResponseBody>> by lazy {
        MutableLiveData()
    }

    fun getDetail(id: String?) {
        useCase.getDetail(id) {
            detail.postValue(it)
        }
    }

    fun getToken(): String {
        return useCase.getToken()
    }
}