package com.test.app.data

import com.test.app.models.Note

class LocalData {

    fun getNotesList(): List<Note>? {
        val list = ArrayList<Note>()

        list.add(Note("Mock note 1"))
        list.add(Note("Mock note 2"))
        list.add(Note("Mock note 3"))
        list.add(Note("Mock note 4"))
        list.add(Note("Mock note 5"))

        return list
    }


}