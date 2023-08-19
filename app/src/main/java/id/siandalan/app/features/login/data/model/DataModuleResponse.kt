package id.siandalan.app.features.login.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.siandalan.app.features.login.domain.model.ModuleItem

@Keep
data class DataModuleResponse(
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("nama")
    @Expose
    val nama: String?
) {
    fun toDomain(): ModuleItem{
        return ModuleItem(
            id,
            nama
        )
    }
}
