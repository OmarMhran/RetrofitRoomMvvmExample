package com.example.roomdatabasebasics.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteTable")
data class Note(
    val title: String,
    val description: String,
    val time: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}