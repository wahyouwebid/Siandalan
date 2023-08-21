package id.siandalan.app.core.model

import com.google.gson.annotations.SerializedName

data class ErrorLoginResponse(
    @SerializedName("password")
    val password: String?,
)
