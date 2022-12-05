package com.D121201075.mytask.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import com.D121201075.mytask.R
import com.D121201075.mytask.model.Tugas
import com.D121201075.mytask.viewmodel.TugasViewModel
import com.airbnb.lottie.LottieAnimationView

object Dialog {
    fun showDialogOkAct(context:Context,judulDialog:String,isiDialog:String) {
        val view = View.inflate(context, R.layout.dialog_anim_ok,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        val dialog = builder.create()
        val btn_ok = view.findViewById<Button>(R.id.btn_ok)
        val judul = view.findViewById<TextView>(R.id.judul)
        val isi = view.findViewById<TextView>(R.id.isi)

        judul.text = judulDialog
        isi.text = isiDialog
        dialog.show()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        btn_ok.setOnClickListener {
            dialog.dismiss()
            (context as Activity).finish()
        }
    }
    fun showDialogOkFrg(context: Context,nav:NavController,destination:Int,judulDialog:String,isiDialog:String) {
        val view = View.inflate(context, R.layout.dialog_anim_ok,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        val dialog = builder.create()
        val btn_ok = view.findViewById<Button>(R.id.btn_ok)
        val judul = view.findViewById<TextView>(R.id.judul)
        val isi = view.findViewById<TextView>(R.id.isi)

        judul.text = judulDialog
        isi.text = isiDialog
        dialog.show()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        btn_ok.setOnClickListener {
            dialog.dismiss()
            nav.navigate(destination)
        }
    }
    fun showDeleteDialog(context:Context,judulDialog:String,isiDialog:String,tugasViewModel: TugasViewModel,tugas: Tugas){
        val view = View.inflate(context,R.layout.dialog_anim_ok_no,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        val yakin = view.findViewById<Button>(R.id.yes)
        val batal = view.findViewById<Button>(R.id.no)
        val judul = view.findViewById<TextView>(R.id.judul)
        val isi = view.findViewById<TextView>(R.id.isi)

        judul.text = judulDialog
        isi.text = isiDialog
        dialog.show()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        yakin.setOnClickListener {
            tugasViewModel.deleteTugas(tugas)
            Toast.makeText(context,"Tugas dihapus",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        batal.setOnClickListener {
            dialog.dismiss()
        }
    }
    fun showDialog2Button(context: Context,nav: NavController,judulDialog:String,isiDialog:String,anims:Int,destination:Int,tugasViewModel: TugasViewModel,kode:Int,idTugas:Int,judulTugas:String,isiTugas:String,kategoriTugas:String){
        val view = View.inflate(context,R.layout.dialog_anim_ok_no,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        val yakin = view.findViewById<Button>(R.id.yes)
        val batal = view.findViewById<Button>(R.id.no)
        val judul = view.findViewById<TextView>(R.id.judul)
        val isi = view.findViewById<TextView>(R.id.isi)
        val anim = view.findViewById<LottieAnimationView>(R.id.anim)

        anim.setAnimation(anims)
        judul.text = judulDialog
        isi.text = isiDialog
        dialog.show()
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        yakin.setOnClickListener {
            //kode = 1 -> update, 2->delete
            if (kode==1){
                val updateTugas = Tugas(idTugas,judulTugas,isiTugas,kategoriTugas,"Selesai")
                tugasViewModel.updateTugas(updateTugas)
                nav.navigate(destination)
            }else{
                tugasViewModel.deleteAllHistory()
                Toast.makeText(context,"History dibersihkan",Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()
        }
        batal.setOnClickListener {
            dialog.dismiss()
        }
    }
}