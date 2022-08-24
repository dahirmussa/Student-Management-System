package com.example.education_app.chat;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.education_app.MessageAdapter;
import com.example.education_app.MessageList;
import com.example.education_app.R;

import org.w3c.dom.Text;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>
{
    private List<ChatList> chatLists;
    private final Context context;

    private String Useremail;

    public ChatAdapter(List<ChatList> chatLists, Context context) {
        this.chatLists = chatLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {

        ChatList list2 = chatLists.get(position);

        if(list2.getName().equals(Useremail)){
            holder.myLayout.setVisibility(View.VISIBLE);
            holder.oppoLayout.setVisibility(View.GONE);

            holder.myMessage.setText(list2.getMessage());
            holder.myMsgTime.setText(list2.getDate() + "" + list2.getTime());

            holder.myMessage.setText(list2.getMessage());

        }else{
            holder.myLayout.setVisibility(View.GONE);
            holder.oppoLayout.setVisibility(View.VISIBLE);
            holder.oppoMessage.setText(list2.getMessage());
            holder.oppoMsgTime.setText(list2.getDate() + "" + list2.getTime());
            holder.oppoMessage.setText(list2.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatLists.size();
    }
    public void updataChatList(List<ChatList> chatLists){
        this.chatLists = chatLists ;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout oppoLayout, myLayout;
        TextView myMessage, oppoMessage;
        TextView oppoMsgTime, myMsgTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            oppoLayout = itemView.findViewById(R.id.oppoLayout);
            myLayout = itemView.findViewById(R.id.myLayout);
            myMessage = itemView.findViewById(R.id.myMessage);
            oppoMessage = itemView.findViewById(R.id.oppoMessage);
            oppoMsgTime = itemView.findViewById(R.id.oppoMsgTime);
            myMsgTime = itemView.findViewById(R.id.myMsgTime);

        }
    }
}
