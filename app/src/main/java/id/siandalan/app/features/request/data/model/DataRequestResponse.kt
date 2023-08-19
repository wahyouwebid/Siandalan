package id.siandalan.app.features.request.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.siandalan.app.features.request.domain.model.RequestItem

@Keep
data class DataRequestResponse(
    @SerializedName("total")
    @Expose
    val total: Total?
){
    fun toDomain(): RequestItem {
        return RequestItem(
            request = total?.request,
            process = total?.process,
            draft = total?.draft,
            drafted = total?.drafted,
            approved = total?.approved,
            finish = total?.finish,
            long = total?.long,
            dataApproved = this.total?.dataApproved?.map { dataApproved ->
                RequestItem.DataApprovedItem(
                    id = dataApproved?.id,
                    no = dataApproved?.noAndalalin,
                    name = dataApproved?.namaPerseroan,
                    projectName = dataApproved?.projectName,
                    category = dataApproved?.classification,
                    dateProcess = dataApproved?.updatedAt,
                    status = dataApproved?.status,
                    applicationLaterDate = dataApproved?.applicationLetterDate,
                    consultant = dataApproved?.leaderName,
                )
            }
        )
    }
}