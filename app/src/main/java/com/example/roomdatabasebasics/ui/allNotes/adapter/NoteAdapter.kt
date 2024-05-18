package com.example.roomdatabasebasics.ui.allNotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasebasics.ui.DeleteNoteInterface
import com.example.roomdatabasebasics.ui.NoteClickInterface
import com.example.roomdatabasebasics.R
import com.example.roomdatabasebasics.model.Note

class NoteAdapter(
    val deleteNoteInterface: DeleteNoteInterface,
    val noteClickInterface: NoteClickInterface

): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.note_list_item,parent,false)
        return NoteViewHolder(view)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.tvNoteTitle.text = note.title
        holder.tvTime.text = note.time
        holder.tvNoteDesc.text = note.description


        holder.ibDelete.setOnClickListener{
            deleteNoteInterface.onDeleteNote(note)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class NoteViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNoteTitle = itemView.findViewById<TextView>(R.id.tvNoteTitle)
        val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
        val tvNoteDesc = itemView.findViewById<TextView>(R.id.tvNoteDesc)
        val ibDelete = itemView.findViewById<ImageButton>(R.id.ibDeleteNote)

        init {
            itemView.setOnClickListener {
                noteClickInterface.onNoteClicked(differ.currentList.get(adapterPosition))
            }
        }
    }
}