package com.test.app.ui.ui.add

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import com.test.app.R
import com.test.app.models.Note
import com.test.app.ui.common.BaseActivity
import com.test.app.utils.Constants
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.Serializable
import java.util.regex.Pattern

class AddNoteActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_add_note

    override val mViewModel = AddViewModel()

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = ""
        }

        val from = intent.getStringExtra("from") ?: ""

        var note = Note("")
        intent.getSerializableExtra("note")?.let {
            note = it as Note
        }

        val position = intent.getIntExtra("position", -1)

        setupView(from, note)
        button_add.setOnClickListener { addNewNote(from, note, position) }
    }

    private fun setupView(from: String, note: Note) {
        when (from) {
            Constants.FROM_ADD -> {
                button_add.text = getString(R.string.add_note)
            }
            Constants.FROM_EDIT -> {
                button_add.text = getString(R.string.edit_note)
                edit_note.setText(note.text)
                edit_note.text?.let {
                    edit_note.setSelection(it.length)
                }
            }
        }
    }

    private fun addNewNote(
        from: String,
        note: Note,
        position: Int
    ) {
        if (edit_note.text.toString().isNotEmpty()) {
            if (Pattern.compile(Constants.NOTE_REGEX).matcher(edit_note.text.toString()).matches()) {

                edit_layout.error = ""

                when (from) {
                    Constants.FROM_ADD -> {
                        note.text = edit_note.text.toString()
                        val intent = Intent()
                        intent.putExtra("note", note)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                    Constants.FROM_EDIT -> {
                        note.text = edit_note.text.toString()
                        val intent = Intent()
                        intent.putExtra("note", note)
                        intent.putExtra("position", position)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }
            } else {
                edit_layout.error = getString(R.string.regex_match)
            }

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
