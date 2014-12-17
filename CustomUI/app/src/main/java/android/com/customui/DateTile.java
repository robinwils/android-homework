package android.com.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rwils on 12/16/14.
 */
public class DateTile extends View {
    private final Paint mPaint;
    private final SimpleDateFormat dateFormat, timeFormat, pmFormat;
    private String pmString;
    private String dateString;
    private String timeString;

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

        } finally {
            a.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        this.dateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy");
        this.timeFormat = new SimpleDateFormat("hh:mm");
        this.pmFormat = new SimpleDateFormat("aaa");
    }

    void getTimeStrings() {
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        dateString = dateFormat.format(time);
        timeString = timeFormat.format(time);
        pmString = pmFormat.format(time);
    }


    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getTimeStrings();
        mPaint.setTextSize(40);
        canvas.drawText(dateString, 20, 50, mPaint);
        mPaint.setTextSize(120);
        canvas.drawText(timeString, 20, 150, mPaint);
        mPaint.setTextSize(30);
        canvas.drawText(pmString, 320, 90, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int h = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(w, h);
    }
}
