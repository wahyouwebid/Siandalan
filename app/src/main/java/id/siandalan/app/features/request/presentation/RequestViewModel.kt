package id.siandalan.app.features.request.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.siandalan.app.features.home.domain.state.HomeResultState
import id.siandalan.app.features.home.domain.usecase.HomeUseCase
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    val home: MutableLiveData<HomeResultState> by lazy {
        MutableLiveData()
    }

    fun getDataHome() {
        useCase.getDataHome {
            home.postValue(it)
        }
    }
}