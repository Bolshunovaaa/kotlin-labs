package com.example.lab_7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab_7.databinding.FragmentOneBinding


class FragmentOne : Fragment() {
    private lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentTwoBtn.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val middleName = binding.middleName.text.toString()
            val lastName = binding.lastName.text.toString()
            val action = FragmentOneDirections.actionFragment1ToFragment2(firstName, middleName, lastName)
            findNavController().navigate(action)
        }
    }
}
