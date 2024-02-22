package jroslar.infinitenel.remindnotes.ui.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.domain.model.NoteModel

class NotesAdapter(
    private var noteList: List<NoteModel> = emptyList(),
    private val onItemSelect: (NoteModel) -> Unit
): RecyclerView.Adapter<NoteViewHolder>() {

    fun updateList(newList: List<NoteModel>) {
        noteList = newList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int)
        = holder.bind(noteList[position], onItemSelect)
}