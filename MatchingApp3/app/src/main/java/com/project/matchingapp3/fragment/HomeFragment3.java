package com.project.matchingapp3.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.dto.NavDataDto;

public class HomeFragment3 extends Fragment {

    private NavDataDto navDataDto;
    private Bitmap bitImg;

    public HomeFragment3(NavDataDto navDataDto, Bitmap bitImg){
        this.navDataDto = navDataDto;
        this.bitImg = bitImg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment3, container, false);

        TextView f1TvName = rootView.findViewById(R.id.FHome3_tv_tName);
        TextView f1TvExplain = rootView.findViewById(R.id.FHome3_tv_tExplin);
        ImageView f1IvImage = rootView.findViewById(R.id.FHome3_iv_tImage);

        f1TvName.setText(navDataDto.getT_name());
        f1TvExplain.setText(navDataDto.getT_explaintation());
        f1IvImage.setImageBitmap(bitImg);

//        Button button = rootView.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity activity = (MainActivity) getActivity();
//                activity.onFragmentChanged(0);
//            }
//        });
//        return inflater.inflate(R.layout.home_fragment3, container, false);
        return rootView;
    }

}
