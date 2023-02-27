package com.example.mortgage_calculator

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mortgage_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val pf:Prefs = Prefs(this)
    var mortgage = Mortgage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        pf.setPreferences(mortgage)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setText(mortgage)
    }
//    override fun onStart()  {
//        super.onStart()
//
//        pf.getPreferences(mortgage)
//
//        setText(mortgage)
//    }

    fun setText(mortgage: Mortgage.Companion)   {

        var amountString = mortgage.getFormattedAmount().toString()
        var yearsString = mortgage.getYears().toString()
        var rateString = String.format("%.1f",(mortgage.getRate() * 100)) + "%"
        var monthlyPaymentString = Mortgage.formattedMonthlyPayment()
        var totalPaymentString = Mortgage.formattedTotalPayment()

        binding.amount.text = amountString
        binding.years.text = yearsString
        binding.rate.text = rateString
        binding.payment.text = monthlyPaymentString
        binding.total.text= totalPaymentString
    }

    fun modifyData(view: View) {
        val myIntent = Intent(this, DataActivity::class.java)
        this.startActivity(myIntent)
        var s: SharedPreferences? = this!!.getSharedPreferences("Mortgage", Context.MODE_PRIVATE)
        overridePendingTransition(R.anim.slide_from_left, R.anim.fade_out_and_scale)
        }
}