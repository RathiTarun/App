package com.example.splitSavvy.ui.friends

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitSavvy.adapter.FriendAdapter
import com.example.splitSavvy.data.mock.MockFriendData
import com.example.splitSavvy.databinding.ActivityAddFriendBinding
import com.example.splitSavvy.model.FriendModel

class AddFriendActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddFriendBinding
    private lateinit var friendAdapter: FriendAdapter
    private var friendList= mutableListOf<FriendModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleView()
        setupSearch()
        setupButton()
    }

    private fun setupRecycleView(){
        //MOCK DATA
        friendList = MockFriendData.getFriends()

        //Adapter
        friendAdapter = FriendAdapter(friendList)

        //RecyclerView Setup
        binding.recyclerFriends.layoutManager = LinearLayoutManager(this)

        binding.recyclerFriends.adapter = friendAdapter
    }

    //Search Function

    private fun setupSearch(){
        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                filterFriends(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }
        })
    }

    //Filter Friends
    private fun filterFriends(query: String){
        val filteredList = friendList.filter {
            it.name.contains(query, ignoreCase = true)
                    ||
                    it.username.contains(query, ignoreCase = true)
                    ||
                    it.email.contains(query, ignoreCase = true)
        }.toMutableList()
        friendAdapter.filterList(filteredList)
    }
    // Buttons

    private fun setupButton(){
        binding.btnBack.setOnClickListener {
            finish()
        }
        // QR Button
        binding.btnQr.setOnClickListener {
            Toast.makeText(
                this,
                "QR scanner Comming Soon",
                Toast.LENGTH_SHORT
            ).show()
        }
        // Send Friend Request
        binding.btnSendRequest.setOnClickListener{
            val selectedFriend = friendList.filter {
                it.isSelected
            }
            if(selectedFriend.isEmpty()){
                Toast.makeText(
                    this,
                    "Select at least one friend",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            Toast.makeText(
                this,
                "$(selectedFriends.size} Friend Request Sent",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}