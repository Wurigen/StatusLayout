package com.quincysx.statuslayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

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

public abstract class StatusView {
    private Context mContext;
    private View mView;

    public StatusView() {
    }

    public StatusView(Context context, View view, Callback callback) {
        mContext = context;
        mView = view;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public void setView(View view) {
        mView = view;
    }

    public void setCallback(final Callback callback) {
        getClickView(mView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onHandle(StatusView.this);
                onFeedback();
            }
        });
    }

    public View getView() {
        if (mView == null) {
            mView = initContentView();
        }
        return mView;
    }

    /**
     * 初始化View
     *
     * @return View
     */
    protected abstract View initContentView();

    /**
     * 点击监听回调
     */
    protected abstract void onFeedback();

    /**
     * 默认的点击区域是整个View，如果自己有别的需求重写此方法
     *
     * @param root 根布局 View
     * @return 你需要监听的 View
     */
    @NonNull
    protected View getClickView(View root) {
        return root;
    }

    public interface Callback {
        void onHandle(StatusView view);
    }
}
