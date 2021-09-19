package com.example.demomessageapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insert(Message message);

    @Query("SELECT * FROM messages WHERE user_id = :user_id")
    LiveData<List<Message>> getAllMessages(int user_id);
}
