package com.example.notepad.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public
interface NotesDAO {
    @Query("select * from Notes")
    List<Notes> getALlNotes();
//    @Query("SELECT * FROM Notes WHERE boolean = :bool GROUP BY id")
//    public List<Notes> selected(boolean bool);
    @Insert
    void addNotes(Notes notes);
    @Delete
    void deleteNotes(Notes notes);
    @Update
    void updateNotes(Notes notes);

}
