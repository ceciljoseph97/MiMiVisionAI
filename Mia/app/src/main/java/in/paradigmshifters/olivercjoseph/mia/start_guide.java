package in.paradigmshifters.olivercjoseph.mia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.paradigmshifters.olivercjoseph.R;

public class start_guide extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;
    private SliderAdapter sliderAdapter;


    private Button mNextBtn;
    private Button mBackBtn;


    private int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_guide);
        //initslideviewpager
        mSlideViewPager=(ViewPager) findViewById(R.id.slideViewPager);
        //initdotLayout
        mDotLayout=(LinearLayout) findViewById(R.id.dotsLayout);
        //Button Functionality
        mNextBtn=(Button) findViewById(R.id.nextBtn);
        mBackBtn=(Button) findViewById(R.id.backBtn);

        //slideradapter
        sliderAdapter=new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        //onClicklisteners
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = mNextBtn.getText().toString();
                if(buttonText=="Finish"){Intent startIntent=new Intent(start_guide.this,StartActivity.class);
                    startActivity(startIntent);
                    finish();}else {
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                }
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }

    public  void addDotsIndicator(int position){
        mDots=new TextView[3];
        mDotLayout.removeAllViews();

        for(int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));


            mDotLayout.addView(mDots[i]);

        }

        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
        addDotsIndicator(position);

        mCurrentPage=position;
     // finding the target tabs
        if(position==0){
            mNextBtn.setEnabled(true);
            mBackBtn.setEnabled(false);
            mBackBtn.setVisibility(View.INVISIBLE);

            mNextBtn.setText("Next");
            mBackBtn.setText("");
        }else if(position==mDots.length-1){
            mNextBtn.setEnabled(true);
            mBackBtn.setEnabled(true);
            mBackBtn.setVisibility(View.VISIBLE);

            mNextBtn.setText("Finish");
            mBackBtn.setText("Back");

        }else{
            mNextBtn.setEnabled(true);
            mBackBtn.setEnabled(true);
            mBackBtn.setVisibility(View.VISIBLE);

            mNextBtn.setText("Next");
            mBackBtn.setText("Back");
        }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };










}
