package id.siandalan.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.UikitLoadingBinding
import id.siandalan.app.databinding.UikitToolbarBinding

class UIKitLoading(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitLoadingBinding = UikitLoadingBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) root.show() else root.hide()
    }
}