package com.D121201075.mytask.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tugas")
data class Tugas (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val nama_tugas:String,
    val isi_tugas:String,
    val kategori_tugas:String,
    val status:String
):Parcelable
