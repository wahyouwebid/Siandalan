package id.siandalan.app.features.login.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = ""
)
