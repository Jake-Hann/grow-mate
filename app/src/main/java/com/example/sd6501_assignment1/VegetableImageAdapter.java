package com.example.sd6501_assignment1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class VegetableImageAdapter extends BaseAdapter {

    private Integer[] vegetableImage_id = {

            R.drawable.spring_onion_bunch,
            R.drawable.cucumbers,
            R.drawable.cabbage,
            R.drawable.tomatoes,
            R.drawable.lettuce,
            R.drawable.violet_cauliflower
    };

    private Context context;

    public VegetableImageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return vegetableImage_id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Calculate width and height for GridView images based on current screen size
        int imageWidth = MainActivity.width /2;
        int imageHeight = (int) Math.round(MainActivity.height /4.3);

        ImageView vegetableImage;

        if(convertView == null){

            // Set how GridView images will be displayed
            vegetableImage = new ImageView(context);
            vegetableImage.setLayoutParams(new GridView.LayoutParams(imageWidth,imageHeight));
            vegetableImage.setScaleType(ImageView.ScaleType.FIT_XY);
            vegetableImage.setPadding(8,8,8,8);

        } else
            // Cast to imageView type
            vegetableImage = (ImageView) convertView;

        // Pass image array into vegetableImage
        vegetableImage.setImageResource(vegetableImage_id[position]);

        return vegetableImage;
    }
}