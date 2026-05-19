package com.example.splitSavvy.ui.expense

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.R
import com.example.splitSavvy.data.mock.MockFriendData
import com.example.splitSavvy.databinding.ActivityAddExpenseBinding
import com.example.splitSavvy.model.FriendModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding

    private val selectedFriends = mutableListOf<FriendModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFriends()
        setupDatePicker()
        setupTimePicker()
        setupButtons()
    }

    // -----------------------------------
    // FRIENDS MOCK DATA
    // -----------------------------------

    private fun setupFriends() {

        val friends = MockFriendData.getFriends()

        friends.forEach { friend ->

            val view = LayoutInflater.from(this)
                .inflate(
                    R.layout.item_friend_split,
                    binding.containerSplitFriends,
                    false
                )

            val imgFriend = view.findViewById<ImageView>(R.id.imgFriend)
            val tvName = view.findViewById<TextView>(R.id.tvFriendName)
            val tvUsername = view.findViewById<TextView>(R.id.tvFriendUsername)
            val checkbox = view.findViewById<CheckBox>(R.id.checkFriend)

            imgFriend.setImageResource(friend.avatar)

            tvName.text = friend.name
            tvUsername.text = friend.username

            checkbox.isChecked = friend.isSelected

            checkbox.setOnCheckedChangeListener { _, isChecked ->

                friend.isSelected = isChecked

                if (isChecked) {
                    selectedFriends.add(friend)
                } else {
                    selectedFriends.remove(friend)
                }
            }

            binding.containerSplitFriends.addView(view)
        }
    }

    // -----------------------------------
    // DATE PICKER
    // -----------------------------------

    private fun setupDatePicker() {

        binding.layoutDate.setOnClickListener {

            val calendar = Calendar.getInstance()

            val dialog = DatePickerDialog(
                this,
                { _, year, month, day ->

                    val selectedCalendar = Calendar.getInstance()

                    selectedCalendar.set(year, month, day)

                    val formatter = SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    )

                    binding.tvDate.text =
                        formatter.format(selectedCalendar.time)

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            dialog.show()
        }
    }

    // -----------------------------------
    // TIME PICKER
    // -----------------------------------

    private fun setupTimePicker() {

        binding.layoutTime.setOnClickListener {

            val calendar = Calendar.getInstance()

            val dialog = TimePickerDialog(
                this,
                { _, hour, minute ->

                    val formattedTime =
                        String.format("%02d:%02d", hour, minute)

                    binding.tvTime.text = formattedTime

                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            )

            dialog.show()
        }
    }

    // -----------------------------------
    // BUTTONS
    // -----------------------------------

    private fun setupButtons() {

        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnReset.setOnClickListener {
            resetForm()
        }

        binding.btnAddExpense.setOnClickListener {

            val amount = binding.etAmount.text.toString()
            val title = binding.etTitle.text.toString()
            val notes = binding.etNotes.text.toString()

            if (amount.isEmpty() || title.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please fill required fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // FUTURE API CALL HERE

            Toast.makeText(
                this,
                "Expense Added Successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // -----------------------------------
    // RESET FORM
    // -----------------------------------

    private fun resetForm() {

        binding.etAmount.text?.clear()
        binding.etTitle.text?.clear()
        binding.etNotes.text?.clear()

        binding.tvDate.text = "10/24/2023"
        binding.tvTime.text = "08:00 PM"

        selectedFriends.clear()

        binding.containerSplitFriends.removeAllViews()

        setupFriends()
    }
}