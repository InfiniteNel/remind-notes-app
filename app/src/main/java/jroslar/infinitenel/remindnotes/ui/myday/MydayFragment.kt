package jroslar.infinitenel.remindnotes.ui.myday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jroslar.infinitenel.remindnotes.databinding.FragmentMydayBinding


class MydayFragment : Fragment() {

    private var _binding: FragmentMydayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMydayBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}