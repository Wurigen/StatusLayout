package com.a21vianet.quincysx.library.listdatastatuslayout;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.a21vianet.quincysx.library.listdatastatuslayout.view.IStateView;
import com.a21vianet.quincysx.library.listdatastatuslayout.viewfactory.IStateViewFactory;

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

public class ListDataStatusLayout extends FrameLayout {
    //视图
    public static final int SUCCESS = 0x00001;
    public static final int ERROR = 0x00002;
    public static final int EMPTY = 0x00003;
    public static final int NETERROR = 0x00004;
    public static final int LOADING = 0x00005;
    public static final int MORE1 = 0x00006;
    public static final int MORE2 = 0x00007;


    /**
     * 内部嵌套组件-默认方式
     */
    public static final int NEST = 0x00006;

    /**
     * 覆盖方式使用
     */
    public static final int COVER = 0x00007;

    private View mContentView;

    /**
     * 布局存放
     */
    private Map<Integer, IStateView> mStateViewMap;

    @ViewState
    private int mCurrentState;

    @ViewModel
    private int mViewModel;

    private IStateViewFactory mIStateViewFactory;

    private static Builder sBuilder = new Builder();

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentState = msg.what;
            hideAllView();
            switch (msg.what) {
                case ERROR:
                case EMPTY:
                case NETERROR:
                case LOADING:
                case MORE1:
                case MORE2:
                    if (mStateViewMap.get(mCurrentState) != null) {
                        mStateViewMap.get(mCurrentState).showView();
                    }
                    break;
                default:
                    showContentView();
            }
        }
    };

    public ListDataStatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public ListDataStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListDataStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes
            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException(getClass().getSimpleName() + " can host only one " +
                    "direct child");
        }
        build();
    }

    private void build() {
        mViewModel = sBuilder.viewModel;
        mStateViewMap = sBuilder.stateViewMap;

        if (getChildCount() > 0) {
            mContentView = getChildAt(0);
        } else {
            if (mViewModel == NEST) {
                throw new RuntimeException("因为您的模式选择为 NEST 方式，所以控件内必须要有一个子布局!");
            }
        }

        addAllView();

        hideAllView();

        showContentView();
    }

    private void addAllView() {
        for (IStateView view : mStateViewMap.values()) {
            addView(view);
        }
    }

    public void setStatus(@ViewState int status) {
        mHandler.sendEmptyMessage(status);
    }

    private void hideAllView() {
        hideContentView();
        for (IStateView view : mStateViewMap.values()) {
            view.hideView();
        }
    }

    private void showContentView() {
        switch (mViewModel) {
            case NEST:
                if (getVisibility() == GONE) {
                    setVisibility(VISIBLE);
                }
                mContentView.setVisibility(VISIBLE);
                break;
            case COVER:
                setVisibility(GONE);
                break;
        }
    }

    private void hideContentView() {
        switch (mViewModel) {
            case NEST:
                if (getVisibility() == GONE) {
                    setVisibility(VISIBLE);
                }
                mContentView.setVisibility(GONE);
                break;
            case COVER:
                setVisibility(VISIBLE);
                break;
        }
    }

    public IStateView getStateView(@ViewState int stateView) {
        return mStateViewMap.get(stateView);
    }

    @ViewState
    public int getCurrentState() {
        return mCurrentState;
    }

    public ListDataStatusLayout addStateView(@ViewState int stateView, IStateView view) {
        view.hideView();
        mStateViewMap.put(stateView, view);
        addView(mStateViewMap.get(stateView));
        return this;
    }

    public ListDataStatusLayout setViewModel(@ViewModel int model) {
        mViewModel = model;
        return this;
    }

    @ViewModel
    public int getViewModel() {
        return mViewModel;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        for (IStateView view : mStateViewMap.values()) {
            view.setOnClickListener(onClickListener);
        }
    }

    public static Builder getBuilder() {
        return sBuilder;
    }

    public static class Builder {
        private Map<Integer, IStateView> stateViewMap = new ConcurrentHashMap<>();

        @ViewModel
        private int viewModel = NEST;

        public Builder addStateView(@ViewState int stateView, IStateView view) {
            view.hideView();
            stateViewMap.put(stateView, view);
            return sBuilder;
        }

        public Builder setViewModel(@ViewModel int model) {
            sBuilder.viewModel = model;
            return sBuilder;
        }
    }

    @IntDef({SUCCESS, ERROR, EMPTY, NETERROR, LOADING, MORE1, MORE2})
    public @interface ViewState {
    }

    @IntDef({NEST, COVER})
    public @interface ViewModel {
    }
}
