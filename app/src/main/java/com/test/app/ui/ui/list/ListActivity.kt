package com.test.app.ui.ui.list

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import com.test.app.R
import com.test.app.data.LocalData
import com.test.app.models.Note
import com.test.app.ui.common.BaseActivity
import com.test.app.ui.ui.list.adapter.NotesAdapter
import com.test.app.utils.Constants
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseActivity(), NotesAdapter.OnNoteAction {

    override fun getLayoutId(): Int = R.layout.activity_list

    override val mViewModel = ListViewModel(LocalData())

    private var notes = ArrayList<Note>()

    private lateinit var adapter: NotesAdapter

    override fun initView() {
        mViewModel.getItems()
        initAdapter()
        setupObservers()
        button_add.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        navigator.goToAdd(this)
    }

    private fun setupObservers() {
        mViewModel.notes.observe(this, Observer { items ->
            notes.clear()
            notes.addAll(items)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initAdapter() {
        adapter = NotesAdapter(this, notes, this)
        recycler_view_notes.adapter = adapter
    }

    override fun onRemoved(position: Int) {
        notes.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(0, notes.size)
    }

    override fun onModified(position: Int, note: Note) {
        navigator.goToEdit(this, note, position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                Constants.ADD_NEW_NOTE -> {
                    data?.getSerializableExtra("note")?.let {
                        val note = it as Note
                        notes.add(note)
                        adapter.notifyDataSetChanged()
                    }
                }
                Constants.EDIT_NOTE -> {
                    data?.let {
                        var note = Note("")

                        it.getSerializableExtra("note")?.let {
                            note = it as Note
                        }

                        val position = it.getIntExtra("position", -1)
                        notes.set(position, note)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

}
