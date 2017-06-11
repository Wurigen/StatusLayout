package com.a21vianet.quincysx.library.listdatastatuslayout.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

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

public abstract class IStateView extends LinearLayout {
    public final static int BTN = 0x0001;
    public final static int FULL = 0x0002;

    public IStateView(Context context) {
        this(context, null);
    }

    public IStateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IStateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public final void hideView() {
        setVisibility(GONE);
    }

    public final void showView() {
        setVisibility(VISIBLE);
    }

    protected abstract void initView();

    public abstract void showBtn();

    public abstract void hideBtn();

    public abstract void setTouchArea(@TouchArea int area);

    public abstract void setHintText(String text);

    public abstract void setHintImage(@DrawableRes int res);

    public abstract void setBtnText(String text);

    @IntDef({BTN, FULL})
    public @interface TouchArea {
    }
}
