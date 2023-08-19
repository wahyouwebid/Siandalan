package id.siandalan.app.features.login.domain.model

data class UserItem(
    val id: String,
    val idRole: String,
    val username: String,
    val name: String,
    val module: String,
    val aksesProvinsi: String,
    val aksesKota: String,
    val position: String,
    val rank: String,
    val nik: String,
    val nip: String,
    val accessToken: String,
)
