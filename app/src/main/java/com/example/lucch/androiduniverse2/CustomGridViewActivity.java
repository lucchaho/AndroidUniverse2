package com.example.lucch.androiduniverse2;

/**
 * Created by lucch on 19/04/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CustomGridViewActivity extends BaseAdapter {

    private Context mContext;
    private final List gridViewString;
    private final List gridViewImageId;

    public CustomGridViewActivity(Context context, List<String> gridViewString, List<String> gridViewImageId) {
        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
    }

    @Override
    public int getCount() {
        return gridViewString.size();
    }

    @Override
    public Object getItem(int i) {
        return gridViewImageId.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View myV;
        if (convertView == null) { // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
            myV = inflater.inflate(R.layout.gridview_layout, parent, false);
        } else {
            myV = (View) convertView;
        }

        TextView text = (TextView) myV.findViewById(R.id.android_gridview_text);
        ImageView image = (ImageView) myV.findViewById(R.id.android_gridview_image);
        text.setText((String) gridViewString.get(i));
        URL url = null;
        try {
            url = new URL((String) gridViewImageId.get(i));
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            image.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myV;
    }

}
