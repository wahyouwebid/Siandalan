package id.siandalan.app.core.mapper

import com.google.gson.Gson
import id.siandalan.app.core.model.ErrorResponse
import retrofit2.HttpException

object ErrorMapper {
    fun errorMapper(response: HttpException?): ErrorResponse? {
        val errorBody = response?.response()?.errorBody()?.string()
        return Gson().fromJson(errorBody, ErrorResponse::class.java)
    }
}