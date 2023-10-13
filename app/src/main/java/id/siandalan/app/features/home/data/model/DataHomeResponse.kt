package id.siandalan.app.features.home.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
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
            draftApprove = total?.drafted?.plus(total.approved ?: 0),
            dataApproved = this.total?.dataDrafted?.map { dataApproved ->
                HomeItem.DataApprovedItem(
                    id = dataApproved?.id,
                    no = dataApproved?.noAndalalin,
                    name = dataApproved?.namaPerseroan,
                    projectName = dataApproved?.projectName,
                    category = dataApproved?.classification,
                    dateProcess = dataApproved?.updatedAt,
                    idConsultant = dataApproved?.idConsultant,
                    alamatPerseroan = dataApproved?.alamatPerseroan,
                    applicationLetterDate = dataApproved?.applicationLetterDate,
                    applicationLetterNo = dataApproved?.applicationLetterNo,
                    area = dataApproved?.area,
                    billingCode = dataApproved?.billingCode,
                    capacity = dataApproved?.capacity,
                    catatanDraftSk = dataApproved?.catatanDraftSk,
                    catatanPengajuan = dataApproved?.catatanPengajuan,
                    chPembayaran = dataApproved?.chPembayaran,
                    classification = dataApproved?.classification,
                    copyOfLetter = dataApproved?.copyOfLetter,
                    createdAt = dataApproved?.createdAt,
                    draftFinalLetterFile = dataApproved?.draftFinalLetterFile,
                    draftUpload = dataApproved?.draftUpload,
                    emailPerusahaan = dataApproved?.emailPerusahaan,
                    filePerbaikanDraft = dataApproved?.filePerbaikanDraft,
                    finalLetterFile = dataApproved?.finalLetterFile,
                    finalLetterNo = dataApproved?.finalLetterNo,
                    idCompany = dataApproved?.idCompany,
                    idProyek = dataApproved?.idProyek,
                    kasubdit = dataApproved?.kasubdit,
                    kelurahanPerseroan = dataApproved?.kelurahanPerseroan,
                    klasifikasiJalan = dataApproved?.klasifikasiJalan,
                    klasifikasiKonsultan = dataApproved?.klasifikasiKonsultan,
                    kodeBank = dataApproved?.kodeBank,
                    kodeNtb = dataApproved?.kodeNtb,
                    kodeNtpn = dataApproved?.kodeNtpn,
                    kodePosPerseroan = dataApproved?.kodePosPerseroan,
                    latitude = dataApproved?.latitude,
                    leaderName = dataApproved?.leaderName,
                    leaderPhone = dataApproved?.leaderPhone,
                    leaderPosition = dataApproved?.leaderPosition,
                    longitude = dataApproved?.longitude,
                    namaSingkatan = dataApproved?.namaSingkatan,
                    nib = dataApproved?.nib,
                    noAndalalin = dataApproved?.noAndalalin,
                    noSertKonsultan = dataApproved?.noSertKonsultan,
                    nomorTelponPerseroan = dataApproved?.nomorTelponPerseroan,
                    npwpPerseroan = dataApproved?.npwpPerseroan,
                    oss = dataApproved?.oss,
                    paymentCode = dataApproved?.paymentCode,
                    paymentDate = dataApproved?.paymentDate,
                    paymentExpired = dataApproved?.paymentExpired,
                    paymentResponse = dataApproved?.paymentResponse,
                    paymentStatus = dataApproved?.paymentStatus,
                    pembangunan = dataApproved?.pembangunan,
                    pengajuanKodeBilling = dataApproved?.pengajuanKodeBilling,
                    polygon = dataApproved?.polygon,
                    price = dataApproved?.price,
                    projectAddress = dataApproved?.projectAddress,
                    projectProvince = dataApproved?.projectProvince,
                    projectRegency = dataApproved?.projectRegency,
                    promissoryNoteDate = dataApproved?.promissoryNoteDate,
                    promissoryNoteNo = dataApproved?.promissoryNoteNo,
                    rtRwPerseroan = dataApproved?.rtRwPerseroan,
                    signedBy = dataApproved?.signedBy,
                    signedName = dataApproved?.signedName,
                    signedNik = dataApproved?.signedNik,
                    status = dataApproved?.status,
                    statusDraftSk = dataApproved?.statusDraftSk,
                    subCategory = dataApproved?.subCategory,
                    territory = dataApproved?.territory,
                    tglDikeluarkanSk = dataApproved?.tglDikeluarkanSk,
                    updatedAt = dataApproved?.updatedAt,
                    updatedOleh = dataApproved?.updatedOleh,
                    verifikasiFilePerbaikanDraft = dataApproved?.verifikasiFilePerbaikanDraft,
                    verifikasiPengajuan = dataApproved?.verifikasiPengajuan,
                    verifikatorAkhir = dataApproved?.verifikatorAkhir,
                    verifikatorAwal = dataApproved?.verifikatorAwal,
                    kapasitasRiil = dataApproved?.kapasitasRiil
                )
            }
        )
    }
}