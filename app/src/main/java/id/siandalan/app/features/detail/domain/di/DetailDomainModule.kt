package id.siandalan.app.features.detail.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.features.detail.domain.DetailInteractor
import id.siandalan.app.features.detail.domain.DetailRepository
import id.siandalan.app.features.detail.domain.DetailUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

@InstallIn(SingletonComponent::class)
@Module
class DetailDomainModule {

    @Provides
    fun provideInteractor(
        repository: DetailRepository,
        disposable: CompositeDisposable
    ): DetailUseCase {
        return DetailInteractor(repository, disposable)
    }

}