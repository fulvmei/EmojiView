package com.chengfu.android.emoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class EmojiSpan extends DynamicDrawableSpan {

    private final Context context;

    private final int resourceId;

    private int size;

    private final int marginLeft;

    private final int marginRight;

    private Drawable drawable;

    private WeakReference<Drawable> drawableRef;

    public EmojiSpan(Context context, int resourceId) {
        this(context, resourceId, 0, 0, 0, ALIGN_BOTTOM);
    }

    public EmojiSpan(Context context, int resourceId, int size) {
        this(context, resourceId, size, 0, 0, ALIGN_BOTTOM);
    }

    public EmojiSpan(Context context, int resourceId, int size, int alignment) {
        this(context, resourceId, size, 0, 0, alignment);
    }

    public EmojiSpan(Context context, int resourceId, int size, int marginLeft, int marginRight, int alignment) {
        super(alignment);
        this.context = context;
        this.resourceId = resourceId;
        this.size = size;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        Drawable d = getCachedDrawable();
        Rect rect = d.getBounds();
        if (fm != null) {
            if (mVerticalAlignment == ALIGN_CENTER) {
                Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
                int fontHeight = fmPaint.bottom - fmPaint.top;
                int drHeight = rect.bottom - rect.top;

                int top = drHeight / 2 - fontHeight / 4;
                int bottom = drHeight / 2 + fontHeight / 4;

                fm.ascent = -bottom;
                fm.top = -bottom;
                fm.bottom = top;
                fm.descent = top;
            } else {
                fm.ascent = -rect.bottom;
                fm.descent = 0;

                fm.top = fm.ascent;
                fm.bottom = 0;
            }
        }
        return rect.right + marginLeft + marginRight;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public Drawable getDrawable() {
        if (drawable == null) {
            try {
                drawable = context.getResources().getDrawable(resourceId);

                int width;
                int height;
                if (size == 0) {
                    width = drawable.getIntrinsicWidth();
                    height = drawable.getIntrinsicHeight();
                    size = Math.max(width, height);
                } else if (drawable.getIntrinsicWidth() > drawable.getIntrinsicHeight()) {
                    width = size;
                    height = width * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth();
                } else {
                    height = size;
                    width = height * drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
                }
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
            transY -= paint.getFontMetricsInt().descent;
        } else if (mVerticalAlignment == ALIGN_CENTER) {
            transY = top + (bottom - top) / 2 - b.getBounds().height() / 2;
        }

        canvas.translate(x + marginLeft, transY);
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
