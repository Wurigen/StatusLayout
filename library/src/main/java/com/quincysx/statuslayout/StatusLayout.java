package com.quincysx.statuslayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

public class StatusLayout extends FrameLayout {
    private View mContentView;
    private StatusView.Callback mCallback;
    /**
     * 布局存放
     */
    private final Map<Class<? extends StatusView>, StatusView> mCacheStatusViewMap = new ConcurrentHashMap<>();

    public StatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes
            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1) {
            throw new IllegalStateException(getClass().getSimpleName() + " can host only one " +
                    "direct child");
        }
        build();
    }

    private void build() {
        mContentView = getChildAt(0);
    }

    public void showAction(Class<? extends StatusView> aClass) {
        StatusView showView = getCacheStatusView(aClass);
        if (showView == null) {
            showView = getStatusView(aClass);
            if (showView == null) {
                throw new NullPointerException("View 构建失败");
            }
            addView(showView.getView());
        }
        hideViewAll();
        showView.getView().setVisibility(VISIBLE);
        mCacheStatusViewMap.put(aClass, showView);
        if (mCallback != null) {
            showView.setCallback(mCallback);
        }
    }

    private void hideViewAll() {
        for (StatusView view : mCacheStatusViewMap.values()) {
            view.getView().setVisibility(GONE);
        }
    }

    public void showSuccess() {
        hideViewAll();
        mContentView.setVisibility(VISIBLE);
    }

    private StatusView getCacheStatusView(Class<? extends StatusView> aClass) {
        return mCacheStatusViewMap.get(aClass);
    }

    private StatusView getStatusView(Class<? extends StatusView> aClass) {
        StatusView view = null;
        try {
            view = aClass.newInstance();
            view.setContext(getContext());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void setCallback(StatusView.Callback callback) {
        mCallback = callback;
    }
}
