package com.test.app.ui.ui.add

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.test.app.R
import com.test.app.models.Note
import com.test.app.ui.common.BaseActivity
import com.test.app.ui.common.BaseViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.toolbar.*

class AddNoteActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_add_note

    override val mViewModel = AddViewModel()

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = ""
        }
        button_add.setOnClickListener { addNewNote() }
    }

    private fun addNewNote() {
        if (edit_note.text.toString().isNotEmpty()) {
            val note = Note(edit_note.text.toString())
            val intent = Intent()
            intent.putExtra("note", note)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
