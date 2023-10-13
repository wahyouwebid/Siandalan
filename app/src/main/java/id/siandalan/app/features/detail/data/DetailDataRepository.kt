package id.siandalan.app.features.detail.data

import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.detail.domain.DetailRepository
import io.reactivex.rxjava3.core.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class DetailDataRepository @Inject constructor(
    private val api: DetailApiService,
    private val sessions: Sessions
): DetailRepository {

    override fun getDetail(id: String?): Observable<ResponseBody> {
        val token = getToken()
        val module = getModule()
        val username = getUsername().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val idRequest = id?.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        return api.getDetail(token, module, username, idRequest)
    }

    override fun postRevise(id: String?, catatanDraftSk: String?): Observable<ResponseBody> {
        val token = getToken()
        val module = "pusat"
        val username = getUsername()
        val stat = "catatan_draft_sk"
        return api.postRevise(token,module, id, stat, catatanDraftSk, username)
    }

    override fun getToken() = sessions.getString(Sessions.accessToken)

    override fun getModule() = sessions.getString(Sessions.module)

    override fun getUsername() = sessions.getString(Sessions.username)
}