package id.siandalan.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import id.siandalan.app.databinding.UikitEmptyDataBinding

class UIKitEmptyData(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitEmptyDataBinding = UikitEmptyDataBinding.inflate(
        LayoutInflater.from(context), this, true
    )


}