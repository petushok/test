/*
 * Copyright 2013 Javier Tarazaga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.listviewscrolltrick;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import com.example.listviewscrolltrick.R;

public class MainActivity extends Activity {

    private MapStickyView mStickyView;
    private View mPlaceholderView;
    private MyListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout mainLayout = (FrameLayout) findViewById(R.id.main);
        mStickyView = new MapStickyView(this);
        mListView = new MyListView(this, mStickyView);

        mainLayout.addView(mListView);
        mainLayout.addView(mStickyView);


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.top_layout, null);
        mPlaceholderView = v.findViewById(R.id.placeholder);

        //Register a callback to be invoked when the global layout state or the visibility of views within the view tree changes
        mListView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        onScrollChanged();

                        ViewTreeObserver obs = mListView.getViewTreeObserver();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            obs.removeOnGlobalLayoutListener(this);
                        } else {
                            //noinspection deprecation
                            obs.removeGlobalOnLayoutListener(this);
                        }
                    }
                });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                onScrollChanged();
            }
        });
    }

    private void onScrollChanged() {
        View v = mListView.getChildAt(0);
        int top = (v == null) ? 0 : v.getTop();

        if (mListView.getChildAt(1) != null) {
            Log.d("listView trans y", String.valueOf(mListView.getChildAt(1).getTop()));
        }

        if (mListView.getFirstVisiblePosition() == 0) {
            mStickyView.setTranslationY(Math.max(-250, (mPlaceholderView.getTop() + top) / 4));
        }
    }
}

