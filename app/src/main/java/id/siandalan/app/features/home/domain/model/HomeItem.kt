package id.siandalan.app.features.home.domain.model

/**
 * Created by wahyouwebid on 21 September 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

data class HomeItem(
    val request: Int?,
    val process: Int?,
    val draft: Int?,
    val drafted: Int?,
    val approved: Int?,
    val finish: Int?,
    val long: Int?,
    val draftApprove: Int?,
    val dataApproved: List<DataApprovedItem>? = mutableListOf(),
) {
    data class DataApprovedItem(
        val id: String?,
        val no: String?,
        val name: String?,
        val projectName: String?,
        val category: String?,
        val dateProcess: String?,
    )
}