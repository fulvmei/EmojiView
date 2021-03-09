package com.chengfu.android.emoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.List;

public class EmojiTextView extends AppCompatTextView {
    int emojiSize;
    int emojiAlign;
    List<Emoji> emojis;

    public EmojiTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EmojiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EmojiTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        emojiSize = (int) getTextSize();
        emojiAlign = DynamicDrawableSpan.ALIGN_BOTTOM;
        if (attrs != null) {
            @SuppressLint("CustomViewStyleable")
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EmojiView);
            emojiSize = (int) a.getDimension(R.styleable.EmojiView_emojiSize, getTextSize());
            emojiAlign = a.getInt(R.styleable.EmojiView_emojiAlign, DynamicDrawableSpan.ALIGN_BOTTOM);
            a.recycle();
        }
        updateEmoji();
    }


    public int getEmojiSize() {
        return emojiSize;
    }

    public void setEmojiSize(int emojiSize) {
        this.emojiSize = emojiSize;
        updateEmoji();
    }

    public int getEmojiAlign() {
        return emojiAlign;
    }

    public void setEmojiAlign(int emojiAlign) {
        this.emojiAlign = emojiAlign;
        updateEmoji();
    }

    public List<Emoji> getEmojis() {
        return emojis;
    }

    public void setEmojis(List<Emoji> emojis) {
        this.emojis = emojis;
        updateEmoji();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!TextUtils.isEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            EmojiUtil.buildEmojiSpannable(getContext(), getEmojiSize(), getEmojiAlign(), builder, getEmojis());
            text = builder;
        }
        super.setText(text, type);
    }

    private void updateEmoji() {
        setText(getText());
    }
}
