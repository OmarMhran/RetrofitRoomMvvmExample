package com.example.roomdatabasebasics.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabasebasics.model.Note


@Database(
    entities = arrayOf(Note::class),
    version = 1
)
abstract class RoomDataBaseClass : RoomDatabase() {

    abstract fun getDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDataBaseClass? = null
        fun getDatabase(context: Context): RoomDataBaseClass {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if(INSTANCE == null){
                synchronized(RoomDataBaseClass::class.java){
                    if (INSTANCE == null){
                        INSTANCE = buildRoom(context)
                    }
                }
            }
            return INSTANCE!!

//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    RoomDataBaseClass::class.java,
//                    "note_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
        }
        private fun buildRoom(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RoomDataBaseClass::class.java,
                "note_database"
            ).build()
    }
}

