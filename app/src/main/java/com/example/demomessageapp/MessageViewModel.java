package com.example.demomessageapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    String className = this.getClass().getSimpleName();
    LiveData<Person> person_list;
    private MessageDao messageDao;
    private MessageRoomDatabase messageRoomDatabase;
    private LiveData<List<Message>> messageList;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        messageRoomDatabase = MessageRoomDatabase.getDatabase(application);
        messageDao = messageRoomDatabase.messageDao();
        messageList = messageDao.getAllMessages();
    }
    public  void  insertMessage(Message message){//wrapper class
      new InsertAsyncTask(messageDao).execute(message);
    }

    public LiveData<List<Message>> getAllMessagesFromDB(){
        return messageList;
    }

    private class  InsertAsyncTask extends AsyncTask<Message,Void,Void>{
        MessageDao messageDao;
        public  InsertAsyncTask(MessageDao messageDao){
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Message... messages) {
            messageDao.insert(messages[0]);
            return null;
        }
    }
}
