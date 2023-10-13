package id.siandalan.app.common.uikit

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import id.siandalan.app.R
import id.siandalan.app.databinding.PopupDialogCommonsBinding
import id.siandalan.app.databinding.PopupDialogSuccessBinding

/**
 * Created by wahyouwebid on 16 August 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

class UIKitPopUpFragment(private val fragment: Fragment) : Dialog(fragment.requireContext()) {

    fun showPopupError(
        action:() -> Unit
    ) {
        val dialog = fragment.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)
        val binding: PopupDialogCommonsBinding by lazy {
            PopupDialogCommonsBinding.inflate(layoutInflater)
        }
        dialog?.setContentView(binding.root)
        val window: Window? = dialog?.window
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            tvTitlePopup.text = context.getString(R.string.title_error)
            tvSubtitlePopup.text = context.getString(R.string.title_error_des)
            btnPositive.setOnClickListener {
                action.invoke()
                dialog?.dismiss()
            }
            btnPositive.text = context.getString(R.string.title_try_again)
            btnNegative.setOnClickListener {
                dialog?.dismiss()
            }
        }

        dialog?.show()
    }

    fun showPopupSuccess() {
        val dialog = fragment.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)
        val binding: PopupDialogSuccessBinding by lazy {
            PopupDialogSuccessBinding.inflate(layoutInflater)
        }
        dialog?.setContentView(binding.root)
        val window: Window? = dialog?.window
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            btnPositive.setOnClickListener {
                dialog?.dismiss()
            }
        }

        dialog?.show()
    }
}