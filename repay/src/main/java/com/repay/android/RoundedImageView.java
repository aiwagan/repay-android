package com.repay.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Property of Matt Allen
 * mattallen092@gmail.com
 * http://mattallensoftware.co.uk/
 *
 * This software is distributed under the Apache v2.0 license and use
 * of the Repay name may not be used without explicit permission from the project owner.
 *
 */

public class RoundedImageView extends ImageView {

	public RoundedImageView(Context context) {
	    super(context);
	}

	public RoundedImageView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}

	public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {

	    Drawable drawable = getDrawable();

	    if (drawable == null) {
	        return;
	    }

	    if (getWidth() == 0 || getHeight() == 0) {
	        return; 
	    }
	    Bitmap b =  ((BitmapDrawable)drawable).getBitmap() ;
	    Bitmap bitmap = b.copy(Config.ARGB_8888, true);

	    int w = getWidth();


	    Bitmap roundBitmap =  getCroppedBitmap(bitmap, w);
	    canvas.drawBitmap(roundBitmap, 0,0, null);

	}

	public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
	    Bitmap sbmp;
	    if(bmp.getWidth() != radius || bmp.getHeight() != radius)
	        sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
	    else
	        sbmp = bmp;
	    Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
	            sbmp.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);

	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

	    paint.setAntiAlias(true);
	    paint.setFilterBitmap(true);
	    paint.setDither(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(Color.parseColor("#BAB399"));
	    canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,
	            sbmp.getWidth() / 2+0.1f, paint);
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(sbmp, rect, rect, paint);
	    return output;
	}
}
