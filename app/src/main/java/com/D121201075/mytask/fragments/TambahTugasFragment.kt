package com.D121201075.mytask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.D121201075.mytask.databinding.FragmentTambahTugasBinding


class TambahTugasFragment : Fragment() {
    private var _binding: FragmentTambahTugasBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTambahTugasBinding.inflate(inflater,container,false)
        val view = binding.root







        return view

    }
}