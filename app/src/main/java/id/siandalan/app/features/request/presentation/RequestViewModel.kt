package id.siandalan.app.features.request.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.request.domain.model.RequestItem
import id.siandalan.app.features.request.domain.RequestUseCase
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val useCase: RequestUseCase
) : ViewModel() {

    val request: MutableLiveData<BaseResultState<RequestItem>> by lazy {
        MutableLiveData()
    }

    fun getDataRequest() {
        useCase.getDataRequest {
            request.postValue(it)
        }
    }
}