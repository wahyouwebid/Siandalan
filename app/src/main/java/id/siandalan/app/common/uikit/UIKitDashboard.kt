package id.siandalan.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import id.siandalan.app.databinding.UikitDashboardBinding

class UIKitDashboard(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitDashboardBinding = UikitDashboardBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setNewRequest(value: String?) {
        binding.tvRequest.text = value
    }

    fun setProgress(value: String?) {
        binding.tvOnProgress.text = value
    }

    fun setVerified(value: String?) {
        binding.tvVerified.text = value
    }

    fun setDraftApproval(value: String?) {
        binding.tvDraftApproval.text = value
    }

    fun setDone(value: String?) {
        binding.tvDone.text = value
    }

    fun setPending(value: String?) {
        binding.tvPending.text = value
    }
}