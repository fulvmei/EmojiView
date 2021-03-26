package com.chengfu.android.emoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.List;

public class EmojiEditText extends AppCompatEditText implements IEmojiView {
    int emojiSize;
    int marginLeft;
    int marginRight;
    @Align
    int emojiAlign;
    @MatchType
    int emojiMatchType;
    List<Emoji> emojis;

    public EmojiEditText(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        emojiSize = (int) getTextSize();
        emojiAlign = ALIGN_BOTTOM;
        emojiMatchType = MATCH_TYPE_FULL;
        if (attrs != null) {
            @SuppressLint("CustomViewStyleable")
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EmojiView);
            emojiSize = (int) a.getDimension(R.styleable.EmojiView_emojiSize, getTextSize());
            marginLeft = (int) a.getDimension(R.styleable.EmojiView_emojiMarginLeft, 0);
            marginRight = (int) a.getDimension(R.styleable.EmojiView_emojiMarginRight, 0);
            emojiAlign = a.getInt(R.styleable.EmojiView_emojiAlign, ALIGN_BOTTOM);
            emojiMatchType = a.getInt(R.styleable.EmojiView_emojiMatchType, MATCH_TYPE_FULL);
            a.recycle();
        }
    }

    @Override
    public int getEmojiSize() {
        return emojiSize;
    }

    @Override
    public void setEmojiSize(int emojiSize) {
        this.emojiSize = emojiSize;
        updateEmoji();
    }

    @Override
    public int getMarginLeft() {
        return marginLeft;
    }

    @Override
    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        updateEmoji();
    }

    @Override
    public int getMarginRight() {
        return marginRight;
    }

    @Override
    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        updateEmoji();
    }

    @Override
    public int getEmojiAlign() {
        return emojiAlign;
    }

    @Override
    public void setEmojiAlign(int emojiAlign) {
        this.emojiAlign = emojiAlign;
        updateEmoji();
    }

    @Override
    public int getEmojiMatchType() {
        return emojiMatchType;
    }

    @Override
    public void setEmojiMatchType(int emojiMatchType) {
        this.emojiMatchType = emojiMatchType;
        updateEmoji();
    }

    @Override
    public List<Emoji> getEmojis() {
        return emojis;
    }

    @Override
    public void setEmojis(List<Emoji> emojis) {
        this.emojis = emojis;
        updateEmoji();
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        updateEmoji();
    }

    private void updateEmoji() {
        EmojiUtil.buildEmojiSpannable(getContext(), getText(), emojis, emojiMatchType, emojiSize, marginLeft, marginRight, emojiAlign);
    }
}
