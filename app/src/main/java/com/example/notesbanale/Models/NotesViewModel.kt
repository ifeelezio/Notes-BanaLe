package com.example.notesbanale.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesbanale.Database.NoteDatabase
import com.example.notesbanale.Database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    var allnotes: LiveData<List<Note>>
    private val repository : NotesRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao)
        allnotes = repository.allNotes
    }


    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){

        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO){

        repository.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){

        repository.update(note)
    }

}