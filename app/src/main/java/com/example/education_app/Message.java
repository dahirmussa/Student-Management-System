package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message extends AppCompatActivity {

    private final List<MessageList> messageLists = new ArrayList<>();
    private RecyclerView messageRecyclerView;
    CircleImageView Userprofile;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    private int unseenMessage = 0;
    private String lastMessage = "";
    private String chatKey = "";
    private boolean dataSet = false;

    private MessageAdapter messageAdapter;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://education-app-bb58d-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        messageRecyclerView = findViewById(R.id.messageRecyclerview);

        firebaseAuth = FirebaseAuth.getInstance();
        Userprofile = findViewById(R.id.Userprofile);

        messageRecyclerView.setHasFixedSize(true);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter(messageLists, Message.this );
        messageRecyclerView.setAdapter(messageAdapter);



      //  StorageReference ProfileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        //ProfileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
          //  @Override
            //public void onSuccess(Uri uri) {
              //  Picasso.get().load(uri).into(Userprofile);
            //}
        //});


     databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             messageLists.clear();
             unseenMessage = 0;
             lastMessage = "";
             chatKey = "";


             for(DataSnapshot dataSnapshot : snapshot.child("users").getChildren()){

                 final String getEmail = dataSnapshot.getKey();

              dataSet = false;

              if(!getEmail.isEmpty()){
                  String getName = dataSnapshot.child("emails").getValue(String.class);
                  String getProfile = dataSnapshot.child("userprofile").getValue(String.class);
                  databaseReference.child("chat").addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          int getChatCount = (int)snapshot.getChildrenCount();

                          if(getChatCount > 0)
                          {
                              for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                                final  String getKey = dataSnapshot1.getKey();
                                  chatKey = getKey;

                                  // add if statement
                                  if(dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("messages"))
                                  {
                                      String getuser1 = dataSnapshot1.child("user_1").getValue(String.class);
                                      String getuser2 = dataSnapshot1.child("user_2").getValue(String.class);
                                      if(!getuser1.equals(getEmail) && !getuser2.equals(getEmail)){ //|| (getuser1.equals(getEmail) && getuser2.equals(getEmail))){

                                          for(DataSnapshot ChatDataSnapshot : dataSnapshot1.child("message").getChildren())
                                          {
                                              final long getMessageKey = Long.parseLong(ChatDataSnapshot.getKey());
                                              final long getLastSeenMessage = Long.parseLong(MemoryData.getMessage(Message.this,getKey));

                                              lastMessage = ChatDataSnapshot.child("msg").getValue(String.class);

                                              if(getMessageKey > getLastSeenMessage)
                                              {
                                                  unseenMessage++;
                                              }
                                          }
                                      }
                                  }
                              }
                          }
                          if(!dataSet){
                              dataSet = true;
                              MessageList messageList = new MessageList(getName,getEmail,lastMessage,getProfile,unseenMessage,chatKey);
                              messageLists.add(messageList);
                              messageRecyclerView.setAdapter(new MessageAdapter(messageLists,Message.this));
                              messageAdapter.updataData(messageLists);
                          }
                      }
                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });

              }
             }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
     });
    }
}