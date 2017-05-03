package com.example.ruixu.imagegallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rui on 5/3/2017.
 */

public class ImageUtil {
    public static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) { return 90; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {  return 180; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {  return 270; }
        return 0;
    }

    public static Bitmap rotateImage (InputStream input){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(input);
            ExifInterface exif = new ExifInterface(input);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotationInDegrees = ImageUtil.exifToDegrees(rotation);
            Matrix matrix = new Matrix();
            if (rotation != 0f) {matrix.preRotate(rotationInDegrees);}
            bitmap = Bitmap.createBitmap(bitmap,0,0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return bitmap;
    }
}
