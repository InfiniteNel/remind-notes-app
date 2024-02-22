package jroslar.infinitenel.remindnotes.ui.notes.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jroslar.infinitenel.remindnotes.databinding.ItemNoteBinding
import jroslar.infinitenel.remindnotes.domain.model.NoteModel

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemNoteBinding.bind(view)
    fun bind(noteModel: NoteModel, onItemSelect: (NoteModel) -> Unit) {
        binding.tvTitleNote.text = noteModel.title
        binding.tvdescriptionNote.text = noteModel.description
        binding.tvdateNote.text = noteModel.noteDay

        binding.root.setOnClickListener { onItemSelect(noteModel) }
    }
}