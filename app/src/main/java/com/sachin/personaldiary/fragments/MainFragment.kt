package com.sachin.personaldiary.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sachin.personaldiary.R

class MainFragment : Fragment() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the username from the arguments
        arguments?.let {
            username = it.getString("USERNAME_KEY", "")
        }

        // Initial fragment replacement
        replaceFragment(HomeFragment(), username)

        bottomNavigationView = view.findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment(), username)
                R.id.viewDiary -> replaceFragment(ViewDiaryFragment(), username)
                R.id.settings -> replaceFragment(SettingsFragment(), username)
                else -> {
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, username: String) {
        val bundle = Bundle()
        bundle.putString("USERNAME_KEY", username)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_main_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}
