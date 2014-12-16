package android.com.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rwils on 12/16/14.
 */
public class DateTile extends View {
    private final Drawable mIcon;
    private final Paint mPaint;
    private final int mSize;

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p/>
     * <p/>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #View(android.content.Context, android.util.AttributeSet, int)
     */
    public DateTile(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DateTile,
                0, 0
        );

        try {
            mIcon = a.getDrawable(R.styleable.DateTile_tileIcon);
            mSize = a.getInteger(R.styleable.DateTile_tileSize, 0);
        } finally {
            a.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bm = Bitmap.createBitmap(mIcon.getIntrinsicWidth(), mIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas tmp = new Canvas(bm);
        mIcon.setBounds(0, 0, tmp.getWidth(), tmp.getHeight());
        mIcon.draw(tmp);

        canvas.drawBitmap(Bitmap.createScaledBitmap(bm, 128, 128, false), 0, 0, mPaint);
    }
}
