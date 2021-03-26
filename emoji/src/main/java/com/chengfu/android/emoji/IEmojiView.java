package com.chengfu.android.emoji;

import android.annotation.SuppressLint;
import android.text.style.DynamicDrawableSpan;

import androidx.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public interface IEmojiView {

    /**
     * Align. One of {@link #ALIGN_BOTTOM}, {@link #ALIGN_BASELINE} or {@link #ALIGN_CENTER}.
     */
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ALIGN_BOTTOM, ALIGN_BASELINE, ALIGN_CENTER})
    @interface Align {
    }

    int ALIGN_BOTTOM = DynamicDrawableSpan.ALIGN_BOTTOM;
    int ALIGN_BASELINE = DynamicDrawableSpan.ALIGN_BASELINE;
    @SuppressLint("InlinedApi")
    int ALIGN_CENTER = DynamicDrawableSpan.ALIGN_CENTER;

    /**
     * Match type. One of {@link #MATCH_TYPE_FULL}, {@link #MATCH_TYPE_START} or {@link #MATCH_TYPE_ONE}.
     */
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MATCH_TYPE_FULL, MATCH_TYPE_START, MATCH_TYPE_ONE})
    @interface MatchType {
    }

    int MATCH_TYPE_FULL = 0;
    int MATCH_TYPE_START = 1;
    int MATCH_TYPE_ONE = 2;

    int getEmojiSize();

    void setEmojiSize(int emojiSize);

    @Align
    int getEmojiAlign();

    void setEmojiAlign(@Align int emojiAlign);

    int getMarginLeft();

    void setMarginLeft(int marginLeft);

    int getMarginRight();

    void setMarginRight(int marginRight);

    @MatchType
    int getEmojiMatchType();

    void setEmojiMatchType(@MatchType int emojiMatchType);

    List<Emoji> getEmojis();

    void setEmojis(List<Emoji> emojis);
}
