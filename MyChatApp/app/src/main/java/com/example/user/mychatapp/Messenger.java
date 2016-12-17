package com.example.user.mychatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Messenger extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference messageDB;
    private static final String TAGSG = "Messenger";

    EditText Msg_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ListView listView = (ListView)findViewById(R.id.list);
        Button msgSend = (Button) findViewById(R.id.butt_send);

        msgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg_edit = (EditText) findViewById(R.id.edit_text);
                String getMsg = Msg_edit.getText().toString().trim();
                writeNewUser(getMsg);
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference("user");
        messageDB = firebaseDatabase.getReference("conversation");
        messageDB.child("abc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAGSG,dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.setValue("Hi Ibad");

    }
    private void writeNewUser(String Message){
        messageDB.child("abc").push().setValue(Message);
    }
}
