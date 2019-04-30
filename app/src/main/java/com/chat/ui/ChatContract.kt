package com.chat.ui

interface ContractView{
    fun sendMessage()
}

interface ContractPresenter{
    fun onAttach(view: ContractView)
    fun onDetach()
    fun onSendClicked()
}