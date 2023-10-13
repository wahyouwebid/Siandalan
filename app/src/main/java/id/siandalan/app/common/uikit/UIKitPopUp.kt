package id.siandalan.app.common.uikit

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import id.siandalan.app.R
import id.siandalan.app.databinding.PopupDialogCommonsBinding

/**
 * Created by wahyouwebid on 16 August 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

class UIKitPopUp(private val activity: AppCompatActivity) :
    Dialog(activity) {

    fun showPopup(action:() -> Unit) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        val binding: PopupDialogCommonsBinding by lazy {
            PopupDialogCommonsBinding.inflate(layoutInflater)
        }
        dialog.setContentView(binding.root)
        val window: Window? = dialog.window
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            btnPositive.setOnClickListener {
                action.invoke()
                dialog.dismiss()
            }
            btnNegative.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    fun showPopupError(
        action:() -> Unit
    ) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        val binding: PopupDialogCommonsBinding by lazy {
            PopupDialogCommonsBinding.inflate(layoutInflater)
        }
        dialog.setContentView(binding.root)
        val window: Window? = dialog.window
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            tvTitlePopup.text = context.getString(R.string.title_error)
            tvSubtitlePopup.text = context.getString(R.string.title_error_des)
            btnPositive.setOnClickListener {
                action.invoke()
                dialog.dismiss()
            }
            btnPositive.text = context.getString(R.string.title_try_again)
            btnNegative.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}