package com.chengfu.android.emoji.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chengfu.android.emoji.EmojiEditText;
import com.chengfu.android.emoji.EmojiTextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EmojiTextView text;
//    EmojiEditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        recyclerView=findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MainAdapter());

        text=findViewById(R.id.text);
//        edit=findViewById(R.id.edit);

        text.setEmojis(BigEmoji.getDefaultBigEmojis());

        text.setText("比心|||||||||||||||||||||||||||||比心||||||||||||||||||比心abcdefghijklmnopkrstuvwxyz||||||||||||||||||||||||||||||||||||||||||||||");
//
//        text.setEmojis(BigEmoji.getDefaultBigEmojis());

//        edit.setEmojis(BigEmoji.getDefaultBigEmojis());
//
//        edit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                text.setText(s);
//            }
//        });
    }

   class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

       @NonNull
       @Override
       public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
           return new ViewHolder(v);
       }

       @Override
       public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           if (position==2){
               holder.text.setText("学习你好啊");
               return;
           }
           holder.text.setText("学习");
       }

       @Override
       public int getItemCount() {
           return 1;
       }

       class ViewHolder extends RecyclerView.ViewHolder{
           EmojiTextView text;
           public ViewHolder(@NonNull View itemView) {
               super(itemView);

               text=itemView.findViewById(R.id.text);
               text.setEmojis(BigEmoji.getDefaultBigEmojis());
           }
       }
   }
}