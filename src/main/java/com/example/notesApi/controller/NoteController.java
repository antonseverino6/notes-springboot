package com.example.notesApi.controller;

import com.example.notesApi.model.Note;
import com.example.notesApi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService service;

    @GetMapping("/")
    public String greet() {
        return "Hello Part 2 of learning spring boot";
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(service.getAllNotes());
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable int id) {

        if (service.getNoteById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(service.getNoteById(id));
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        note.setTimestamp(new Date());
        service.createNote(note);

        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @PutMapping("/note/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody Note updatedNote) {
        Note note = service.getNoteById(id);

        if (note != null) {
            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            note.setTimestamp(new Date());

            service.updateNote(note);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("note/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable int id) {
        if (service.getNoteById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        service.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
