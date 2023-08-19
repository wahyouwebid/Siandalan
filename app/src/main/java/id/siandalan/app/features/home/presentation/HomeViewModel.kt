package id.siandalan.app.features.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.home.domain.model.HomeItem
import id.siandalan.app.features.home.domain.usecase.HomeUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    val home: MutableLiveData<BaseResultState<HomeItem>> by lazy {
        MutableLiveData()
    }

    fun getDataHome() {
        useCase.getDataHome {
            home.postValue(it)
        }
    }
}