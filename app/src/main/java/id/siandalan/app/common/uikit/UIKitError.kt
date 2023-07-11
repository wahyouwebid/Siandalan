package id.siandalan.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.UikitErrorBinding
import id.siandalan.app.databinding.UikitToolbarBinding

class UIKitError(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitErrorBinding = UikitErrorBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setError(error: String, action:() -> Unit) = with(binding) {
        tvError.text = error
        btnLogin.setOnClickListener {
            action.invoke()
        }
    }
}