package com.chat.network

interface ChatListener {
    fun onNewMessage(message: String, isCurrentUser: Boolean = false)
}