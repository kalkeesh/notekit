// // package com.notebook.notekit;

// // public class NoteController {
    
// // }
// package com.notebook.notekit.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.notebook.notekit.model.Note;
// import com.notebook.notekit.repository.NoteRepository;

// @RestController
// @RequestMapping("/api/notes")
// public class NoteController {

//     @Autowired
//     private NoteRepository noteRepository;

//     // Save Note (POST)
//     @PostMapping
//     public Note createNote(@RequestBody Note note) {
//         return noteRepository.save(note);
//     }

//     // Get All Notes (GET)
//     @GetMapping
//     public List<Note> getAllNotes() {
//         return noteRepository.findAll();
//     }
// }
package com.notebook.notekit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notebook.notekit.model.Note;
import com.notebook.notekit.repository.NoteRepository;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    // Save Note (POST)
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    // Get All Notes (GET)
    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Update Note (PUT)
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note updatedNote) {
        Optional<Note> existingNote = noteRepository.findById(id);

        if (existingNote.isPresent()) {
            Note note = existingNote.get();
            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            return noteRepository.save(note);
        } else {
            throw new RuntimeException("Note not found with id " + id);
        }
    }
}
