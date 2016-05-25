package com.example.lw_pc.tribblekill.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Attachment;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.adapter.ImageShowerAdapter;
import com.example.lw_pc.tribblekill.ui.fragment.DetailsFragment;
import com.example.lw_pc.tribblekill.util.MyViewPager;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageShower extends AppCompatActivity {
    private MyViewPager viewPager;
    private ImageView[] imageViews;
    private ImageShowerAdapter imageShowerAdapter;
    private PhotoView photoView;
    private ProgressBar mProgressBar;

    PhotoViewAttacher mAttacher;

    private Shot shot;
    private List<Attachment> mAttachments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_shower);
        photoView = (PhotoView) findViewById(R.id.image);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        /*imageView = (ImageView) findViewById(R.id.image);

        Picasso.with(this)
                .load(shot.getImages().getHidpi())
                .into(imageView);*/

        shot = (Shot) getIntent().getSerializableExtra(DetailsFragment.SHOT);
        //viewPager = (MyViewPager) findViewById(R.id.viewpager);
        /*imageViews = new ImageView[1 + shot.getAttachments_count()];
        Api api = DribbbleApi.getDribbbleApi();
        api.getAttachments(shot.getId()).enqueue(new Callback<List<Attachment>>() {
            @Override
            public void onResponse(Response<List<Attachment>> response, Retrofit retrofit) {
                setAttachments(response.body());
                if (imageViews.length > 1 && mAttachments.size() > 0) {
                    for (int i = 1; i < imageViews.length; i++) {
                        imageViews[i] = new ImageView(ImageShower.this);
                        *//*Picasso.with(ImageShower.this)
                                .load(mAttachments.get(i-1).getUrl())
                                .into(imageViews[i]);*//*

                        Ion.with(ImageShower.this)
                                .load(mAttachments.get(i-1).getUrl())
                                .intoImageView(imageViews[i]);
                        if (imageViews[i] != null) {
                            mAttacher = new PhotoViewAttacher(imageViews[i]);
                        }


                    }
                }

                if (imageViews[imageViews.length-1] != null) {
                    imageShowerAdapter = new ImageShowerAdapter(imageViews);
                    viewPager.setAdapter(imageShowerAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/

        /*imageViews[0] = new ImageView(this);
        Picasso.with(this)
                .load(shot.getImages().getHidpi())
                .into(imageViews[0]);*/

        Ion.with(this)
                .load(shot.getImages().getHidpi())
                .intoImageView(photoView)
                .setCallback(new FutureCallback<ImageView>() {
                    @Override
                    public void onCompleted(Exception e, ImageView result) {
                        mAttacher.update();
                        mProgressBar.setVisibility(View.GONE);
                    }
                });



    }



    public static void start(Context context, Shot shot) {
        Intent intent = new Intent(context, ImageShower.class);
        intent.putExtra(DetailsFragment.SHOT, shot);
        context.startActivity(intent);
    }

    public void setAttachments(List<Attachment> attachments) {
        mAttachments = attachments;
    }




}
