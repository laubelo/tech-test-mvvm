package com.test.app.ui.ui.list

import androidx.lifecycle.MutableLiveData
import com.test.app.data.LocalData
import com.test.app.models.Note
import com.test.app.ui.common.BaseViewModel

class ListViewModel(private val localData: LocalData) : BaseViewModel() {

    val notes = MutableLiveData<List<Note>>()


    fun getItems(){
        notes.value = localData.getNotesList()
    }

}