package id.siandalan.app.features.home.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class DocDrafted(
    @SerializedName("file_name")
    @Expose
    val fileName: String?,

    @SerializedName("id_andalalin")
    @Expose
    val idAndalalin: String?,

    @SerializedName("name")
    @Expose
    val name: String?
)