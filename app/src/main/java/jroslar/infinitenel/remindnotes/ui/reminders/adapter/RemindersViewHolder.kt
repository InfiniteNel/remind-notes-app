package jroslar.infinitenel.remindnotes.ui.reminders.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jroslar.infinitenel.remindnotes.databinding.ItemReminderBinding
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel

class RemindersViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemReminderBinding.bind(view)

    fun bind(reminderModel: ReminderModel, onItemSelect: (ReminderModel) -> Unit) {
        binding.tvTitleReminder.text = reminderModel.title
        binding.tvTimeReminder.text = reminderModel.timeDay

        if (reminderModel.repeatDay.isEmpty()) {
            binding.tvDaysReminder.text = reminderModel.remindDay
        } else {
            binding.tvDaysReminder.text = reminderModel.repeatDay.toString()
        }

        binding.root.setOnClickListener { onItemSelect(reminderModel) }
    }
}