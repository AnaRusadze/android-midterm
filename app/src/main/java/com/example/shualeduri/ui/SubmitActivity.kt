package com.example.shualeduri.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.shualeduri.PaymentInfo
import com.example.shualeduri.R

class SubmitActivity : AppCompatActivity() {

    private lateinit var price: TextView
    private lateinit var fullName: TextView
    private lateinit var cardNumber: TextView
    private lateinit var expDate: TextView
    private lateinit var cvvCode: TextView
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        price = findViewById(R.id.priceTextView)
        fullName = findViewById(R.id.fullNameTextView)
        cardNumber = findViewById(R.id.cardNumberTextView)
        expDate = findViewById(R.id.expirationDateTextView)
        cvvCode = findViewById(R.id.cvvCodeTextView)
        submitButton = findViewById(R.id.submitButton)

        setSubmitInfo()
        backToShop()

    }

    private fun setSubmitInfo(){
        val paymentInfo = intent.getParcelableExtra<PaymentInfo>("PAYMENT_INFO")

        price.text = paymentInfo!!.itemPrice
        fullName.text = paymentInfo.fullName
        expDate.text = "${paymentInfo.expMonth}/${paymentInfo.expYear}"
        cvvCode.text = paymentInfo.cvvCode.toString()

        //hide first part of card number
        val cardNum = paymentInfo.cardNumber.toString()
        val cardNumFirstPart = cardNum.substring(0, 12)
        cardNumber.text = cardNum.replace(cardNumFirstPart, "••••••••••••")
    }

    private fun backToShop(){
        submitButton.setOnClickListener {
            Toast.makeText(applicationContext, "Paying operation was successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
    }
}