package com.D121201075.mytask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201075.mytask.R
import com.D121201075.mytask.adapter.TugasAdapter
import com.D121201075.mytask.viewmodel.TugasViewModel
import com.D121201075.mytask.databinding.FragmentHomeBinding
import com.airbnb.lottie.LottieDrawable

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var tugasViewModel: TugasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root


        val adapter = TugasAdapter()
        binding.tugasRecycle.adapter = adapter
        binding.tugasRecycle.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        tugasViewModel.readAllData.observe(viewLifecycleOwner){ tugas->
            adapter.setData(tugas)
            checkSize(tugas.size)
            botExpression(tugas.size)
        }

        return view
    }
    private fun checkSize(size: Int) {
        binding.apply {
            if (size<=0){
                tugasKosong.visibility= View.VISIBLE
                tugasRecycle.visibility = View.GONE
            }else{
                tugasKosong.visibility= View.GONE
                tugasRecycle.visibility = View.VISIBLE
            }
        }
    }
    private fun botExpression(size:Int){
        binding.apply {
            if (size>5){
                binding.anims.setAnimation(R.raw.anim_lvl4)
                binding.anims.repeatCount = LottieDrawable.INFINITE
                binding.anims.playAnimation()
                textTugas(size)
            }else if(size>3){
                binding.anims.setAnimation(R.raw.anim_lvl3)
                binding.anims.repeatCount = LottieDrawable.INFINITE
                binding.anims.playAnimation()
                textTugas(size)
            }else if(size>=1){
                binding.anims.setAnimation(R.raw.anim_lvl2)
                binding.anims.repeatCount = LottieDrawable.INFINITE
                binding.anims.playAnimation()
                textTugas(size)
            }else if(size==0){
                binding.anims.setAnimation(R.raw.anim_lvl1)
                binding.anims.repeatCount = LottieDrawable.INFINITE
                binding.anims.playAnimation()
                textTugas(size)
            }

        }

    }
    private fun textTugas(tugas:Int){
        if (tugas==0){
            binding.jumlahTugas.text = resources.getString(R.string.jumlahTugas0)
        }else{
            binding.jumlahTugas.text = resources.getString(R.string.jumlahTugas1)+" $tugas "+resources.getString(R.string.jumlahTugas2)
        }

    }
}