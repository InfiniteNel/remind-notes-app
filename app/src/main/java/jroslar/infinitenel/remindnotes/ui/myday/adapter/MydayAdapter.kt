package jroslar.infinitenel.remindnotes.ui.myday.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.domain.model.NoteModel
import jroslar.infinitenel.remindnotes.domain.model.NotificationModel
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import jroslar.infinitenel.remindnotes.ui.notes.adapter.NoteViewHolder
import jroslar.infinitenel.remindnotes.ui.reminders.adapter.RemindersViewHolder

class MydayAdapter(
    private var notificationList: List<NotificationModel> = emptyList(),
    private val onReminderSelected: (ReminderModel) -> Unit,
    private val onNoteSelected: (NoteModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val typeReminder = 0
    private val typeNote = 1

    fun updateList(list: List<NotificationModel>) {
        notificationList = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (notificationList[position]) {
            is ReminderModel -> typeReminder
            is NoteModel -> typeNote
            else -> throw IllegalArgumentException("Tipo de vista desconocido")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            typeReminder -> RemindersViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
            )

            typeNote -> NoteViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
            )

            else -> throw IllegalArgumentException("Tipo de vista desconocido")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RemindersViewHolder -> holder.bind(
                notificationList[position] as ReminderModel,
                onReminderSelected
            )

            is NoteViewHolder -> holder.bind(
                notificationList[position] as NoteModel,
                onNoteSelected
            )
        }
    }

    override fun getItemCount(): Int =
        notificationList.size

}