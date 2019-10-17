package com.test.app.ui.common

import android.app.Activity
import android.content.Intent
import com.test.app.models.Note
import com.test.app.ui.ui.add.AddNoteActivity
import com.test.app.utils.Constants
import javax.inject.Singleton

@Singleton
class Navigator {

    fun goToAdd(activity: Activity) {
        val intent = Intent(activity, AddNoteActivity::class.java)
        intent.putExtra("from", Constants.FROM_ADD)
        activity.startActivityForResult(intent, Constants.ADD_NEW_NOTE)
    }

    fun goToEdit(
        activity: Activity,
        note: Note,
        position: Int
    ) {
        val intent = Intent(activity, AddNoteActivity::class.java)
        intent.putExtra("from", Constants.FROM_EDIT)
        intent.putExtra("note", note)
        intent.putExtra("position", position)
        activity.startActivityForResult(intent, Constants.EDIT_NOTE)
    }

}