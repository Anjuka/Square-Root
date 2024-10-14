package com.hamiliya.squareroot

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hamiliya.squareroot.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        
        binding.btnRoot.setOnClickListener { 
            var input = binding.etInput.text.toString()
            if (input.isNotEmpty()){
                var root = calRoot(input.toDouble())
                binding.tvAnswer.text = root.toString()
                answerVisibility()
            }else {
                showErrorMsg()
            }
        }

        binding.btnPow.setOnClickListener {
            var input = binding.etInput.text.toString()
            if (input.isNotEmpty()){
                var pow = calPow(input.toDouble())
                binding.tvAnswer.text = pow.toString()
                answerVisibility()
            }else {
                showErrorMsg()
            }
        }

        binding.ivClear.setOnClickListener {
            clearInput()
        }

        binding.main.setOnClickListener {
            hideKeyBoard()
        }
        
    }

    private fun calRoot(value: Double) : Double {
        return sqrt(value)
    }

    private fun calPow(value: Double) : Double {
        return value.pow(2)
    }

    private fun showErrorMsg() {
        Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show()
    }

    private fun clearInput(){
        binding.etInput.setText("")
        binding.tvAnsHead.visibility = View.INVISIBLE
        binding.tvAnswer.visibility = View.INVISIBLE
        binding.ivClear.visibility = View.INVISIBLE
        hideKeyBoard()
    }
    
    private fun answerVisibility(){
        binding.tvAnsHead.visibility = View.VISIBLE
        binding.tvAnswer.visibility = View.VISIBLE
        binding.ivClear.visibility = View.VISIBLE
        hideKeyBoard()
    }

    private fun hideKeyBoard(){
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}