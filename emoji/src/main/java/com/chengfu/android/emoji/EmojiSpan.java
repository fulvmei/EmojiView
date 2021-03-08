package com.chengfu.android.emoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import java.lang.ref.WeakReference;

public class EmojiSpan extends DynamicDrawableSpan {

    private final Context context;

    private final int resourceId;

    private final int size;

    private int height;

    private int width;

//    private int top;

    private Drawable drawable;

    private WeakReference<Drawable> drawableRef;

    public EmojiSpan(Context context, int resourceId, int size, int alignment) {
        super(alignment);
        this.context = context;
        this.resourceId = resourceId;
        this.width = height = this.size = size;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public Drawable getDrawable() {
        if (drawable == null) {
            try {
                drawable = context.getResources().getDrawable(resourceId);
                height = size;
                width = height * drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
//                top = (textSize - height) / 2;
//                drawable.setBounds(0, top, width, top + height);
                drawable.setBounds(0, 0, width, height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return drawable;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getCachedDrawable();
        canvas.save();

        int transY = bottom - b.getBounds().bottom;
        if (mVerticalAlignment == ALIGN_BASELINE) {
            transY = top + ((bottom - top) / 2) - ((b.getBounds().bottom - b.getBounds().top) / 2) - top;
        }

        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }

    private Drawable getCachedDrawable() {
        if (drawableRef == null || drawableRef.get() == null) {
            drawableRef = new WeakReference<>(getDrawable());
        }
        return drawableRef.get();
    }
}
