package id.siandalan.app.features.login.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.siandalan.app.features.login.domain.model.UserItem

@Keep
data class DataLoginResponse(
    @SerializedName("message")
    @Expose
    val message: String?,

    @SerializedName("user")
    @Expose
    val user: User,

    @SerializedName("access_token")
    @Expose
    val accessToken: String,
) {
    fun mapAccessToken(): String {
        return accessToken
    }

    data class User(
        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("id_role")
        @Expose
        val idRole: String,

        @SerializedName("username")
        @Expose
        val username: String,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("module")
        @Expose
        val module: String,

        @SerializedName("akses_provinsi")
        @Expose
        val aksesProvinsi: String,

        @SerializedName("akses_kota")
        @Expose
        val aksesKota: String,

        @SerializedName("position")
        @Expose
        val position: String,

        @SerializedName("rank")
        @Expose
        val rank: String,

        @SerializedName("nik")
        @Expose
        val nik: String,

        @SerializedName("nip")
        @Expose
        val nip: String,
    ) {

        fun toDomain(accessToken: String): UserItem{
            return UserItem(
                id,
                idRole,
                username,
                name,
                module,
                aksesProvinsi,
                aksesKota,
                position,
                rank,
                nik,
                nip,
                accessToken
            )
        }
    }
}