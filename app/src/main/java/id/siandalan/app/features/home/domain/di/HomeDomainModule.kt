package id.siandalan.app.features.home.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.features.home.domain.HomeInteractor
import id.siandalan.app.features.home.domain.HomeRepository
import id.siandalan.app.features.home.domain.HomeUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

@InstallIn(SingletonComponent::class)
@Module
class HomeDomainModule {

    @Provides
    fun provideInteractor(
        repository: HomeRepository,
        disposable: CompositeDisposable
    ): HomeUseCase {
        return HomeInteractor(repository, disposable)
    }

}