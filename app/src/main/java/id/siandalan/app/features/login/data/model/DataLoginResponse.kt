package id.siandalan.app.features.login.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.siandalan.app.features.login.domain.model.LoginItem

@Keep
data class DataLoginResponse(
    @SerializedName("username")
    @Expose
    val username: String?,
    @SerializedName("photo")
    @Expose
    val name: String?
) {
    fun toDomain(): LoginItem{
        return LoginItem(
            username,
            name
        )
    }
}
