package com.a21vianet.quincysx.library.listdatastatuslayout.viewfactory.impl;

import android.content.Context;

import com.a21vianet.quincysx.library.listdatastatuslayout.ListDataStatusLayout;
import com.a21vianet.quincysx.library.listdatastatuslayout.R;
import com.a21vianet.quincysx.library.listdatastatuslayout.view.IStateView;
import com.a21vianet.quincysx.library.listdatastatuslayout.view.impl.DefStatusView;
import com.a21vianet.quincysx.library.listdatastatuslayout.viewfactory.IStateViewFactory;

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

public class DefStateViewFactory implements IStateViewFactory {
    public IStateView getStatusView(Context context, @ListDataStatusLayout.ViewState int
            statusview) {
        DefStatusView statusView = new DefStatusView(context);
        statusView.setBtnText(context.getString(R.string.listdatastatuslayout_btn_text));
        switch (statusview) {
            case ListDataStatusLayout.EMPTY:
                statusView.setHintText(context.getString(R.string
                        .listdatastatuslayout_def_empty_text));
                statusView.setHintImage(R.drawable.ic_data_status_empty);
                break;
            case ListDataStatusLayout.NETERROR:
                statusView.setHintText(context.getString(R.string
                        .listdatastatuslayout_def_neterror_text));
                statusView.setHintImage(R.drawable.ic_data_status_net_error);
                break;
            case ListDataStatusLayout.ERROR:
                statusView.setHintText(context.getString(R.string
                        .listdatastatuslayout_def_error_text));
                statusView.setHintImage(R.drawable.ic_data_status_error);
                break;
            case ListDataStatusLayout.LOADING:
                statusView.setHintText(context.getString(R.string
                        .listdatastatuslayout_def_loading_text));
                break;
            default:
        }
        return statusView;
    }

}
