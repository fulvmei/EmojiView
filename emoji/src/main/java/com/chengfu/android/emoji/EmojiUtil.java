package com.chengfu.android.emoji;

import android.content.Context;
import android.text.Spannable;
import android.util.ArrayMap;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class EmojiUtil {

    public static void buildEmojiSpannable(Context context, Spannable spannable, List<Emoji> emojis) {
        buildEmojiSpannable(context, spannable, emojis, 0);
    }

    public static void buildEmojiSpannable(Context context, Spannable spannable, List<Emoji> emojis, int size) {
        buildEmojiSpannable(context, spannable, emojis, size, 0, 0);
    }

    public static void buildEmojiSpannable(Context context, Spannable spannable, List<Emoji> emojis, int size, int marginLeft, int marginRight) {
        buildEmojiSpannable(context, spannable, emojis, IEmojiView.MATCH_TYPE_FULL, size, marginLeft, marginRight, IEmojiView.ALIGN_BOTTOM);
    }

    public static void buildEmojiSpannable(Context context, Spannable spannable, List<Emoji> emojis, @IEmojiView.MatchType int matchType, int size, int marginLeft, int marginRight, @IEmojiView.Align int alignment) {
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
            int maxCharLength = 0;
            for (int i = 0; i < emojis.size(); i++) {
                Emoji emoji = emojis.get(i);
                if (maxCharLength < emoji.code.length()) {
                    maxCharLength = emoji.code.length();
                }
                if (i != 0) {
                    regex.append("|");
                }
                emojiResMap.put(emoji.getCode(), emoji.getRes());
                regex.append("(");
                char c = emoji.getCode().charAt(0);
                if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9')) {
                    regex.append("\\");
                }
                regex.append(emoji.getCode());
                regex.append(")");

            }
            Pattern pattern = Pattern.compile(regex.toString(), Pattern.CASE_INSENSITIVE);
            Matcher matcher;
            if (spannable.length() > maxCharLength) {
                if (matchType == IEmojiView.MATCH_TYPE_START) {
                    matcher = pattern.matcher(spannable.subSequence(0, maxCharLength));
                } else {
                    matcher = pattern.matcher(spannable);
                }
            } else {
                maxCharLength = spannable.length();
                matcher = pattern.matcher(spannable);
            }
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                if (matchType == IEmojiView.MATCH_TYPE_START && start != 0) {
                    break;
                }
                String group = matcher.group();
                Integer resourceId = emojiResMap.get(group);
                if (resourceId != null && resourceId > 0) {
                    EmojiSpan emojiSpan = new EmojiSpan(context, resourceId, size, marginLeft, marginRight, alignment);
                    spannable.setSpan(emojiSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (matchType == IEmojiView.MATCH_TYPE_ONE) {
                    break;
                }
            }
        }
    }

}
