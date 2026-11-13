package com.example.tip_calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.tip_calculator.databinding.ActivityMainBinding
import kotlin.math.ceil
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khi nhập Bill Amount
        binding.billAmount.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                calculateTip()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Khi nhập Tip Percentage
        binding.tipPercentage.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                calculateTip()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Khi bật/tắt Round up
        binding.roundUpSwitch.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }
    }

    private fun calculateTip() {

        val billText = binding.billAmount.text.toString()
        val tipText = binding.tipPercentage.text.toString()

        // Nếu chưa nhập đủ
        if (billText.isEmpty() || tipText.isEmpty()) {
            binding.tipAmountText.text = "Tip Amount: $0.00"
            return
        }

        val billAmount = billText.toDoubleOrNull() ?: 0.0
        val tipPercentage = tipText.toDoubleOrNull() ?: 0.0

        // Tính tiền tip
        var tip = billAmount * tipPercentage / 100

        // Round up
        if (binding.roundUpSwitch.isChecked) {
            tip = round(tip)
        }

        binding.tipAmountText.text =
            String.format("Tip Amount: $%.2f", tip)
    }
}