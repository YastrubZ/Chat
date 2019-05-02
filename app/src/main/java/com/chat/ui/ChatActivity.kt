package com.chat.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.chat.R
import com.chat.network.ChatHandler
import com.chat.network.ChatListener
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), ChatListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        ChatHandler.subscribe(
            resources.getString(R.string.default_user_name),
            resources.getString(R.string.default_user_message),
            this,
            baseContext
        )

        sendButton.setOnClickListener {
            sendMessage()
        }

        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                sendButton.isEnabled = p0!!.isNotEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    override fun onDestroy() {
        ChatHandler.unsubscribe()
        super.onDestroy()
    }

    private fun sendMessage() {
        ChatHandler.sendMessage(
            userEditText.text.toString(),
            messageEditText.text.toString()
        )
        messageEditText.text.clear()
    }

    override fun onNewMessage(message: String, isCurrentUser: Boolean) {
        runOnUiThread {
            val newTextView = TextView(this)
            newTextView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            newTextView.gravity = if(isCurrentUser) Gravity.END else Gravity.START
            newTextView.text = message
            chatLayout.addView(newTextView)
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }
}
