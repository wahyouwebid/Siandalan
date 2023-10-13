package id.siandalan.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import id.siandalan.app.databinding.UikitToolbarSubFeaturesBinding

class UIKitToolbarSubFeatures(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitToolbarSubFeaturesBinding = UikitToolbarSubFeaturesBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setToolbar(title: String?, action:() -> Unit) = with(binding) {
        tvToolbar.text = title
        cvBack.setOnClickListener {
            action.invoke()
        }
    }
}