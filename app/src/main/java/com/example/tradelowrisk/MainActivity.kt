package com.example.tradelowrisk

import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    var capital:EditText? = null
    var riskAmount:TextView?=null
    var sharePrice:EditText?=null
    var shares:TextView?=null
    var stopLose:TextView?=null
    var ratio:List<Double> = listOf (0.01, 0.02, 0.03)
    var spiner:Spinner?=null
    var cal:Button?=null
    var target1:TextView? = null
    var target2:TextView? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            cntView()
            calculate()
            handleException()
    }

    private fun cntView() {
        capital = findViewById(R.id.capital)
        riskAmount = findViewById(R.id.risk)
        sharePrice = findViewById(R.id.sharePrice)
        shares = findViewById(R.id.shares)
        stopLose = findViewById(R.id.stopLose)
        spiner = findViewById(R.id.spinner)
        cal = findViewById(R.id.calculate)
        target1 = findViewById(R.id.target1)
        target2 = findViewById(R.id.target2)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ratio)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner?.adapter = arrayAdapter
    }
    private fun calculate() {
        cal?.setOnClickListener {
            val capStr: String = capital!!.text.toString()
            val ssPrice: String = sharePrice!!.text.toString()
            if (capStr == "" || ssPrice == "") {
                riskAmount?.text = ""
                shares?.text = ""
                stopLose?.text = ""
                target1?.text = ""
                target2?.text = ""
                Toast.makeText(this, " Please Fill Capital & Share Price", Toast.LENGTH_SHORT).show()
            } else {

                val cap: Double = capStr.toDouble()
                val sPrice: Double = ssPrice.toDouble()
                val gSpin: Double = spiner!!.selectedItem as Double
                val rA: Double = (gSpin * cap)
                val sN: Double = (cap / sPrice)
                val sP: Double = sPrice - (rA / sN)
                val t1: Double = (rA / sN * 2) + sPrice
                val t2: Double = (rA / sN * 3) + sPrice

                riskAmount?.text = rA.toString()
                shares?.text = sN.toString()
                stopLose?.text = sP.toString()
                target1?.text = t1.toString()
                target2?.text = t2.toString()
            }
            }
        }
        private fun handleException() {
            try {
                capital;riskAmount; sharePrice; stopLose; spiner;cal; target1; target2
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please add a values", Toast.LENGTH_SHORT).show()
            }

        }
}
