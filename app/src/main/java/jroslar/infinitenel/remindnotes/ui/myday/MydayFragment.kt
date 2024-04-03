package jroslar.infinitenel.remindnotes.ui.myday

import android.os.Build
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
import jroslar.infinitenel.remindnotes.databinding.FragmentMydayBinding
import jroslar.infinitenel.remindnotes.domain.model.NotificationModel
import jroslar.infinitenel.remindnotes.ui.myday.adapter.MydayAdapter
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

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
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)

        c.add(Calendar.DATE, 1)

        val dayTomorrow = c.get(Calendar.DAY_OF_MONTH)
        val monthTomorrow = c.get(Calendar.MONTH) + 1
        val yearTomorrow = c.get(Calendar.YEAR)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateLocalToday = LocalDate.of(year, month, day)
            val dateLocalTomorrow = LocalDate.of(yearTomorrow, monthTomorrow, dayTomorrow)
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())

            viewModel.getListNotification(
                dateLocalToday.format(formatter), dateLocalTomorrow.format(formatter)
            )
        } else {
            viewModel.getListNotification(
                "%d-%d-%d".format(day, month, year),
                "%d-%d-%d".format(dayTomorrow, monthTomorrow, yearTomorrow)
            )
        }
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

            },
            onReminderSelected = {

            }
        )
    }
}