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

import com.a21vianet.quincysx.library.listdatastatuslayout.view.IView;
import com.a21vianet.quincysx.library.listdatastatuslayout.viewfactory.IStateViewFactory;
import com.a21vianet.quincysx.library.listdatastatuslayout.viewfactory.impl.DefStateViewFactory;

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


    /**
     * 内部嵌套组件-默认方式
     */
    public static final int NEST = 0x00006;

    /**
     * 覆盖方式使用
     */
    public static final int COVER = 0x00007;

    private View mContentView;

    private IView mLoadingView;
    private IView mErrorView;
    private IView mEmptyView;
    private IView mNetErrorView;

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
                case SUCCESS:
                    showContentView();
                    break;
                case ERROR:
                    if (mErrorView != null) {
                        mErrorView.showView();
                    }
                    break;
                case EMPTY:
                    if (mEmptyView != null) {
                        mEmptyView.showView();
                    }
                    break;
                case NETERROR:
                    if (mNetErrorView != null) {
                        mNetErrorView.showView();
                    }
                    break;
                case LOADING:
                    if (mLoadingView != null) {
                        mLoadingView.showView();
                    }
                    break;
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

        if (getChildCount() > 0) {
            mContentView = getChildAt(0);
        } else {
            if (mViewModel == NEST) {
                throw new RuntimeException("因为您的模式选择为 NEST 方式，所以控件内必须要有一个子布局!");
            }
        }

        mIStateViewFactory = createStateViewFactory();

        setLoadingView(sBuilder.loaddingView == null ? mIStateViewFactory.getStatusView
                (getContext(), LOADING) : sBuilder.loaddingView);
        setErrorView(sBuilder.errorView == null ? mIStateViewFactory.getStatusView(
                getContext(), ERROR) : sBuilder.errorView);
        setEmptyView(sBuilder.emptyView == null ? mIStateViewFactory.getStatusView(
                getContext(), EMPTY) : sBuilder.emptyView);
        setNetErrorView(sBuilder.netErrorView == null ? mIStateViewFactory.getStatusView
                (getContext(), NETERROR) : sBuilder.netErrorView);

        addView(mLoadingView);
        addView(mErrorView);
        addView(mEmptyView);
        addView(mNetErrorView);

        hideAllView();

        showContentView();
    }

    protected IStateViewFactory createStateViewFactory() {
        return new DefStateViewFactory();
    }

    public void setStatus(@ViewState int status) {
        mHandler.sendEmptyMessage(status);
    }

    private void hideAllView() {
        hideContentView();
        if (mLoadingView != null) {
            mLoadingView.hideView();
        }
        if (mErrorView != null) {
            mErrorView.hideView();
        }
        if (mEmptyView != null) {
            mEmptyView.hideView();
        }
        if (mNetErrorView != null) {
            mNetErrorView.hideView();
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

    public IView getLoadingView() {
        return mLoadingView;
    }

    public IView getErrorView() {
        return mErrorView;
    }

    public IView getEmptyView() {
        return mEmptyView;
    }

    public IView getNetErrorView() {
        return mNetErrorView;
    }

    @ViewState
    public int getCurrentState() {
        return mCurrentState;
    }

    public ListDataStatusLayout setLoadingView(IView loadingView) {
        mLoadingView = loadingView;
        return this;
    }

    public ListDataStatusLayout setErrorView(IView errorView) {
        mErrorView = errorView;
        return this;
    }

    public ListDataStatusLayout setEmptyView(IView emptyView) {
        mEmptyView = emptyView;
        return this;
    }

    public ListDataStatusLayout setNetErrorView(IView netErrorView) {
        mNetErrorView = netErrorView;
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
        mNetErrorView.setOnClickListener(onClickListener);
        mLoadingView.setOnClickListener(onClickListener);
        mErrorView.setOnClickListener(onClickListener);
        mEmptyView.setOnClickListener(onClickListener);
    }

    public static Builder getBuilder() {
        return sBuilder;
    }

    public static class Builder {
        private IView loaddingView;
        private IView errorView;
        private IView emptyView;
        private IView netErrorView;

        @ViewModel
        private int viewModel = NEST;

        public Builder setLoaddingView(IView view) {
            sBuilder.loaddingView = view;
            return sBuilder;
        }

        public Builder setErrorView(IView view) {
            sBuilder.errorView = view;
            return sBuilder;
        }

        public Builder setEmptyView(IView view) {
            sBuilder.emptyView = view;
            return sBuilder;
        }

        public Builder setNetErrorView(IView view) {
            sBuilder.netErrorView = view;
            return sBuilder;
        }

        public Builder setViewModel(@ViewModel int model) {
            sBuilder.viewModel = model;
            return sBuilder;
        }
    }

    @IntDef({SUCCESS, ERROR, EMPTY, NETERROR, LOADING})
    public @interface ViewState {
    }

    @IntDef({NEST, COVER})
    public @interface ViewModel {
    }
}
