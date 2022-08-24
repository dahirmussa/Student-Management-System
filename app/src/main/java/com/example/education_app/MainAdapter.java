package com.example.education_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myviewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull MainModel model) {

        holder.roles.setText(model.getRoles());
        holder.password.setText(model.getPassword());
        holder.email.setText(model.getEmails());


        Glide.with(holder.img.getContext())
                .load(model.getUserprofile())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


        //Glide.with(holder.img.getContext()).load(model.getUserprofile());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_pop))
                        .setExpanded(true, 1200)
                        .create();

                // dialogPlus.show();

                View view = dialogPlus.getHolderView();
                EditText email = view.findViewById(R.id.txtemail);
                EditText password = view.findViewById(R.id.txtpass);
                EditText role = view.findViewById(R.id.textrole);
                EditText urprofile = view.findViewById(R.id.profiles);
                Button btnUpdate = view.findViewById(R.id.btnupdate);

                email.setText(model.getEmails());
                password.setText(model.getPassword());
                role.setText(model.getRoles());
                urprofile.setText(model.getUserprofile());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("emails", email.getText().toString());
                        map.put("password", password.getText().toString());
                        map.put("roles", role.getText().toString());
                        map.put("userprofile", urprofile.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("users").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                 Toast.makeText(holder.email.getContext(),"Data updated", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText (holder.email.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
            }
        });

        holder.thedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.email.getContext());

                builder.setTitle("Are you sure");

                builder.setMessage("deleted data can't be undo");

                builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("users").child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.email.getContext(),"Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                    builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_items,parent,false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView email, password, roles;

        Button thedelete, edit;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            password = (TextView) itemView.findViewById(R.id.emailtext);
            email = (TextView) itemView.findViewById(R.id.coursetext);
            roles = (TextView) itemView.findViewById(R.id.role);

            edit = (Button) itemView.findViewById(R.id.edit);
            thedelete = (Button) itemView.findViewById(R.id.delete);

        }
    }
}

