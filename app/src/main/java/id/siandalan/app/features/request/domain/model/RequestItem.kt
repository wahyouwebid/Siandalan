package id.siandalan.app.features.request.domain.model

data class RequestItem(
    val request: Int?,
    val process: Int?,
    val draft: Int?,
    val drafted: Int?,
    val approved: Int?,
    val finish: Int?,
    val long: Int?,
    val dataApproved: List<DataApprovedItem>? = mutableListOf(),
) {
    data class DataApprovedItem(
        val id: String?,
        val no: String?,
        val name: String?,
        val projectName: String?,
        val category: String?,
        val dateProcess: String?,
        val status: String?,
        val applicationLaterDate: String?,
        val consultant: String?,
    )
}