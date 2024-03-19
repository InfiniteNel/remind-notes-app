package jroslar.infinitenel.remindnotes.ui.reminders.dialogs

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
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.core.DaysOfWeek
import jroslar.infinitenel.remindnotes.core.dialogs.DatePickerDialog
import jroslar.infinitenel.remindnotes.core.dialogs.TimePickerDialog
import jroslar.infinitenel.remindnotes.databinding.DialogManageRemindersBinding
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import java.util.Calendar

class ManageRemindersDialog: DialogFragment() {

    var reminder: ReminderModel = EmptyReminder
    
    private var isDialogCancelable: Boolean = true
    private var positiveAction: Action = Action.Empty
    private var negativeAction: Action = Action.Empty

    private var _binding: DialogManageRemindersBinding? =  null
    private val binding get() = _binding!!

    companion object {
        val EmptyReminder = ReminderModel(
            title = "",
            description = "",
            important = false,
            remindDay = "",
            repeatDay = emptyList(),
            timeDay = ""
        )

        fun create(
            reminderModel: ReminderModel = EmptyReminder,
            isDialogCancelable: Boolean = true,
            positiveAction: Action = Action.Empty,
            negativeAction: Action = Action.Empty
        ): ManageRemindersDialog = ManageRemindersDialog().apply {
            this.reminder = reminderModel
            this.isDialogCancelable = isDialogCancelable
            this.positiveAction = positiveAction
            this.negativeAction = negativeAction
        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window ?: return
        val params = window.attributes

        window.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER)

        window.attributes = params

        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogManageRemindersBinding.inflate(requireActivity().layoutInflater)

        initData()
        initListener()

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setCancelable(isDialogCancelable)
            .create()
    }

    private fun initListener() {
        binding.tvTimeReminder.setOnClickListener {
            TimePickerDialog.create {
                binding.tvTimeReminder.text = it
            }.show(requireActivity().supportFragmentManager, null)
        }
        binding.tvAddDateTextReminder.setOnClickListener {
            showDatePicker()
        }
        binding.tvAddDateReminder.setOnClickListener {
            showDatePicker()
        }
        binding.ivAddDateReminder.setOnClickListener {
            if (binding.tvAddDateReminder.text.isEmpty()) {
                showDatePicker()
            } else {
                binding.tvAddDateReminder.text = ""
                binding.ivAddDateReminder.setImageResource(R.drawable.baseline_alarm_add_24)
            }
        }

        if (negativeAction == Action.Empty) {
            binding.btnNegative.isGone = true
        } else {
            binding.btnNegative.text = negativeAction.text
            binding.btnNegative.setOnClickListener { negativeAction.onClickListener(this) }
        }
        binding.btnPositive.text = positiveAction.text
        binding.btnPositive.setOnClickListener {
            reminder = getReminderData()
            positiveAction.onClickListener(this)
        }
        isCancelable = isDialogCancelable
    }

    private fun showDatePicker() {
        DatePickerDialog.create {
            binding.ivAddDateReminder.setImageResource(R.drawable.baseline_alarm_off_24)
            binding.tvAddDateReminder.text = it
            reminder = reminder.copy(remindDay = it)
        }.show(requireActivity().supportFragmentManager, null)
    }

    private fun getReminderData(): ReminderModel {
        return reminder.copy(
            title = binding.etTitleReminder.text.toString(),
            description = binding.etdescriptionReminder.text.toString(),
            repeatDay = getListDayOfWeek(),
            timeDay = binding.tvTimeReminder.text.toString()
        )
    }

    private fun getListDayOfWeek(): List<DaysOfWeek> {
        val list: MutableList<DaysOfWeek> = mutableListOf()

        if (binding.btnMonday.isChecked)
            list.add(DaysOfWeek.MONDAY)
        if (binding.btnTuesday.isChecked)
            list.add(DaysOfWeek.TUESDAY)
        if (binding.btnWednesday.isChecked)
            list.add(DaysOfWeek.WEDNESDAY)
        if (binding.btnThursday.isChecked)
            list.add(DaysOfWeek.THURSDAY)
        if (binding.btnFriday.isChecked)
            list.add(DaysOfWeek.FRIDAY)
        if (binding.btnSaturday.isChecked)
            list.add(DaysOfWeek.SATURDAY)
        if (binding.btnSunday.isChecked)
            list.add(DaysOfWeek.SUNDAY)

        return list
    }

    private fun initData() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        binding.etTitleReminder.setText(reminder.title)
        binding.etdescriptionReminder.setText(reminder.description)

        if (reminder.timeDay.isEmpty())
            binding.tvTimeReminder.text = String.format("%02d:%02d", hour, minute)
        else
            binding.tvTimeReminder.text = reminder.timeDay

        if (reminder.remindDay.isNotEmpty()) {
            binding.tvAddDateReminder.text = reminder.remindDay
            binding.ivAddDateReminder.setImageResource(R.drawable.baseline_alarm_off_24)
        }

        reminder.repeatDay.forEach {
            when (it) {
                DaysOfWeek.MONDAY -> binding.btnMonday.isChecked = true
                DaysOfWeek.TUESDAY -> binding.btnTuesday.isChecked = true
                DaysOfWeek.WEDNESDAY -> binding.btnWednesday.isChecked = true
                DaysOfWeek.THURSDAY -> binding.btnThursday.isChecked = true
                DaysOfWeek.FRIDAY -> binding.btnFriday.isChecked = true
                DaysOfWeek.SATURDAY -> binding.btnSaturday.isChecked = true
                DaysOfWeek.SUNDAY -> binding.btnSunday.isChecked = true
            }
        }
    }

    data class Action(
        val text: String,
        val onClickListener: (ManageRemindersDialog) -> Unit
    ) {
        companion object {
            val Empty = Action("") { }
        }
    }
}