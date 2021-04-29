package com.example.shualeduri.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shualeduri.R


class ShopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val itemOldPrice = findViewById<TextView>(R.id.itemPrice)
        val currentPrice = findViewById<TextView>(R.id.discountPrice)
        val buyNowButton = findViewById<Button>(R.id.buyNowButton)
        val addToCartButton = findViewById<Button>(R.id.addToCartButton)

        //cross the old price
        itemOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        //navigate to paying
        buyNowButton.setOnClickListener {
            val intent = Intent(this, PayActivity::class.java).apply {
                putExtra("ITEM_PRICE", currentPrice.text.toString())
            }
            startActivity(intent)
        }

        //add to cart
        addToCartButton.setOnClickListener {
            Toast.makeText(this, "This item is added to cart successfully!", Toast.LENGTH_SHORT).show()
        }
    }



}