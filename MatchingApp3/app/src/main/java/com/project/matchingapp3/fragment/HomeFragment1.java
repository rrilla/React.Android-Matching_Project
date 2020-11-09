package com.project.matchingapp3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.project.matchingapp3.R;

public class HomeFragment1 extends Fragment {

    ViewFlipper viewFlipper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int images[] = {
                R.drawable.banner,
                R.drawable.banner2,
                R.drawable.banner3,
                R.drawable.banner4
        };

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment1, container, false);

        viewFlipper = rootView.findViewById(R.id.image_slide);

        for(int image : images) {
            fllipperImages(image, rootView);
        }


//        Button button = rootView.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity activity = (MainActivity) getActivity();
//                activity.onFragmentChanged(0);
//            }
//        });
//        return rootView;

        return inflater.inflate(R.layout.home_fragment1, container, false);
    }

    // 이미지 슬라이더 구현 메서드
    public void fllipperImages(int image, ViewGroup rootView) {
        ImageView imageView = new ImageView(rootView.getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);      // 이미지 추가
        viewFlipper.setFlipInterval(4000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        viewFlipper.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        viewFlipper.setInAnimation(rootView.getContext(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(rootView.getContext(),android.R.anim.slide_out_right);
    }


}
