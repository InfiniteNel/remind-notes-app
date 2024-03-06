package jroslar.infinitenel.remindnotes.core.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import jroslar.infinitenel.remindnotes.databinding.DialogSuccessBinding

class SuccessDialog: DialogFragment()  {

    private var title: String = ""
    private var description: String = ""
    private var isDialogCancelable: Boolean = true
    private var positiveAction: Action = Action.Empty
    private var negativeAction: Action = Action.Empty

    companion object {
        fun create(
            title: String = "",
            description: String = "",
            isDialogCancelable: Boolean = true,
            positiveAction: Action = Action.Empty,
            negativeAction: Action = Action.Empty,
        ): SuccessDialog = SuccessDialog().apply {
            this.title = title
            this.description = description
            this.isDialogCancelable = isDialogCancelable
            this.positiveAction = positiveAction
            this.negativeAction = negativeAction
        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window ?: return
        val params = window.attributes

        window.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER) // Centra horizontalmente

        params?.width = ViewGroup.LayoutParams.MATCH_PARENT // Ajusta el ancho
        window.attributes = params

        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogSuccessBinding.inflate(requireActivity().layoutInflater)

        binding.tvTitle.text = title
        binding.tvDescription.text = description
        if (negativeAction == Action.Empty) {
            binding.btnNegative.isGone = true
        } else {
            binding.btnNegative.text = negativeAction.text
            binding.btnNegative.setOnClickListener { negativeAction.onClickListener(this) }
        }
        binding.btnPositive.text = positiveAction.text
        binding.btnPositive.setOnClickListener { positiveAction.onClickListener(this) }
        isCancelable = isDialogCancelable

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setCancelable(isDialogCancelable)
            .create()
    }

    data class Action(
        val text: String,
        val onClickListener: (SuccessDialog) -> Unit
    ) {
        companion object {
            val Empty = Action("") {}
        }
    }
}