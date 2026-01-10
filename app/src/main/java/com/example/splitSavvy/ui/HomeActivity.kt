package com.example.splitSavvy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.R
import com.example.splitSavvy.model.Transaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.abs

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupBalanceCards()
        setupRecentActivity()
        setupFab()
    }

    //Balance (API Hook #1)

    private fun setupBalanceCards(){
        val youOwe = 145.50
        val owedToYou = 320.00

        findViewById<TextView>(R.id.tvYouOwe).text = formatAmount(-youOwe)
        findViewById<TextView>(R.id.tvOwedToYou).text=formatAmount(owedToYou)

    }
    private fun setupRecentActivity(){
        val container = findViewById<LinearLayout>(R.id.activityContainer)
        val inflater = LayoutInflater.from(this)

        val mockTransaction = listOf(
            Transaction("Dinner at --",-25.00, "yesterday"),
            Transaction("Ola",25.00,"yesterday"),
            Transaction("Internet",344.00,"December 29")
        )

        container.removeAllViews()

        mockTransaction.forEach { txn ->
            val itemView = inflater.inflate(R.layout.item_activity, container,false)

            val title = itemView.findViewById<TextView>(R.id.tvTitle)
            val amount = itemView.findViewById<TextView>(R.id.tvAmount)
            val date = itemView.findViewById<TextView>(R.id.tvDate)

            title.text = txn.title
            date.text = txn.date
            amount.text= formatAmount(txn.amount)

            amount.setTextColor(
                if(txn.amount>=0)
                getColor(R.color.accent_green)
                else
                getColor(R.color.accent_red)
            )

            container.addView(itemView)
        }

    }

    // floating Addition Button
    private fun setupFab(){
        findViewById<FloatingActionButton>(R.id.fabAdd)?.setOnClickListener {

        }
    }

    private fun formatAmount(value: Double): String{
        val sign = if(value>=0) "+" else "-"
        return "$sign$${abs(value)}"
    }

}