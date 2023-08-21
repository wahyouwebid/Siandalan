package id.siandalan.app.core.mapper

import com.google.gson.Gson
import id.siandalan.app.core.model.ErrorLoginResponse
import id.siandalan.app.core.model.ErrorResponse
import retrofit2.HttpException

object ErrorMapper {
    fun errorMapper(response: HttpException?): ErrorResponse? {
        val errorBody = response?.response()?.errorBody()?.string()
        return Gson().fromJson(errorBody, ErrorResponse::class.java)
    }

    fun errorMapperlogin(response: HttpException?): ErrorLoginResponse? {
        val errorBody = response?.response()?.errorBody()?.string()
        return Gson().fromJson(errorBody, ErrorLoginResponse::class.java)
    }
}