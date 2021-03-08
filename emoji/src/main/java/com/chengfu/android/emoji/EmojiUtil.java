package com.chengfu.android.emoji;

import android.content.Context;
import android.text.Spannable;
import android.util.ArrayMap;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmojiUtil {
    public static void buildEmojiSpannable(Context context, int size, int alignment, Spannable spannable, List<Emoji> emojis) {
        if (spannable == null) {
            return;
        }
        EmojiSpan[] oldSpans = spannable.getSpans(0, spannable.length(), EmojiSpan.class);
        for (EmojiSpan oldSpan : oldSpans) {
            spannable.removeSpan(oldSpan);
        }
        if (emojis != null) {
            Map<String, Integer> emojiResMap = new ArrayMap<>();
            StringBuilder regex = new StringBuilder();
            for (int i = 0; i < emojis.size(); i++) {
                Emoji emoji = emojis.get(i);
                if (i != 0) {
                    regex.append("|");
                }
                emojiResMap.put(emoji.getCode(), emoji.getRes());
                regex.append("(");
                regex.append("\\");
                regex.append(emoji.getCode());
                regex.append(")");

            }

            Pattern pattern = Pattern.compile(regex.toString(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(spannable);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String group = matcher.group();
                Integer resourceId = emojiResMap.get(group);
                if (resourceId != null && resourceId > 0) {
                    EmojiSpan emojiSpan = new EmojiSpan(context, resourceId, size, alignment);
                    spannable.setSpan(emojiSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
    }

}
