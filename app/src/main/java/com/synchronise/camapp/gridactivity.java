package com.synchronise.camapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class gridactivity extends AppCompatActivity {



    GridView androidGridView;
    ArrayList<byte[]> list;


    Integer[] imageIDs;
    Bitmap bitmap2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridactivity);


        androidGridView =  findViewById(R.id.gridView);
        androidGridView.setAdapter(new ImageAdapterGridView(this));


        list = new ArrayList<>();



        // get all data from sqlite

        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM FOOD1");
        list.clear();
        while (cursor.moveToNext()) {
            byte[] image = cursor.getBlob(1);


            list.add(image);
        }
        cursor.close();











        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                Toast.makeText(getBaseContext(), "Grid Item " + (position + 1) + " Selected", Toast.LENGTH_LONG).show();
            }
        });

    }

    public class ImageAdapterGridView extends BaseAdapter {
        private Context mContext;

        public ImageAdapterGridView(Context c) {
            mContext = c;
        }

        public int getCount() {
            return (list == null) ? 0 : list.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView mImageView;


                Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(position), 0, list.get(position).length);






            if (convertView == null) {
                mImageView = new ImageView(mContext);
                mImageView.setLayoutParams(new GridView.LayoutParams(130, 130));
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mImageView.setPadding(16, 16, 16, 16);
            } else {
                mImageView = (ImageView) convertView;
            }
            mImageView.setImageBitmap(bitmap);
            return mImageView;
        }
    }

    }

