package com.D121201075.mytask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.D121201075.mytask.R
import com.D121201075.mytask.databinding.FragmentEditTugasBinding
import com.D121201075.mytask.model.Tugas
import com.D121201075.mytask.utils.Dialog
import com.D121201075.mytask.viewmodel.TugasViewModel

class EditTugasFragment : Fragment() {
    private var _binding: FragmentEditTugasBinding?=null
    private val binding get() = _binding!!

    private val args by navArgs<EditTugasFragmentArgs>()
    private lateinit var tugasViewModel :TugasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditTugasBinding.inflate(inflater,container,false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        binding.apply {
            dropdownMenu.setText(args.currentTugas.kategori_tugas)
            dropdownMenu.setAdapter(ArrayAdapter.createFromResource(requireContext(),R.array.kategoriArray, android.R.layout.simple_list_item_1))
            judulTugas.setText(args.currentTugas.nama_tugas)
            isiTugas.setText(args.currentTugas.isi_tugas)

            btnSimpan.setOnClickListener {
                updateTugas()
            }
            back.setOnClickListener {
                findNavController().navigate(R.id.action_editTugasFragment_to_homeFragment)
            }
        }
        return view
    }

    private fun updateTugas(){

        binding.apply {
            val kategori = dropdownMenu.text.toString()
            val judul_tugas = judulTugas.text.toString()
            val isi_tugas = isiTugas.text.toString()

            if(kategori.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.kategori_kosong),
                    Toast.LENGTH_SHORT).show()
                dropdownMenu.requestFocus()
                return
            }
            if(judul_tugas.isEmpty()){
                judulTugas.error = resources.getString(R.string.judul_kosong)
                judulTugas.requestFocus()
                return
            }
            if(isi_tugas.isEmpty()){
                isiTugas.error = resources.getString(R.string.isi_kosong)
                isiTugas.requestFocus()
                return
            }
            val updateTugas = Tugas(args.currentTugas.id, judul_tugas,isi_tugas,kategori,resources.getString(R.string.statusBelumSelesai))
            tugasViewModel.updateTugas(updateTugas)
            context?.let {
                Dialog.showDialogOkFrg(
                    it,findNavController(),R.id.action_editTugasFragment_to_homeFragment,requireContext().resources.getString(R.string.dialogJudul),
                    requireContext().resources.getString(R.string.dialogSuksesEditTugas))
            }
        }

    }
}