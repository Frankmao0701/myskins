package com.mykins.linkin.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

public class AlbumUtil {
    public static final int ALBUM_RESULT_CODE = 5;

    public static void openSysAlbum(Activity activity) {
        Intent albumIntent = new Intent(Intent.ACTION_PICK);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(albumIntent, ALBUM_RESULT_CODE);
    }
}
