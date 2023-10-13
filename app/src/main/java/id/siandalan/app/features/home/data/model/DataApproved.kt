package id.siandalan.app.features.home.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class DataApproved(
    @SerializedName("alamat_perseroan")
    @Expose
    val alamatPerseroan: String?,
    @SerializedName("application_letter_date")
    @Expose
    val applicationLetterDate: String?,
    @SerializedName("application_letter_no")
    @Expose
    val applicationLetterNo: String?,
    @SerializedName("area")
    @Expose
    val area: String?,
    @SerializedName("billing_code")
    @Expose
    val billingCode: String?,
    @SerializedName("capacity")
    @Expose
    val capacity: String?,
    @SerializedName("catatan_draft_sk")
    @Expose
    val catatanDraftSk: String?,
    @SerializedName("catatan_pengajuan")
    @Expose
    val catatanPengajuan: String?,
    @SerializedName("category")
    @Expose
    val category: String?,
    @SerializedName("ch_pembayaran")
    @Expose
    val chPembayaran: String?,
    @SerializedName("classification")
    @Expose
    val classification: String?,
    @SerializedName("copy_of_letter")
    @Expose
    val copyOfLetter: String?,
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any?,
    @SerializedName("draft_final_letter_file")
    @Expose
    val draftFinalLetterFile: String?,
    @SerializedName("draft_upload")
    @Expose
    val draftUpload: String?,
    @SerializedName("email_perusahaan")
    @Expose
    val emailPerusahaan: String?,
    @SerializedName("file_perbaikan_draft")
    @Expose
    val filePerbaikanDraft: String?,
    @SerializedName("final_letter_file")
    @Expose
    val finalLetterFile: String?,
    @SerializedName("final_letter_no")
    @Expose
    val finalLetterNo: String?,
    @SerializedName("h")
    @Expose
    val h: String?,
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("id_company")
    @Expose
    val idCompany: String?,
    @SerializedName("id_consultant")
    @Expose
    val idConsultant: String?,
    @SerializedName("id_proyek")
    @Expose
    val idProyek: String?,
    @SerializedName("kasubdit")
    @Expose
    val kasubdit: String?,
    @SerializedName("kelurahan_perseroan")
    @Expose
    val kelurahanPerseroan: String?,
    @SerializedName("klasifikasi_jalan")
    @Expose
    val klasifikasiJalan: String?,
    @SerializedName("klasifikasi_konsultan")
    @Expose
    val klasifikasiKonsultan: String?,
    @SerializedName("kode_bank")
    @Expose
    val kodeBank: String?,
    @SerializedName("kode_ntb")
    @Expose
    val kodeNtb: String?,
    @SerializedName("kode_ntpn")
    @Expose
    val kodeNtpn: String?,
    @SerializedName("kode_pos_perseroan")
    @Expose
    val kodePosPerseroan: String?,
    @SerializedName("latitude")
    @Expose
    val latitude: String?,
    @SerializedName("leader_name")
    @Expose
    val leaderName: String?,
    @SerializedName("leader_phone")
    @Expose
    val leaderPhone: String?,
    @SerializedName("leader_position")
    @Expose
    val leaderPosition: String?,
    @SerializedName("longitude")
    @Expose
    val longitude: String?,
    @SerializedName("nama_perseroan")
    @Expose
    val namaPerseroan: String?,
    @SerializedName("nama_singkatan")
    @Expose
    val namaSingkatan: String?,
    @SerializedName("nib")
    @Expose
    val nib: String?,
    @SerializedName("no_andalalin")
    @Expose
    val noAndalalin: String?,
    @SerializedName("no_sert_konsultan")
    @Expose
    val noSertKonsultan: String?,
    @SerializedName("nomor_telpon_perseroan")
    @Expose
    val nomorTelponPerseroan: String?,
    @SerializedName("npwp_perseroan")
    @Expose
    val npwpPerseroan: String?,
    @SerializedName("oss")
    @Expose
    val oss: String?,
    @SerializedName("p")
    @Expose
    val p: String?,
    @SerializedName("payment_code")
    @Expose
    val paymentCode: String?,
    @SerializedName("payment_date")
    @Expose
    val paymentDate: String?,
    @SerializedName("payment_expired")
    @Expose
    val paymentExpired: String?,
    @SerializedName("payment_response")
    @Expose
    val paymentResponse: String?,
    @SerializedName("payment_status")
    @Expose
    val paymentStatus: String?,
    @SerializedName("pembangunan")
    @Expose
    val pembangunan: String?,
    @SerializedName("pengajuan_kode_billing")
    @Expose
    val pengajuanKodeBilling: String?,
    @SerializedName("polygon")
    @Expose
    val polygon: String?,
    @SerializedName("price")
    @Expose
    val price: String?,
    @SerializedName("project_address")
    @Expose
    val projectAddress: String?,
    @SerializedName("project_name")
    @Expose
    val projectName: String?,
    @SerializedName("project_province")
    @Expose
    val projectProvince: String?,
    @SerializedName("project_regency")
    @Expose
    val projectRegency: String?,
    @SerializedName("promissory_note_date")
    @Expose
    val promissoryNoteDate: String?,
    @SerializedName("promissory_note_no")
    @Expose
    val promissoryNoteNo: String?,
    @SerializedName("rt_rw_perseroan")
    @Expose
    val rtRwPerseroan: String?,
    @SerializedName("signed_by")
    @Expose
    val signedBy: String?,
    @SerializedName("signed_date")
    @Expose
    val signedDate: Any?,
    @SerializedName("signed_name")
    @Expose
    val signedName: String?,
    @SerializedName("signed_nik")
    @Expose
    val signedNik: String?,
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("status_draft_sk")
    @Expose
    val statusDraftSk: String?,
    @SerializedName("sub_category")
    @Expose
    val subCategory: String?,
    @SerializedName("territory")
    @Expose
    val territory: String?,
    @SerializedName("tgl_dikeluarkan_sk")
    @Expose
    val tglDikeluarkanSk: String?,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String?,
    @SerializedName("updated_oleh")
    @Expose
    val updatedOleh: String?,
    @SerializedName("verifikasi_file_perbaikan_draft")
    @Expose
    val verifikasiFilePerbaikanDraft: String?,
    @SerializedName("verifikasi_pengajuan")
    @Expose
    val verifikasiPengajuan: String?,
    @SerializedName("verifikator_akhir")
    @Expose
    val verifikatorAkhir: String?,
    @SerializedName("verifikator_awal")
    @Expose
    val verifikatorAwal: String?,
    @SerializedName("w")
    @Expose
    val w: String?,
    @SerializedName("x")
    @Expose
    val x: String?,
    @SerializedName("y")
    @Expose
    val y: String?,
    @SerializedName("kapasitas_riil")
    @Expose
    val kapasitasRiil: String?,

)