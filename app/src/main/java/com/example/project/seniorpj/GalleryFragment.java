package com.example.project.seniorpj;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.seniorpj.Result.ResultActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Smew on 23/9/2560.
 */

public class GalleryFragment extends Fragment {

    private ImageView imgView;
    Button btn_gallery;

    private static int IMG_RESULT = 1;
    String ImageDecode;
    Intent intent;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        getActivity().startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
        imgView = (ImageView) v.findViewById(R.id.imgView_gallery);
        btn_gallery = (Button) v.findViewById(R.id.buttonLoadPicture);

        intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMG_RESULT);

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getContext(), ResultActivity.class);
                startActivity(intent2);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                    && null != data) {

                Uri URI = data.getData();
                String[] FILE = {MediaStore.Images.Media.DATA};


                Cursor cursor = getActivity().getContentResolver().query(URI,
                        FILE, null, null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();

                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(ImageDecode));

            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Please try again", Toast.LENGTH_LONG)
                    .show();
        }

    }


}
