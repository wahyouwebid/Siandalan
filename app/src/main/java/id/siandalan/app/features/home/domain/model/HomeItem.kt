package id.siandalan.app.features.home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
    @Parcelize
    data class DataApprovedItem(
        val id: String?,
        val no: String?,
        val name: String?,
        val projectName: String?,
        val category: String?,
        val dateProcess: String?,
        val idConsultant: String?,

        val alamatPerseroan: String?,
        val applicationLetterDate: String?,
        val applicationLetterNo: String?,
        val area: String?,
        val billingCode: String?,
        val capacity: String?,
        val catatanDraftSk: String?,
        val catatanPengajuan: String?,
        val chPembayaran: String?,
        val classification: String?,
        val copyOfLetter: String?,
        val createdAt: String?,
        val draftFinalLetterFile: String?,
        val draftUpload: String?,
        val emailPerusahaan: String?,
        val filePerbaikanDraft: String?,
        val finalLetterFile: String?,
        val finalLetterNo: String?,
        val idCompany: String?,
        val idProyek: String?,
        val kasubdit: String?,
        val kelurahanPerseroan: String?,
        val klasifikasiJalan: String?,
        val klasifikasiKonsultan: String?,
        val kodeBank: String?,
        val kodeNtb: String?,
        val kodeNtpn: String?,
        val kodePosPerseroan: String?,
        val latitude: String?,
        val leaderName: String?,
        val leaderPhone: String?,
        val leaderPosition: String?,
        val longitude: String?,
        val namaSingkatan: String?,
        val nib: String?,
        val noAndalalin: String?,
        val noSertKonsultan: String?,
        val nomorTelponPerseroan: String?,
        val npwpPerseroan: String?,
        val oss: String?,
        val paymentCode: String?,
        val paymentDate: String?,
        val paymentExpired: String?,
        val paymentResponse: String?,
        val paymentStatus: String?,
        val pembangunan: String?,
        val pengajuanKodeBilling: String?,
        val polygon: String?,
        val price: String?,
        val projectAddress: String?,
        val projectProvince: String?,
        val projectRegency: String?,
        val promissoryNoteDate: String?,
        val promissoryNoteNo: String?,
        val rtRwPerseroan: String?,
        val signedBy: String?,
        val signedName: String?,
        val signedNik: String?,
        val status: String?,
        val statusDraftSk: String?,
        val subCategory: String?,
        val territory: String?,
        val tglDikeluarkanSk: String?,
        val updatedAt: String?,
        val updatedOleh: String?,
        val verifikasiFilePerbaikanDraft: String?,
        val verifikasiPengajuan: String?,
        val verifikatorAkhir: String?,
        val verifikatorAwal: String?,
        val kapasitasRiil: String?,
        val dataDocument: List<DocumentItem> = mutableListOf(
            DocumentItem(
                "Surat Permohonan Persetujuan Andalalin",
                "uploads/andalalin/permohonan/ID_1538/surat_andalalin_32742_95_-_2.%20KATA%20PENGANTAR%20-%20SPBU%20PT.%20Tawakal%20Tsani%20Makmur.pdf",
                "KATA PENGANTAR - SPBU PT. Tawakal Tsani Makmur.pdf"
            ),
            DocumentItem(
                "Surat Bukti Kepemilikan atau Penguasaan Lahan",
                "uploads/andalalin/permohonan/ID_1538/gambar_diusulkan_32756_28_-_BAB%206%20-%20SPBU%20PT.%20Tawakal%20Tsani%20Makmur.pdf",
                "DAFTAR ISI - SPBU PT. Tawakal Tsani Makmur.pdf"
            )
        ),
    ): Parcelable {
        @Parcelize
        data class DocumentItem(
            val titleName: String,
            val documentLink: String,
            val documentName: String,
        ): Parcelable
    }
}