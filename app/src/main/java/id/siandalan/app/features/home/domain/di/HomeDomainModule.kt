package id.siandalan.app.features.home.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.features.home.domain.interactor.HomeInteractor
import id.siandalan.app.features.home.domain.repository.HomeRepository
import id.siandalan.app.features.home.domain.usecase.HomeUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HomeDomainModule {

    @Provides
    fun provideInteractor(
        repository: HomeRepository,
        disposable: CompositeDisposable,
    ): HomeUseCase {
        return HomeInteractor(repository, disposable)
    }

    @Provides
    @Singleton
    fun provideDisposable(): CompositeDisposable = CompositeDisposable()

}