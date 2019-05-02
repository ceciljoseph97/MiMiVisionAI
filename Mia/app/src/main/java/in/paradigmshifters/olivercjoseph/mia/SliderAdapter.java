package in.paradigmshifters.olivercjoseph.mia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.paradigmshifters.olivercjoseph.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context=context;
    }
//arrays for holding values images text etc
    public int[] slide_images={
            R.drawable.computer_vision,
            R.drawable.newfeatures_1x,
            R.drawable.main_data_exchange_icon
    };


    public String[] slide_headings={
            "Mimi Vision AI",
            "Connect with Friends",
            "Share & Chat"
    };


    public String [] slide_descs={
            "hahaha",
            "he sucks",
            "lol",
    };




    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    //instantiate item method


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slidelayout,container,false);


        ImageView slideImageView=(ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading=(TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription=(TextView) view.findViewById((R.id.slide_desc));

        //setting bby position
        slideImageView.setImageResource((slide_images[position]));
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }
//for destroying slides
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         container.removeView((RelativeLayout)object);
    }
}
