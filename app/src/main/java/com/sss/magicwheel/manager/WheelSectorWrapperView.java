package com.sss.magicwheel.manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.sss.magicwheel.entity.CoordinatesHolder;
import com.sss.magicwheel.entity.LinearClipData;
import com.sss.magicwheel.util.MagicCalculationHelper;

/**
 * @author Alexey Kovalev
 * @since 04.12.2015.
 */
public class WheelSectorWrapperView extends ImageView {

    private static final String TAG = WheelSectorWrapperView.class.getCanonicalName();

    private final Paint paint;
    private Path path;
    private LinearClipData linearClipData;


    public WheelSectorWrapperView(Context context) {
        this(context, null);
    }

    public WheelSectorWrapperView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setColor(Color.RED);

        path = new Path();
    }


    public void setSectorClipArea(LinearClipData linearClipData) {
        this.linearClipData = linearClipData;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw()");

        if (linearClipData == null) {
            super.onDraw(canvas);
            return;
        }

        Log.e(TAG, "Clip area " + linearClipData);

        Path pathToClip = createPathForClip(linearClipData, canvas);
        canvas.clipPath(pathToClip);

        super.onDraw(canvas);
    }


    private Path createPathForClip(LinearClipData clipData, Canvas canvas) {
        path.reset();

        CoordinatesHolder first = linearClipData.getFirst();
        CoordinatesHolder second = linearClipData.getSecond();
        CoordinatesHolder third = linearClipData.getThird();
        CoordinatesHolder four = linearClipData.getFourth();

        path.moveTo(first.getXAsFloat(), first.getYAsFloat());
        path.lineTo(second.getXAsFloat(), second.getYAsFloat());
        path.lineTo(four.getXAsFloat(), four.getYAsFloat());
        path.lineTo(third.getXAsFloat(), third.getYAsFloat());
        path.close();

        return path;

    }

}