package com.example.nbc__imagecollector__typea.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nbc__imagecollector__typea.databinding.FragmentMyBoxBinding


class MyBoxFragment : Fragment() {

    private var _binding: FragmentMyBoxBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyBoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun newInstant() : MyBoxFragment {
        val args = Bundle()
        val frag = MyBoxFragment()
        frag.arguments = args
        return frag
    }
}