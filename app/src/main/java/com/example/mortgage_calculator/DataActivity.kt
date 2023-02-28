package com.example.mortgage_calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mortgage_calculator.databinding.ActivityDataBinding

class DataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBinding
    private var mortgage: Mortgage.Companion = Mortgage
    private val p = Prefs(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setText(mortgage)
    }

    override fun onStart() {
        super.onStart()

        p.getPreferences(mortgage)

        setText(mortgage)
    }

    fun setText(mortgage: Mortgage.Companion)   {

        var amountString = mortgage.getAmount().toString()
        var rateString = mortgage.getRate().toString()

        binding.dataAmount.setText(amountString)
        binding.dataRate.setText(rateString)

        if(mortgage.getYears() == 10)    {
            binding.ten.isChecked = true
        }

        else if(mortgage.getYears() == 15)   {
            binding.fifteen.isChecked = true
        }

        else if(mortgage.getYears() == 30)   {
            binding.thirty.isChecked = true
        }

    }

    fun goBack(v: View?) {
        updateMortgageObject()
        finish()
        overridePendingTransition(R.anim.fade_in_and_scale, R.anim.slide_to_left)
    }

    fun updateMortgageObject()
    {
        val amountET = binding.dataAmount
        val rb10 = binding.ten
        val rb15 = binding.fifteen

        var years = 30
        if (rb10.isChecked)
            years = 10
        else if (rb15.isChecked)
            years = 15
        mortgage.setYears(years)

        val rateET = binding.dataRate
        val rateString:String = rateET.getText().toString()
        val amountString = amountET.text.toString()
        try {
            val amount = amountString.toFloat()
            mortgage.setAmount(amount)
            val rate: Float = rateString.toFloat()
            mortgage.setRate(rate)
            p.setPreferences(mortgage)
        } catch (nfe: NumberFormatException) {
            mortgage.setAmount(100000.0f)
            mortgage.setRate(.035f)
        }
    }
}