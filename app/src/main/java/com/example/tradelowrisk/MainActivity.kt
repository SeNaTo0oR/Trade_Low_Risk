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
    var ratio:List<Double> = listOf( 0.01, 0.02, 0.03)
    var spiner:Spinner?=null
    var cal:Button?=null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            cntView()
            calculate()

    }

    private fun cntView() {
        capital = findViewById(R.id.capital)
        riskAmount = findViewById(R.id.risk)
        sharePrice = findViewById(R.id.sharePrice)
        shares = findViewById(R.id.shares)
        stopLose = findViewById(R.id.stopLose)
        spiner = findViewById(R.id.spinner)
        cal = findViewById(R.id.calculate)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ratio)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner?.adapter = arrayAdapter
    }

    private fun calculate(){

        cal?.setOnClickListener {

            var capStr:String = capital!!.text.toString()
            var ssPrice: String = sharePrice!!.text.toString()
            var cap:Double = capStr.toDouble()
            var sPrice:Double = ssPrice.toDouble()
            var gSpin:Double = spiner!!.selectedItem as Double
            val rA:Double = ( gSpin * cap)
            val sN:Double = (cap / sPrice)
            var sP:Double =   sPrice - (rA/sN)


            riskAmount?.text = rA.toString()
            shares?.text = sN.toString()
            stopLose?.text = sP.toString()

        }

    }

}