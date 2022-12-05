package com.D121201075.mytask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201075.mytask.R
import com.D121201075.mytask.adapter.HistoryAdapter
import com.D121201075.mytask.databinding.FragmentHistoryBinding
import com.D121201075.mytask.utils.Dialog
import com.D121201075.mytask.viewmodel.TugasViewModel

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding?=null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel: TugasViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        val adapter = HistoryAdapter()
        binding.tugasRecycle.adapter = adapter
        binding.tugasRecycle.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        tugasViewModel.readAllDataHistory.observe(viewLifecycleOwner){ tugas->
            adapter.setData(tugas)
            checkSize(tugas.size)
        }
        binding.deleteHistory.setOnClickListener {
            Dialog.showDialog2Button(
                requireContext(),
                findNavController(),
                resources.getString(R.string.historyDelete),
                resources.getString(R.string.subHistoryDelete),
                R.raw.anim_delete,
                0,
                tugasViewModel,
                2,
                0,
                "",
                "",
                ""
            )
        }
        return view
    }

    private fun checkSize(size: Int) {
        binding.apply {
            if (size<=0){
                historyKosong.visibility= View.VISIBLE
                tugasRecycle.visibility = View.GONE
            }else{
                historyKosong.visibility= View.GONE
                tugasRecycle.visibility = View.VISIBLE
            }
        }
    }
}