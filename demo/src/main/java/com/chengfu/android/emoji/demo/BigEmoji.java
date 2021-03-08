package com.chengfu.android.emoji.demo;

import com.chengfu.android.emoji.Emoji;

import java.util.ArrayList;
import java.util.List;

public class BigEmoji {

    public static final String[] NAMES = {
            "nice",
            "woo",
            "yeah",
            "唉",
            "比心",
            "吃瓜",
            "充满活力",
            "呔",
            "调皮",
            "扶我起来",
            "佛系",
            "告辞",
            "给你花花",
            "加油",
            "惊",
            "哭唧唧",
            "收到",
            "躺",
            "完全ok",
            "晚安",
            "委屈巴巴",
            "我酸了",
            "无比震惊",
            "学习"};
    public static final int RES[] = {
            R.drawable.comment_ic_big_emoji1,
            R.drawable.comment_ic_big_emoji2,
            R.drawable.comment_ic_big_emoji3,
            R.drawable.comment_ic_big_emoji4,
            R.drawable.comment_ic_big_emoji5,
            R.drawable.comment_ic_big_emoji6,
            R.drawable.comment_ic_big_emoji7,
            R.drawable.comment_ic_big_emoji8,
            R.drawable.comment_ic_big_emoji9,
            R.drawable.comment_ic_big_emoji10,
            R.drawable.comment_ic_big_emoji11,
            R.drawable.comment_ic_big_emoji12,
            R.drawable.comment_ic_big_emoji13,
            R.drawable.comment_ic_big_emoji14,
            R.drawable.comment_ic_big_emoji15,
            R.drawable.comment_ic_big_emoji16,
            R.drawable.comment_ic_big_emoji17,
            R.drawable.comment_ic_big_emoji18,
            R.drawable.comment_ic_big_emoji19,
            R.drawable.comment_ic_big_emoji20,
            R.drawable.comment_ic_big_emoji21,
            R.drawable.comment_ic_big_emoji22,
            R.drawable.comment_ic_big_emoji23,
            R.drawable.comment_ic_big_emoji24};

    public static List<Emoji> getDefaultBigEmojis() {
        List<Emoji> emojis = new ArrayList<>();
        for (int i = 0; i < NAMES.length; i++) {
            Emoji bigEmoji = new Emoji("[" + NAMES[i] + "]", RES[i], NAMES[i]);
            emojis.add(bigEmoji);
        }
        return emojis;
    }

}
