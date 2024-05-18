package com.example.roomdatabasebasics.ui

import com.example.roomdatabasebasics.model.Note

interface DeleteNoteInterface {
    fun onDeleteNote(note: Note)
}