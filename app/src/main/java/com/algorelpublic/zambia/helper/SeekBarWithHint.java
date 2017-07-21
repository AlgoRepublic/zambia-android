package com.algorelpublic.zambia.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.algorelpublic.zambia.R;

/**
 * Created by Adil on 11/07/2017.
 */

public class SeekBarWithHint extends SeekBar {
    private Paint seekBarHintPaint;
    private int hintTextColor;
    private float hintTextSize;

    public SeekBarWithHint(Context context) {
        super(context);
        init();
    }

    public SeekBarWithHint(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SeekBarWithHint,
                0, 0);

        try {
            hintTextColor = a.getColor(R.styleable.SeekBarWithHint_hint_text_color, 0);
            hintTextSize = a.getDimension(R.styleable.SeekBarWithHint_hint_text_size, 0);
        } finally {
            a.recycle();
        }

        init();
    }

    public SeekBarWithHint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public SeekBarWithHint(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SeekBarWithHint,
                0, 0);

        try {
            hintTextColor = a.getColor(R.styleable.SeekBarWithHint_hint_text_color, 0);
            hintTextSize = a.getDimension(R.styleable.SeekBarWithHint_hint_text_size, 0);
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        seekBarHintPaint = new TextPaint();
        seekBarHintPaint.setColor(hintTextColor);
        seekBarHintPaint.setTextAlign(Paint.Align.CENTER);
        seekBarHintPaint.setTextSize(hintTextSize);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int thumb_x = (int) (((double) this.getProgress() / this.getMax()) * (double) this.getWidth()) + 10;
//        int middle = getHeight() / 2 + 4;
        int thumb_x = (int)(((double) this.getProgress() / this.getMax()) * (double) this.getWidth());
        int middle = this.getHeight() / 2;
        canvas.drawText(String.valueOf(getProgress()), thumb_x, middle, seekBarHintPaint);
    }
}