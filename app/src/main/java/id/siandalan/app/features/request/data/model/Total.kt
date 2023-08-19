package id.siandalan.app.features.request.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class Total(
    @SerializedName("approved")
    @Expose
    val approved: Int?,
    @SerializedName("data_approved")
    @Expose
    val dataApproved: List<DataApproved?>?,
    @SerializedName("data_drafted")
    @Expose
    val dataDrafted: List<DataApproved?>?,
    @SerializedName("draft")
    @Expose
    val draft: Int?,
    @SerializedName("drafted")
    @Expose
    val drafted: Int?,
    @SerializedName("finish")
    @Expose
    val finish: Int?,
    @SerializedName("long")
    @Expose
    val long: Int?,
    @SerializedName("process")
    @Expose
    val process: Int?,
    @SerializedName("request")
    @Expose
    val request: Int?
)