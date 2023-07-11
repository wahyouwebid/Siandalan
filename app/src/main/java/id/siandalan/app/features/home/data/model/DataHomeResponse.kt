package id.siandalan.app.features.home.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import id.siandalan.app.features.home.domain.model.HomeItem

@Keep
data class DataHomeResponse(
    @SerializedName("total")
    @Expose
    val total: Total?
){
    fun toDomain(): HomeItem {
        return HomeItem(
            request = total?.request,
            process = total?.process,
            draft = total?.draft,
            drafted = total?.drafted,
            approved = total?.approved,
            finish = total?.finish,
            long = total?.long,
            dataApproved = this.total?.dataApproved?.map { dataApproved ->
                HomeItem.DataApprovedItem(
                    id = dataApproved?.id,
                    no = dataApproved?.noAndalalin,
                    name = dataApproved?.namaPerseroan,
                    projectName = dataApproved?.projectName,
                    category = dataApproved?.classification,
                    dateProcess = dataApproved?.updatedAt
                )
            }
        )
    }
}