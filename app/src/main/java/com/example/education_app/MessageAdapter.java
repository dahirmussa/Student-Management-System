package com.example.education_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.education_app.chat.Chat;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private  List<MessageList> messageList;
    private final Context context;

    public MessageAdapter(List<MessageList> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_adapter_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {

        MessageList list2 = messageList.get(position);

        if(!list2.getProfilePic().isEmpty())
        {
          Picasso.get().load(list2.getProfilePic()).into(holder.profilePic);
        }
        holder.name.setText(list2.getName());
        holder.lastMessage.setText(list2.getLastMessage());

        if(list2.getUseenMessage() == 0)
        {
            holder.unseenMessages.setVisibility(View.GONE);
            holder.unseenMessages.setText(list2.getUseenMessage()+ "");
            holder.lastMessage.setTextColor(context.getResources().getColor(R.color.blue));
        }else{
            holder.unseenMessages.setVisibility(View.VISIBLE);
        }

        holder.rootlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(context, Chat.class);
                 intent.putExtra("chatKey", list2.getChatKey());
                 intent.putExtra("name", list2.getName());
                 intent.putExtra("email", list2.getEmail());

                 context.startActivity(intent);
            }
        });

    }

    public void updataData(List<MessageList> messageList){
        this.messageList = messageList;


    }
    @Override
    public int getItemCount() {
        return messageList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView profilePic;
        private TextView name;
        private TextView lastMessage;
        private TextView unseenMessages;
        private LinearLayout rootlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.profilePic);
            name = itemView.findViewById(R.id.name);
            lastMessage = itemView.findViewById(R.id.lastmessage);
            unseenMessages = itemView.findViewById(R.id.unseenmessage);
            rootlayout = itemView.findViewById(R.id.rootlayout);

        }
    }
}

