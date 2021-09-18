package com.example.demomessageapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Message.class,version = 1)
public abstract class MessageRoomDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();
    private  static MessageRoomDatabase messageRoomDatabase;
    static MessageRoomDatabase getDatabase(final Context context){
        if(messageRoomDatabase == null){
            messageRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    MessageRoomDatabase.class,"message_database").fallbackToDestructiveMigration().build();
        }
        return messageRoomDatabase;
    }
}
