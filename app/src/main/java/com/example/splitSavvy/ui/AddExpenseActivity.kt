package com.example.splitSavvy.ui/*package com.example.splitsavvy.ui.addexpense

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.R
import com.example.splitSavvy.model.Friend
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var tvPaidBy: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView
    private lateinit var splitContainer: LinearLayout

    private var selectedPayer: Friend? = null
    private val selectedFriends = mutableListOf<Friend>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        initViews()
        setupMockData()
        setupListeners()
    }

    private fun initViews() {
        tvPaidBy = findViewById(R.id.tvPaidBy)
        tvDate = findViewById(R.id.tvDate)
        tvTime = findViewById(R.id.tvTime)
        splitContainer = findViewById(R.id.containerSplitFriends)

        findViewById<View>(R.id.btnClose).setOnClickListener { finish() }
        findViewById<View>(R.id.btnReset).setOnClickListener { resetFields() }
        findViewById<View>(R.id.btnAddExpense).setOnClickListener { submitExpense() }
    }

    private fun setupMockData() {
        val friends = MockFriendProvider.getFriends()

        selectedPayer = friends.first()
        tvPaidBy.text = selectedPayer?.name

        setupSplitFriends(friends)
        setCurrentDateTime()
    }

    private fun setupSplitFriends(friends: List<Friend>) {
        val inflater = layoutInflater
        splitContainer.removeAllViews()
        selectedFriends.clear()

        friends.forEach { friend ->
            val view = inflater.inflate(R.layout.item_friend_split, splitContainer, false)

            view.findViewById<TextView>(R.id.tvFriendName).text = friend.name
            view.findViewById<TextView>(R.id.tvFriendUsername).text = friend.username
            val checkBox = view.findViewById<CheckBox>(R.id.cbSplit)

            checkBox.isChecked = friend.isSelected
            if (friend.isSelected) selectedFriends.add(friend)

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                friend.isSelected = isChecked
                if (isChecked) selectedFriends.add(friend) else selectedFriends.remove(friend)
            }

            splitContainer.addView(view)
        }
    }

    private fun setupListeners() {
        findViewById<View>(R.id.layoutPaidBy).setOnClickListener {
            Toast.makeText(this, "Paid By selector (API later)", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.layoutDate).setOnClickListener { openDatePicker() }
        findViewById<View>(R.id.layoutTime).setOnClickListener { openTimePicker() }
    }

    private fun openDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(this, { _, y, m, d ->
            cal.set(y, m, d)
            tvDate.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(cal.time)
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun openTimePicker() {
        val cal = Calendar.getInstance()
        TimePickerDialog(this, { _, h, m ->
            cal.set(Calendar.HOUR_OF_DAY, h)
            cal.set(Calendar.MINUTE, m)
            tvTime.text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(cal.time)
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
    }

    private fun setCurrentDateTime() {
        val now = Date()
        tvDate.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(now)
        tvTime.text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(now)
    }

    private fun resetFields() {
        findViewById<EditText>(R.id.etAmount).text.clear()
        findViewById<EditText>(R.id.etTitle).text.clear()
        findViewById<EditText>(R.id.etNotes).text.clear()
        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
    }

    private fun submitExpense() {
        val amount = findViewById<EditText>(R.id.etAmount).text.toString()
        val title = findViewById<EditText>(R.id.etTitle).text.toString()
        val notes = findViewById<EditText>(R.id.etNotes).text.toString()

        if (amount.isBlank() || title.isBlank()) {
            Toast.makeText(this, "Amount & title required", Toast.LENGTH_SHORT).show()
            return
        }

        // 🔌 API HOOK (replace later)
        // sendExpenseToBackend(...)

        Toast.makeText(this, "Expense added (mock)", Toast.LENGTH_LONG).show()
        finish()
    }
}
*/