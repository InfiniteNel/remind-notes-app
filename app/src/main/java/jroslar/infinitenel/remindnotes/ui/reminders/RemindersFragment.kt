package jroslar.infinitenel.remindnotes.ui.reminders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jroslar.infinitenel.remindnotes.databinding.FragmentRemindersBinding
import jroslar.infinitenel.remindnotes.ui.reminders.adapter.RemindersAdapter


@AndroidEntryPoint
class RemindersFragment : Fragment() {

    private var _binding: FragmentRemindersBinding? = null
    private val binding get() = _binding!!

    private lateinit var reminderAdapter: RemindersAdapter

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