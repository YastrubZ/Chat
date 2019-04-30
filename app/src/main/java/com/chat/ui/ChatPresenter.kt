package com.chat.ui

class ChatPresenter: ContractPresenter {
    private var view: ContractView? = null

    override fun onAttach(view: ContractView){
        this.view = view
    }

    override fun onDetach(){
        view = null
    }

    override fun onSendClicked() {
        view?.sendMessage()
    }
}