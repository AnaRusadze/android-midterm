package com.example.shualeduri.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.shualeduri.PaymentInfo
import com.example.shualeduri.R
import java.util.*

class PayActivity : AppCompatActivity() {

    private lateinit var itemPrice: String
    private lateinit var amountEditText: EditText
    private lateinit var currencyTextView: TextView
    private lateinit var buyNowButton: Button
    private lateinit var expirationMonth: EditText
    private lateinit var expirationYear: EditText
    private lateinit var creditCardNumber: EditText
    private lateinit var fullName: EditText
    private lateinit var cvv: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        amountEditText = findViewById(R.id.amountEditText)
        currencyTextView = findViewById(R.id.amountCurrencyText)
        expirationMonth = findViewById(R.id.expirationMonthEditText)
        expirationYear = findViewById(R.id.expirationYearEditText)
        creditCardNumber = findViewById(R.id.CreditCardNumberEditText)
        fullName = findViewById(R.id.fullNameEditText)
        cvv = findViewById(R.id.cvvCodeEditText)
        buyNowButton = findViewById(R.id.buyNowButton)

        init()
    }

    private fun init() {

        getItemPrice()
        buyNowButton.setOnClickListener {
            if (checkEmptyFields() && checkFullName() && checkCardNumber() && checkExpirationDate() && checkCvvCode()) {
                navigateToSubmit()
            }
        }
    }

    private fun getItemPrice() {
        itemPrice = intent.getStringExtra("ITEM_PRICE").toString()

        val currency = itemPrice.substring(0, 1)
        val amount = itemPrice.substring(1, itemPrice.length)

        currencyTextView.text = currency
        amountEditText.setText(amount)
    }

    private fun checkEmptyFields(): Boolean {
        return if (amountEditText.text.isEmpty() || fullName.text.isEmpty() || creditCardNumber.text.isEmpty() || expirationMonth.text.isEmpty() || expirationYear.text.isEmpty() || cvv.text.isEmpty()) {
            makeToast("Please fill all fields!")
            false
        } else {
            true
        }
    }

    private fun checkFullName(): Boolean {
        val nameRegex = "^[\\p{L} .'-]+$"

        return if (fullName.text.matches(nameRegex.toRegex())) {
            true
        } else {
            makeToast("full name is incorrect")
            false
        }
    }

    private fun checkCardNumber(): Boolean {

        val visaRegex = "^4[0-9]{6,}$"
        val masterCardRegex = "^5[1-5][0-9]{5,}$"

        return if (creditCardNumber.text.length == 16 && (creditCardNumber.text.matches(visaRegex.toRegex()) || creditCardNumber.text.matches(masterCardRegex.toRegex()))) {
            true
        } else {
            makeToast("credit card number is incorrect")
            false
        }
    }

    private fun checkExpirationDate(): Boolean {

        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        val currentYearWithTwoChars = currentYear.substring(2, currentYear.length).toInt()

        return if (expirationYear.text.toString().toInt() >= currentYearWithTwoChars && expirationMonth.text.toString().toInt() < 12) {
            if (expirationYear.text.toString().toInt() == currentYearWithTwoChars && expirationMonth.text.toString().toInt() !in currentMonth..12) {
                makeToast("expiration month doesn't match with current time")
                false
            } else true

        } else {
            makeToast("expiration year or month is incorrect")
            false
        }

    }

    private fun checkCvvCode(): Boolean {
        val cvvRegex = "^[0-9]{3}$"

        return if (cvv.text.matches(cvvRegex.toRegex())) {
            true
        } else {
            makeToast("cvv is incorrect")
            false
        }
    }

    private fun makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
    private fun navigateToSubmit() {

        val paymentInfo = PaymentInfo(
            itemPrice,
            fullName.text.toString(),
            creditCardNumber.text.toString().toLong(),
            expirationMonth.text.toString().toInt(),
            expirationYear.text.toString().toInt(),
            cvv.text.toString().toInt()
        )
        val intent = Intent(this, SubmitActivity::class.java).apply {
            putExtra("PAYMENT_INFO", paymentInfo)
        }
        startActivity(intent)
    }


}