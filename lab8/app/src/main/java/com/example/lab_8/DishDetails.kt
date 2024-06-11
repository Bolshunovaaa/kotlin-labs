package com.example.lab_8

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_8.databinding.IphoneDetailsBinding

class IphoneDetailsActivity : AppCompatActivity() {
    private lateinit var binding: IphoneDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IphoneDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val iphoneName = intent.getStringExtra("iphoneName")
        val image = intent.getStringExtra("image")
        binding.iphoneTextView.text = iphoneName
        binding.imageView2.setImageURI(Uri.parse(image))
    }
}
