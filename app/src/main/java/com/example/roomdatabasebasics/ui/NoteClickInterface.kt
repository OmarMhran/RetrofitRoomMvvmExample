package com.example.roomdatabasebasics.ui

import com.example.roomdatabasebasics.model.Note

interface NoteClickInterface {
    fun onNoteClicked(note: Note)
}