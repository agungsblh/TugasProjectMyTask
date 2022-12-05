package com.D121201075.mytask.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.D121201075.mytask.R
import com.D121201075.mytask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.menu.getItem(1).isEnabled = false

        val navController = findNavController(R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.detailTugasFragment||destination.id == R.id.editTugasFragment) {
                binding.addTask.visibility = View.GONE
                binding.bottomAppBar.visibility = View.GONE
            } else {
                binding.addTask.visibility = View.VISIBLE
                binding.bottomAppBar.visibility = View.VISIBLE
            }
        }
        onAction()
    }
    private fun onAction(){
        binding.apply {
            addTask.setOnClickListener {
                val intent = Intent(this@MainActivity,TambahTugasActivity::class.java)
                startActivity(intent)
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp()||return super.onSupportNavigateUp()
    }

}