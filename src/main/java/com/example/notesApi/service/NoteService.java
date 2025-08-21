package com.example.notesApi.service;

import com.example.notesApi.model.Note;
import com.example.notesApi.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepo repo;

    public List<Note> getAllNotes() {
        return repo.findAll();
    }

    public Note getNoteById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void createNote(Note note) {
        repo.save(note);
    }

    public void updateNote(Note note) {
        repo.save(note);
    }

    public void deleteNote(int id) {
        repo.deleteById(id);
    }
}
