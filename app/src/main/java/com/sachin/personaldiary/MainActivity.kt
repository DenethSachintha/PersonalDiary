package com.sachin.personaldiary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.sachin.personaldiary.databinding.ActivityMainBinding
import com.sachin.personaldiary.fragments.HomeFragment
import com.sachin.personaldiary.fragments.SettingsFragment
import com.sachin.personaldiary.fragments.ViewDiaryFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.viewDiary -> replaceFragment(ViewDiaryFragment())
                R.id.settings -> replaceFragment(SettingsFragment())

                else ->{
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}
