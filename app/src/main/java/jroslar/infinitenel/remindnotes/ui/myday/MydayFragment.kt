package jroslar.infinitenel.remindnotes.ui.myday

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
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.core.utils.DateUtils
import jroslar.infinitenel.remindnotes.databinding.FragmentMydayBinding
import jroslar.infinitenel.remindnotes.domain.model.NotificationModel
import jroslar.infinitenel.remindnotes.ui.myday.adapter.MydayAdapter
import jroslar.infinitenel.remindnotes.ui.reminders.dialogs.ManageRemindersDialog
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class MydayFragment : Fragment() {

    private var _binding: FragmentMydayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MydayViewModel by viewModels()

    private lateinit var adapterToday: MydayAdapter
    private lateinit var adapterTomorrow: MydayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMydayBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initStateUI()
        initList()
    }

    private fun initList() {
        val c = Calendar.getInstance()
        viewModel.getListNotification(getDateToday(c), getDateTomorrow(c))
    }

    private fun getDateTomorrow(c: Calendar): String {
        c.add(Calendar.DATE, 1)

        val dayTomorrow = c.get(Calendar.DAY_OF_MONTH)
        val monthTomorrow = c.get(Calendar.MONTH)
        val yearTomorrow = c.get(Calendar.YEAR)

        return DateUtils.intsToDateFormat(yearTomorrow, monthTomorrow, dayTomorrow)
    }

    private fun getDateToday(c: Calendar): String {
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DateUtils.intsToDateFormat(year, month, day)
    }

    private fun initStateUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        MydayState.Loading -> showStateLoading()
                        is MydayState.Success -> showStateSuccess(it.list)
                    }
                }
            }
        }
    }

    private fun showStateSuccess(list: Pair<List<NotificationModel>, List<NotificationModel>>) {
        binding.tvNoDataToday.isVisible = list.first.isEmpty()
        binding.tvNoDataTomorrow.isVisible = list.second.isEmpty()

        adapterToday.updateList(list.first)
        adapterTomorrow.updateList(list.second)
    }

    private fun showStateLoading() {
        binding.tvNoDataToday.isVisible = true
        binding.tvNoDataTomorrow.isVisible = true
    }

    private fun initUI() {
        intiAdapter()
    }

    private fun intiAdapter() {
        adapterToday = createNewAdapter()

        binding.rvListToday.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterToday
        }

        adapterTomorrow = createNewAdapter()

        binding.rvListTomorrow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterTomorrow
        }
    }

    private fun createNewAdapter(): MydayAdapter {
        return MydayAdapter(
            onNoteSelected = {
                findNavController().navigate(
                    MydayFragmentDirections.actionNavMydayToEditNoteFragment(
                        it.id
                    )
                )
            },
            onReminderSelected = {
                ManageRemindersDialog.create(
                    reminderModel = it,
                    positiveAction = ManageRemindersDialog.Action(getString(R.string.dialogSaveReminderPositiveAction)) { dialog ->
                        val c = Calendar.getInstance()
                        viewModel.updateReminder(dialog.reminder, getDateToday(c), getDateTomorrow(c))
                        dialog.dismiss()
                    },
                    negativeAction = ManageRemindersDialog.Action(getString(R.string.dialogSaveReminderNegativeAction)) { dialog ->
                        dialog.dismiss()
                    }
                ).show(requireActivity().supportFragmentManager, null)
            }
        )
    }
}