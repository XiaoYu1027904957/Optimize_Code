/*
Copyright 2014 David Morrissey

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.xiaoyu.rewritebaisi.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.app.ImageDisplayActivity;
public class ImageDisplayLargeFragment extends Fragment {

    private final String imageurl;

    public ImageDisplayLargeFragment(String imageurl) {

        this.imageurl = imageurl;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.imagedisplay_large_fragment, container, false);
        rootView.findViewById(R.id.next).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ImageDisplayActivity)getActivity()).next();
            }
        });
        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)rootView.findViewById(R.id.imageView);

        imageView.setImage(ImageSource.asset("card.png"));
        return rootView;
    }

}
