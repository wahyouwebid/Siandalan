package id.siandalan.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.UikitLoadingBinding

class UIKitLoading(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitLoadingBinding = UikitLoadingBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setLoadingHome(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            root.show()
            shimmerHome.show()
            shimmerRequest.hide()
            loadingProgress.hide()
        } else {
            root.hide()
            shimmerHome.hide()
            shimmerRequest.hide()
            loadingProgress.hide()
        }
    }

    fun setLoadingApproval(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            root.show()
            shimmerHome.hide()
            shimmerRequest.show()
            loadingProgress.hide()
        } else {
            root.hide()
            shimmerHome.hide()
            shimmerRequest.hide()
            loadingProgress.hide()
        }
    }

    fun setLoadingProgress(isLoading: Boolean) = with(binding){
        if (isLoading) {
            root.show()
            shimmerHome.hide()
            shimmerRequest.hide()
            loadingProgress.show()
        } else {
            root.hide()
            shimmerHome.hide()
            shimmerRequest.hide()
            loadingProgress.hide()
        }
    }
}