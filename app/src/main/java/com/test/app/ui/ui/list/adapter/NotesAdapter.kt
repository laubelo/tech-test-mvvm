package com.test.app.ui.ui.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.test.app.R
import com.test.app.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(
    private val context: Context,
    private val notes: List<Note>,
    private val onNoteSelected: OnNoteSelected
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(notes[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(note: Note) {
            itemView.apply {
                text_note.text = note.text
                linear_note.setOnClickListener {
                    onNoteSelected.onSelected(adapterPosition)
                }
                if (adapterPosition == notes.size.minus(1)){
                    view_separator.visibility = View.GONE
                } else{
                    view_separator.visibility = View.VISIBLE
                }
            }


        }

    }

    interface OnNoteSelected{
        fun onSelected(position: Int)
    }


}