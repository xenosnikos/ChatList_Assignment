package com.android.chatapp.screens.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.chatapp.R;
import com.android.chatapp.databinding.SingleChatViewBinding;
import com.android.chatapp.screens.Chat.Model.Channels;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.viewholder> {

    ArrayList<Channels> channels;
    Context context;
    public ChatAdapter(ArrayList<Channels> channels,Context context){
        this.channels=channels;
        this.context=context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        SingleChatViewBinding singleChatViewBinding =
                DataBindingUtil.inflate(inflater, R.layout.single_chat_view, parent, false);
        return new viewholder(singleChatViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        if (channels.get(position).message_last!=null) {
            holder.binding.tvname.setText(channels.get(position).message_last.user.name);
            holder.binding.tvlastmsg.setText(channels.get(position).message_last.text);
            holder.binding.tvdate.setText(channels.get(position).message_last.dta_create);

            Glide.with(context).load(channels.get(position).message_last.user.avatar_url).into(holder.binding.profileImage);

            if (channels.get(position).message_last.is_read==0){
                holder.binding.tvlastmsg.setTextColor(ContextCompat.getColor(context,R.color.teal_700));
            }else {
                holder.binding.tvlastmsg.setTextColor(ContextCompat.getColor(context,R.color.grey));
            }
             if (channels.get(position).pin_to_top==true){
                holder.binding.ivpin.setVisibility(View.VISIBLE);
            }else {
                 holder.binding.ivpin.setVisibility(View.GONE);
            }
        }
        }

    @Override
    public int getItemCount() {
        return channels.size();
    }


    public class viewholder extends RecyclerView.ViewHolder{
        SingleChatViewBinding binding;
        public viewholder(@NonNull SingleChatViewBinding itemView) {
            super(itemView.getRoot());
           this. binding=itemView;
        }

    }
}
