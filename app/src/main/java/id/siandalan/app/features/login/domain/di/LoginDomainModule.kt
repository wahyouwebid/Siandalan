package id.siandalan.app.features.login.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.features.login.domain.LoginInteractor
import id.siandalan.app.features.login.domain.LoginRepository
import id.siandalan.app.features.login.domain.LoginUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
@InstallIn(SingletonComponent::class)
@Module
class LoginDomainModule {

    @Provides
    fun provideInteractor(
        repository: LoginRepository,
        disposable: CompositeDisposable,
    ): LoginUseCase {
        return LoginInteractor(repository, disposable)
    }
}