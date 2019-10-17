package com.test.app.ui.ui.list

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import com.test.app.R
import com.test.app.data.LocalData
import com.test.app.models.Note
import com.test.app.ui.common.BaseActivity
import com.test.app.ui.common.Navigator
import com.test.app.ui.ui.list.adapter.NotesAdapter
import com.test.app.utils.Constants
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseActivity(), NotesAdapter.OnNoteSelected {

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

    override fun onSelected(position: Int) {

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
            }
    }

}
