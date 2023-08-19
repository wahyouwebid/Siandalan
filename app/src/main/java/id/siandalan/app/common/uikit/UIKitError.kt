package id.siandalan.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import id.siandalan.app.R
import id.siandalan.app.core.mapper.ErrorMapper
import id.siandalan.app.databinding.UikitErrorBinding
import retrofit2.HttpException
import java.io.IOException

class UIKitError(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitErrorBinding = UikitErrorBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setError(error: Throwable, action:() -> Unit) = with(binding) {
        when(error) {
            is HttpException -> {
                tvError.text = ErrorMapper.errorMapper(error)?.error ?: context.getString(R.string.title_error_des)
                btnLogin.setOnClickListener {
                    action.invoke()
                }
            }

            is IOException -> {
                tvError.text = context.getString(R.string.title_no_internet_connection)
                btnLogin.setOnClickListener {
                    action.invoke()
                }
            }
        }
    }
}