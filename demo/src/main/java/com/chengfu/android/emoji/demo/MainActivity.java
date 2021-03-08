package com.chengfu.android.emoji.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.chengfu.android.emoji.EmojiEditText;
import com.chengfu.android.emoji.EmojiTextView;

public class MainActivity extends AppCompatActivity {

    EmojiTextView text;
    EmojiEditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        text=findViewById(R.id.text);
        edit=findViewById(R.id.edit);

        text.setEmojis(BigEmoji.getDefaultBigEmojis());

        edit.setEmojis(BigEmoji.getDefaultBigEmojis());

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                text.setText(s);
            }
        });
    }
}