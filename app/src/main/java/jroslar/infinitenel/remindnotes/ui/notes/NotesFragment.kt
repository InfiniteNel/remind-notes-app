package jroslar.infinitenel.remindnotes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import jroslar.infinitenel.remindnotes.databinding.FragmentNotesBinding
import jroslar.infinitenel.remindnotes.ui.notes.adapter.NotesAdapter

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initAdapter()
    }

    private fun initAdapter() {
        notesAdapter = NotesAdapter(onItemSelect = {
        })

        binding.rvListNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }
    }
}