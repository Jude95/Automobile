package com.jude.automobile.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.presenter.ImageViewPresenter;
import com.jude.automobile.utils.HackyViewPager;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;


/**
* Created by Mr.Jude on 2015/2/22.
 * 展示图片的Activity
*/
@RequiresPresenter(ImageViewPresenter.class)
public class ImageViewActivity extends BeamBaseActivity<ImageViewPresenter>{
    public static String KEY_URIS = "uris";
    public static String KEY_URI = "uri";
    public static String KEY_INDEX = "index";

    private HackyViewPager mViewPager;
    private ImageView mImg_return;
    private TextView mTv_title;
    private ImagePagerAdapter mAdapter;

    Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imageview);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
        mImg_return = (ImageView) findViewById(R.id.home);
        mTv_title = (TextView) findViewById(R.id.title);

        mViewPager = (HackyViewPager) findViewById(R.id.viewpager);
        mAdapter = new ImagePagerAdapter();
        mViewPager.setAdapter(mAdapter);

        mTv_title.setOnClickListener(v->finish());
        mImg_return.setOnClickListener(v->finish());

        ArrayList<Uri> urls = getIntent().getParcelableArrayListExtra(KEY_URIS);
        if (urls == null)urls = new ArrayList<>();
        Uri uri =  getIntent().getParcelableExtra(KEY_URI);
        if (uri!=null) urls.add(uri);
        int index = getIntent().getIntExtra(KEY_INDEX,0);
//        if (urls.size()<=1){
//            mTv_lock.setVisibility(View.GONE);
//            mViewPager.toggleLock();
//        }

        mAdapter.setUrls(urls);
        mViewPager.setCurrentItem(index);
        mViewPager.setOnClickListener(v->finish());
    }

    class ImagePagerAdapter extends PagerAdapter {
        private ArrayList<Uri> urls;

        public void setUrls(ArrayList<Uri> urls){
            this.urls = urls;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return urls==null?0:urls.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ImageViewActivity.this).inflate(R.layout.item_imagepage, container, false);
            final PhotoView photoView = (PhotoView) view.findViewById(R.id.photoview);
            final View wheel = view.findViewById(R.id.wheel);
            Glide.with(container.getContext())
                    .load(urls.get(position).toString())
                    .into(photoView);
            container.addView(view);
            photoView.setOnPhotoTapListener((view1, x, y) -> finish());
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

}
