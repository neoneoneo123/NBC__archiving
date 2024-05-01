package com.example.nbc__imagecollector__typea.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nbc__imagecollector__typea.databinding.FragmentImageSearchBinding

class ImageSearchFragment : Fragment() {

    private var _binding: FragmentImageSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun newInstant() : ImageSearchFragment {
        val args = Bundle()
        val frag = ImageSearchFragment()
        frag.arguments = args
        return frag
    }

}