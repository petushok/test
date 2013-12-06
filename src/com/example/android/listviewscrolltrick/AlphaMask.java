package com.example.android.listviewscrolltrick;

import android.content.Context;
import android.graphics.*;
import android.view.View;
import com.example.listviewscrolltrick.R;

/**
 * Created with IntelliJ IDEA.
 * User: Я
 * Date: 22.09.13
 */
    public class AlphaMask extends View {
        Bitmap filteredBitmap;
        Bitmap bitmap;
        Paint colorFilterPaint;

        public AlphaMask(Context ctx) {
            super(ctx);

            bitmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backgroud);
            Bitmap mask = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.mask);

            float[] src = {
                    0, 0, 0, 0, 255,
                    0, 0, 0, 0, 255,
                    0, 0, 0, 0, 255,
                    1, 1, 1, -1, 0,
            };
            ColorMatrix cm = new ColorMatrix(src);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
            Paint maskPaint = new Paint();
            maskPaint.setColorFilter(filter);
            maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

            filteredBitmap = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(filteredBitmap);
            c.drawBitmap(bitmap, 0, 0, null);
            c.drawBitmap(mask, 0, 0, maskPaint);

            colorFilterPaint = new Paint();
            colorFilterPaint.setColorFilter(new LightingColorFilter(0x00000000, 0xffffff));
        }

//        @Override
//        public void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//
//            canvas.save();
//            Paint maskPaint = new Paint();
//            maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//
//            Paint imagePaint = new Paint();
//            imagePaint.setFilterBitmap(false);
//            imagePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
//
//            canvas.drawBitmap(mMask, 10, 10, maskPaint);
//            canvas.drawBitmap(mImage, mPosX, mPosY, imagePaint);
//            canvas.restore();
//
//
//
//        }

        @Override
        public void draw(Canvas canvas) {
            canvas.save();
            //  canvas.scale(3, 3);
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.drawBitmap(filteredBitmap, 0, 0, colorFilterPaint);
            canvas.restore();
        }
    }