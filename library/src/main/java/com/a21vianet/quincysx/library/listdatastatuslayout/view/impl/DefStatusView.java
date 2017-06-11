package com.a21vianet.quincysx.library.listdatastatuslayout.view.impl;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a21vianet.quincysx.library.listdatastatuslayout.R;
import com.a21vianet.quincysx.library.listdatastatuslayout.view.IStateView;
import com.bumptech.glide.Glide;

/**
 * Copyright 2017 QuincySx
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class DefStatusView extends IStateView {
    private View mContentView;
    private Button mButton;
    private TextView mTextView;
    private ImageView mImageView;

    @TouchArea
    private int mTouchArea = BTN;

    public DefStatusView(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        mContentView = inflate(getContext(), R.layout.commonlayout, null);
        mContentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mButton = (Button) mContentView.findViewById(R.id.common_btn);
        mTextView = (TextView) mContentView.findViewById(R.id.common_hint);
        mImageView = (ImageView) mContentView.findViewById(R.id.common_image);
        addView(mContentView);
    }

    @Override
    public void showBtn() {
        mButton.setVisibility(VISIBLE);
    }

    @Override
    public void hideBtn() {
        mButton.setVisibility(GONE);
    }

    @Override
    public void setTouchArea(@TouchArea int area) {
        mTouchArea = area;
    }

    @Override
    public void setHintText(String text) {
        mTextView.setText(text);
    }

    @Override
    public void setHintImage(@DrawableRes int res) {
        Glide.with(getContext())
                .load(res)
                .into(mImageView);
    }

    @Override
    public void setBtnText(String text) {
        mButton.setText(text);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener callBack) {
        switch (mTouchArea) {
            case BTN:
                mButton.setOnClickListener(callBack);
                break;
            case FULL:
                mContentView.setOnClickListener(callBack);
                break;
        }
    }
}
