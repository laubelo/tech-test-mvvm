package com.test.app.ui.common

import android.app.Activity
import android.content.Intent
import com.test.app.ui.ui.add.AddNoteActivity
import com.test.app.utils.Constants
import javax.inject.Singleton

@Singleton
class Navigator {

    fun goToAdd(activity: Activity) {
        val intent = Intent(activity, AddNoteActivity::class.java)
        activity.startActivityForResult(intent, Constants.ADD_NEW_NOTE)
    }

}