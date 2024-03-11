package jroslar.infinitenel.remindnotes.ui.reminders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel

class RemindersAdapter(
    private var remindersList: List<ReminderModel> = emptyList(),
    private val onItemSelected: (ReminderModel) -> Unit
): RecyclerView.Adapter<RemindersViewHolder>() {

    fun update(list: List<ReminderModel>) {
        remindersList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemindersViewHolder {
        return RemindersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        )
    }

    override fun getItemCount(): Int = remindersList.size

    override fun onBindViewHolder(holder: RemindersViewHolder, position: Int) {
        holder.bind(remindersList[position], onItemSelected)
    }
}