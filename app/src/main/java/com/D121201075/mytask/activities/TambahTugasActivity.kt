package com.D121201075.mytask.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.D121201075.mytask.R
import com.D121201075.mytask.model.Tugas
import com.D121201075.mytask.viewmodel.TugasViewModel
import com.D121201075.mytask.databinding.ActivityTambahTugasBinding
import com.D121201075.mytask.utils.Dialog
import kotlinx.coroutines.launch


class TambahTugasActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTambahTugasBinding
    private lateinit var tugasViewModel: TugasViewModel
    lateinit var kategoriTugas: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahTugasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        setDropDownKategori()
        onAction()
    }
    private fun setDropDownKategori() {
        kategoriTugas = ArrayAdapter.createFromResource(this,R.array.kategoriArray, android.R.layout.simple_list_item_1)
        binding.dropdownMenu.setAdapter(kategoriTugas)
    }
    private fun onAction(){
        binding.apply {
            back.setOnClickListener {
                finish()
            }
            btnSimpan.setOnClickListener {
                sendToDB()
            }
        }
    }
    private fun sendToDB(){
        binding.apply {
            val kategori = dropdownMenu.text.toString()
            val judul = judulTugas.text.toString()
            val isi = isiTugas.text.toString()
            if(kategori.isEmpty()){
                Toast.makeText(this@TambahTugasActivity,resources.getString(R.string.kategori_kosong),Toast.LENGTH_SHORT).show()
                dropdownMenu.requestFocus()
                return
            }
            if(judul.isEmpty()){
                judulTugas.error = resources.getString(R.string.judul_kosong)
                judulTugas.requestFocus()
                return
            }
            if(isi.isEmpty()){
                isiTugas.error = resources.getString(R.string.isi_kosong)
                isiTugas.requestFocus()
                return
            }

            lifecycleScope.launch{
                val tugas = Tugas(0,judul,isi,kategori, resources.getString(R.string.statusBelumSelesai))
                tugasViewModel.addTugas(tugas)
                Dialog.showDialogOkAct(this@TambahTugasActivity,resources.getString(R.string.dialogJudul),resources.getString(R.string.dialogSuksesAddTugas))
            }
        }
    }
}