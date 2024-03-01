package jroslar.infinitenel.remindnotes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jroslar.infinitenel.remindnotes.databinding.FragmentNotesBinding
import jroslar.infinitenel.remindnotes.ui.notes.adapter.NotesAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: NotesViewModel by viewModels()

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
        initUiState()
        initListener()
    }

    private fun initListener() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(
                NotesFragmentDirections.actionNavNoteToManageNoteFragment()
            )
        }
    }

    private fun initUiState() {
        lifecycleScope.launch {
            notesViewModel.getListNotes()

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                notesViewModel.state.collect {
                    when (it) {
                        is NotesState.NoData -> noDataState(it)
                        is NotesState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(it: NotesState.Success) {
        binding.tvNoteNoData.isVisible = false
        notesAdapter.updateList(it.noteList)
    }

    private fun noDataState(it: NotesState.NoData) {
        binding.tvNoteNoData.isVisible = true
        notesAdapter.updateList(it.noteList)
    }

    private fun initAdapter() {
        notesAdapter = NotesAdapter(onItemSelect = {
            findNavController().navigate(NotesFragmentDirections.actionNavNoteToEditNoteFragment(it.id))
        })

        binding.rvListNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }
    }
}