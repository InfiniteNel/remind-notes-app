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
import jroslar.infinitenel.remindnotes.databinding.FragmentRemindersBinding
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import jroslar.infinitenel.remindnotes.ui.reminders.adapter.RemindersAdapter
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
        viewModel.getRemindersList()
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
        reminderAdapter = RemindersAdapter( onItemSelected = {

        })

        binding.rvListReminder.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reminderAdapter
        }
    }
}