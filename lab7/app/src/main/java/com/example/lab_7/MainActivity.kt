package com.example.lab_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.lab_7.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstName: String = ""
    private var middleName: String = ""
    private var lastName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.frame.id) as? NavHostFragment
        navHostFragment?.let { navFragment ->
            val firstFragment = navFragment.childFragmentManager.fragments.firstOrNull() as? FragmentOne

            val bundle = Bundle().apply {
                putString("firstName", firstName)
                putString("middleName", middleName)
                putString("lastName", lastName)
            }

            firstFragment?.let {
                firstFragment.childFragmentManager.setFragmentResult(
                    "main_data",
                    bundle
                )
            }
        }

        binding.fragmentThreeBtn.setOnClickListener {
            val fragmentThree = FragmentThree()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragmentThree).addToBackStack(null).commit()
        }
    }
}