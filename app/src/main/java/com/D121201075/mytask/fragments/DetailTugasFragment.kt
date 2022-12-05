package com.D121201075.mytask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.D121201075.mytask.R
import com.D121201075.mytask.databinding.FragmentDetailTugasBinding
import com.D121201075.mytask.utils.Dialog
import com.D121201075.mytask.viewmodel.TugasViewModel

class DetailTugasFragment : Fragment() {
    private var _binding: FragmentDetailTugasBinding?=null
    private val binding get() = _binding!!

    private val args by navArgs<DetailTugasFragmentArgs>()
    private lateinit var tugasViewModel :TugasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailTugasBinding.inflate(inflater,container,false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        binding.apply {
            judulTugas.text = args.currentTugas.nama_tugas
            isiTugas.text = args.currentTugas.isi_tugas
            kategoriTugas.text = args.currentTugas.kategori_tugas
            if (args.currentTugas.status==(resources.getString(R.string.selesai))){
                btnSelesai.visibility = View.GONE
                back.setOnClickListener {
                    findNavController().navigate(R.id.action_detailTugasFragment_to_historyFragment)
                }
            }else{
                btnSelesai.visibility = View.VISIBLE
                back.setOnClickListener {
                    findNavController().navigate(R.id.action_detailTugasFragment_to_homeFragment)
                }
            }
            btnSelesai.setOnClickListener {
                Dialog.showDialog2Button(requireContext(),
                    findNavController(),
                    resources.getString(R.string.selesaikanTugas),
                    resources.getString(R.string.subSelesaikanTugas),
                    R.raw.anim_done_task,
                    R.id.action_detailTugasFragment_to_homeFragment,
                    tugasViewModel,
                    1,
                    args.currentTugas.id,
                    args.currentTugas.nama_tugas,
                    args.currentTugas.isi_tugas,
                    args.currentTugas.kategori_tugas
                )
            }
        }
        return view
    }
}