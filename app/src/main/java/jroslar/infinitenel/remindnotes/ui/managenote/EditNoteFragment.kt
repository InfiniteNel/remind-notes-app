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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.databinding.FragmentEditNoteBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditNoteFragment : Fragment(), MenuProvider {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    private val args: EditNoteFragmentArgs by navArgs()

    private val viewmodel: EditNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initMenu()
        initData()
    }

    private fun initData() {
        lifecycleScope.launch {
            viewmodel.data.collect {note ->
                binding.apply {
                    tvEditNoteTitle.setText(note?.title)
                    tvEditNoteDate.text = note?.noteDay
                    tvEditNoteDescription.setText(note?.description)
                }
            }
        }
        viewmodel.getNote(args.noteId)
    }

    private fun initMenu() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.edit_note_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.editNoteDelete -> true
            R.id.editNoteSave -> true
            else -> false
        }
    }
}