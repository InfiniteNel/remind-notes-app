package jroslar.infinitenel.remindnotes.ui.reminders

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
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.databinding.FragmentRemindersBinding
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import jroslar.infinitenel.remindnotes.ui.reminders.adapter.RemindersAdapter
import jroslar.infinitenel.remindnotes.ui.reminders.dialogs.ManageRemindersDialog
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RemindersFragment : Fragment() {

    private var _binding: FragmentRemindersBinding? = null
    private val binding get() = _binding!!

    private lateinit var reminderAdapter: RemindersAdapter

    private val viewModel: RemindersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemindersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initUIState()
        initListener()
        viewModel.getRemindersList()
    }

    private fun initListener() {
        binding.fabAddReminder.setOnClickListener {
            showCreateReminderDialog()
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is RemindersState.NoData -> noDataState(it.remindersList)
                        is RemindersState.Success -> successState(it.remindersList)
                    }
                }
            }
        }
    }

    private fun noDataState(remindersList: List<ReminderModel>) {
        binding.tvReminderNoData.isVisible = true
        reminderAdapter.update(remindersList)
    }

    private fun successState(remindersList: List<ReminderModel>) {
        binding.tvReminderNoData.isVisible = false
        reminderAdapter.update(remindersList)
    }

    private fun initAdapter() {
        reminderAdapter = RemindersAdapter( onItemSelected = { model ->
            showEditReminderDialog(model)
        })

        binding.rvListReminder.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reminderAdapter
        }
    }

    private fun showEditReminderDialog(model: ReminderModel) {
        ManageRemindersDialog.create(
            reminderModel = model,
            positiveAction = ManageRemindersDialog.Action(getString(R.string.dialogSaveReminderPositiveAction)) { dialog ->
                viewModel.updateReminder(dialog.reminder)
                dialog.dismiss()
            },
            negativeAction = ManageRemindersDialog.Action(getString(R.string.dialogSaveReminderNegativeAction)) { dialog ->
                viewModel.deleteReminder(dialog.reminder.id)
                dialog.dismiss()
            }
        ).show(requireActivity().supportFragmentManager, null)
    }

    private fun showCreateReminderDialog() {
        ManageRemindersDialog.create(
            positiveAction = ManageRemindersDialog.Action(getString(R.string.dialogInsertReminderPositiveAction)) { dialog ->
                viewModel.insertReminder(dialog.reminder)
                dialog.dismiss()
            },
            negativeAction = ManageRemindersDialog.Action(getString(R.string.dialogInsertReminderNegativeAction)) { dialog ->
                dialog.dismiss()
            }
        ).show(requireActivity().supportFragmentManager, null)
    }
}