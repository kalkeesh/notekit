package com.notebook.notekit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.notebook.notekit.model.Note;

public interface NoteRepository extends MongoRepository<Note, String> {
}
