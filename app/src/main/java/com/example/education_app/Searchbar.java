package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class Searchbar extends AppCompatActivity {

    EditText mSearchField;
    ImageButton mSearchBtn;

     RecyclerView mResultList;

     DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbar);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("modules");


        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        //mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

                if(searchText.equals("project")){
                    startActivity(new Intent(Searchbar.this,Module_Recycler.class));
                }
            }
        });
    }
    private void firebaseUserSearch(String searchText) {

        Toast.makeText(Searchbar.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("modules_names").startAt(searchText).endAt(searchText + "\uf8ff");
        FirebaseRecyclerOptions<Users> searchmodules = new FirebaseRecyclerOptions.Builder<Users>()
                .setQuery(firebaseSearchQuery, Users.class)
                .build();

        FirebaseRecyclerAdapter<Users, UsersViewHolder> Searchadapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(searchmodules) {
            @Override
            protected void onBindViewHolder(@NonNull final UsersViewHolder viewHolder, final int position, @NonNull final Users model) {
                //viewHolder.txtOrderPhone.setText(model.getPhone());
                viewHolder.setDetails(getApplicationContext(), model.getModules_names());
            }
            @Override
            public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);

                return new UsersViewHolder(itemView);
            }
        };
        Searchadapter.startListening();
        mResultList.setAdapter(Searchadapter); //Set adapter for Recycler View


       // mResultList.setAdapter(firebaseRecyclerAdapter);

    }


    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String userName){

            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
          //  TextView user_status = (TextView) mView.findViewById(R.id.status_text);

            user_name.setText(userName);
            //user_status.setText(userStatus);

        }

    }

}
