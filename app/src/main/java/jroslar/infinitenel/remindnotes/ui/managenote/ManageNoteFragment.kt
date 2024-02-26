package jroslar.infinitenel.remindnotes.ui.managenote

import android.os.Build
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

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
            DatePickerDialog.create { day, month, year -> onDateSelect(day, month, year) }
                .show(requireActivity().supportFragmentManager, null)
        }
    }

    private fun onDateSelect(day: Int, month: Int, year: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val fechaLocalDate = LocalDate.of(year, month, day)
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())
            binding.tvNoteDate.text = fechaLocalDate.format(formatter)
        } else {
            binding.tvNoteDate.text = "%d-%d-%d".format(day, month, year)
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
            binding.tvNoteTitle.text.toString(),
            binding.tvNoteDescription.text.toString(),
            false,
            binding.tvNoteDate.text.toString()
        )
    }
}