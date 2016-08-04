package com.example.doubletapexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
	private static final int ACTION_DOWN = 0;
	private static final int ACTION_POINTER_DOWN = 1;
	private static final int OTHER = 2;
	private float firstTouchX;
	private float firstTouchY;
	private float secondTouchX;
	private float secondTouchY;

	private int state;
	private Paint paint;

	public CustomView(Context context) {
		this(context, null, 0);
		init(context);
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		init(context);
	}

	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		switch (state) {
		case ACTION_DOWN:

			canvas.drawCircle(firstTouchX, firstTouchY, 50, paint);
			return;
		case ACTION_POINTER_DOWN:

			canvas.drawCircle(firstTouchX, firstTouchY, 50, paint);
			canvas.drawCircle(secondTouchX, secondTouchY, 50, paint);
			return;
		default:
			return;

		}

	}

	private void init(Context context) {
		state = OTHER;
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		final int action = MotionEventCompat.getActionMasked(ev);

		switch (action) {
		case MotionEvent.ACTION_DOWN: {
			final float x = MotionEventCompat.getX(ev, 0);
			final float y = MotionEventCompat.getY(ev, 0);

			firstTouchX = x;
			firstTouchY = y;
			state = ACTION_DOWN;
			invalidate();
			break;
		}

		case MotionEvent.ACTION_POINTER_DOWN: {
			final float x = MotionEventCompat.getX(ev, 1);
			final float y = MotionEventCompat.getY(ev, 1);

			secondTouchX = x;
			secondTouchY = y;
			state = ACTION_POINTER_DOWN;
			invalidate();
			break;
		}

		case MotionEvent.ACTION_UP: {
			state = OTHER;
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					invalidate();
				}
			}, 500);

			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {

			state = OTHER;
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					invalidate();
				}
			}, 500);
			break;
		}
		}
		return true;
	}

}
