package com.example.education_app.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.education_app.MemoryData;
import com.example.education_app.Profile;
import com.example.education_app.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.protobuf.StringValue;
import com.google.protobuf.TimestampOrBuilder;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://education-app-bb58d-default-rtdb.firebaseio.com/");
    private final List<ChatList> chatLists = new ArrayList<>();
    private boolean loadingfirst = true;

    private RecyclerView chatrecyclerview;
    private String chatKey;
    private ChatAdapter chatAdapter;
    String getemails = "";
    //private int generatechatKey;
   // String user_1ids;
    ImageView backBtn;
    TextView name;
    CircleImageView profilePic;
    EditText sendBtn1;
    ImageView sendBtn;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        backBtn = findViewById(R.id.backBtn);
        name = findViewById(R.id.name);
        profilePic = findViewById(R.id.profilePic);
        sendBtn1 = findViewById(R.id.sendBtn1);
        sendBtn = findViewById(R.id.sendBtn);

        chatrecyclerview = findViewById(R.id.chatrecyclerview);
        chatrecyclerview.setHasFixedSize(true);
        chatrecyclerview.setLayoutManager(new LinearLayoutManager(Chat.this));

        chatAdapter = new ChatAdapter(chatLists, Chat.this);
        firebaseAuth = FirebaseAuth.getInstance();
      //  user_1ids = firebaseAuth.getCurrentUser().getUid();




        // get data from message adapter
        final String getName = getIntent().getStringExtra("name");
        final String getEmail = getIntent().getStringExtra("email");


        // get email from memory
        getemails = MemoryData.getData(Chat.this);

        chatKey = getIntent().getStringExtra("chatKey");
        name.setText(getName);

        // if(chatKey.isEmpty()){
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (chatKey.isEmpty()) {
                    chatKey = "1";

                    if (snapshot.hasChild("chat")) {
                        chatKey = String.valueOf(snapshot.child("chat").getChildrenCount() + 1);
                    }
                }

                if(snapshot.hasChild("chat")){
                    if(snapshot.child("chat").child(chatKey).hasChild("message")){
                    chatLists.clear();
                        for(DataSnapshot messageSnapshot : snapshot.child("chat").child(chatKey).child("message").getChildren()){

                           if(loadingfirst || messageSnapshot.hasChild("msg") && messageSnapshot.hasChild("name")) {
                               final String messageTimeStamps = messageSnapshot.getKey();
                               final String getName = messageSnapshot.child("name").getValue(String.class);
                               final String getMsg = messageSnapshot.child("msg").getValue(String.class);


                               Timestamp timestamp = new Timestamp(Long.parseLong(messageTimeStamps));
                               Date date = new Date(timestamp.getTime());

                               SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                               SimpleDateFormat simpleTimeFormat=new SimpleDateFormat("hh:mm aa", Locale.getDefault());
                               ChatList chatList = new ChatList(getName,getMsg,simpleDateFormat.format(date),simpleTimeFormat.format(date));

                               chatLists.add(chatList);

                               if(Long.parseLong(messageTimeStamps) > Long.parseLong(MemoryData.getLastMessage(Chat.this, chatKey))){

                                   MemoryData.savelastmessage(messageTimeStamps,chatKey,Chat.this);

                                  // Timestamp timestamp = new Timestamp()
                                   //Date date = new Date();

                                   //Timestamp timestamp = new Timestamp (Long.parseLong(messageTimeStamps));


                                   loadingfirst = false;
                                   chatAdapter.updataChatList(chatLists);

                                   chatrecyclerview.scrollToPosition(chatLists.size() - 1);

                               }
                           }
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //  }

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getTxtMessage = sendBtn1.getText().toString();

                final String currentTimeStamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);

                MemoryData.savelastmessage(currentTimeStamp, chatKey, Chat.this);
                databaseReference1.child("chat").child(chatKey).child("user1").setValue(getemails);
            //    databaseReference1.child("chat").child(chatKey).child("user2").setValue(getName);

                databaseReference1.child("chat").child(chatKey).child("message").child(currentTimeStamp).child("msg").setValue(getTxtMessage);
                databaseReference.child("chat").child(chatKey).child("message").child(currentTimeStamp).child("name").setValue(getemails);

                sendBtn1.setText("");
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}