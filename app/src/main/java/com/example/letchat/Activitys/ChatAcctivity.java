package com.example.letchat.Activitys;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.letchat.Adapters.MessageAdapters;
import com.example.letchat.Models.Message;
import com.example.letchat.databinding.ActivityChatAcctivityBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ChatAcctivity extends AppCompatActivity {
    ActivityChatAcctivityBinding binding;
    MessageAdapters adapters;
    ArrayList<Message> messages;
    String senderRoom, receiverRoom;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatAcctivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        messages = new ArrayList<>();
        adapters = new MessageAdapters(this, messages);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapters);

        String name = getIntent().getStringExtra("name");
        String receiverUID = getIntent().getStringExtra("uid");
        String senderUID = FirebaseAuth.getInstance().getUid();

        senderRoom = senderUID + receiverUID;
        receiverRoom = receiverUID + senderUID;

        database = FirebaseDatabase.getInstance();
        database.getReference().child("Chats_Section")
                .child(senderRoom)
                .child("messages")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messages.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                            Message message = snapshot1.getValue(Message.class);
                            message.setMessageId(snapshot1.getKey());
                            messages.add(message);
                        }
                        adapters.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String massageTXT = binding.messageBox.getText().toString();
                Date date = new Date();
                Message message = new Message(massageTXT, senderUID, date.getTime());
                binding.messageBox.setText("");
                database.getReference()
                        .child("Chats_Section")
                        .child(senderRoom)
                        .child("messages")
                        .push()
                        .setValue(message)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                database.getReference()
                                        .child("Chats_Section")
                                        .child(receiverRoom)
                                        .child("messages")
                                        .push()
                                        .setValue(message)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });
                            }
                        });
            }
        });

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}