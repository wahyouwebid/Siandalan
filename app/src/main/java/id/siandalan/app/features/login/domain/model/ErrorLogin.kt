package id.siandalan.app.features.login.domain.model

enum class ErrorLogin(val message: String){
    USERNAME_EMPTY("Username cannot be empty"),
    PASSWORD_IS_EMPTY("Password cannot be empty"),
}