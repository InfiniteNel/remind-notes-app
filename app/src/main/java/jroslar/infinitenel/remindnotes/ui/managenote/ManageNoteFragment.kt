package jroslar.infinitenel.remindnotes.ui.managenote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.core.dialogs.DatePickerDialog
import jroslar.infinitenel.remindnotes.databinding.FragmentManageNoteBinding
import jroslar.infinitenel.remindnotes.domain.model.NoteModel

@AndroidEntryPoint
class ManageNoteFragment : Fragment(), MenuProvider {

    private var _binding: FragmentManageNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ManageNoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initMenu()
        initListener()
    }

    private fun initListener() {
        binding.tvNoteDate.setOnClickListener {
            DatePickerDialog.create { date -> binding.tvNoteDate.text = date }
                .show(requireActivity().supportFragmentManager, null)
        }
    }

    private fun initMenu() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.note_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.saveNote -> {
                viewModel.insertNote(getNoteData())
                findNavController().navigateUp()
                true
            }
            else -> true
        }
    }

    private fun getNoteData(): NoteModel {
        return NoteModel(
            title = binding.tvNoteTitle.text.toString(),
            description = binding.tvNoteDescription.text.toString(),
            important = false,
            noteDay = binding.tvNoteDate.text.toString()
        )
    }
}