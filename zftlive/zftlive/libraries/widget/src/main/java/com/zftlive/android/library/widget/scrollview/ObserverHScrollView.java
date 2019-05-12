/*
 *     Android基础开发个人积累、沉淀、封装、整理共通
 *     Copyright (c) 2016. 曾繁添 <zftlive@163.com>
 *     Github：https://github.com/zengfantian || http://git.oschina.net/zftlive
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.zftlive.android.library.widget.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义的 滚动控件,重载了 onScrollChanged（滚动条变化）,监听每次的变化通知给 观察(此变化的)观察者可使用 AddOnScrollChangedListener 来订阅本控件的
 * 滚动条变化
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
public class ObserverHScrollView extends HorizontalScrollView {
  ScrollViewObserver mScrollViewObserver = new ScrollViewObserver();

  public ObserverHScrollView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public ObserverHScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ObserverHScrollView(Context context) {
    super(context);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    Log.i("ObserverHScrollView", "MyHScrollView onTouchEvent");
    return super.onTouchEvent(ev);
  }

  @Override
  protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    /*
     * 当滚动条移动后，引发 滚动事件。通知给观察者，观察者会传达给其他的。
     */
    if (mScrollViewObserver != null /* && (l != oldl || t != oldt) */) {
      mScrollViewObserver.NotifyOnScrollChanged(l, t, oldl, oldt);
    }
    super.onScrollChanged(l, t, oldl, oldt);
  }

  /*
   * 订阅 本控件 的 滚动条变化事件
   */
  public void AddOnScrollChangedListener(OnScrollChangedListener listener) {
    mScrollViewObserver.AddOnScrollChangedListener(listener);
  }

  /*
   * 取消 订阅 本控件 的 滚动条变化事件
   */
  public void RemoveOnScrollChangedListener(OnScrollChangedListener listener) {
    mScrollViewObserver.RemoveOnScrollChangedListener(listener);
  }

  /*
   * 当发生了滚动事件时
   */
  public static interface OnScrollChangedListener {
    public void onScrollChanged(int l, int t, int oldl, int oldt);
  }

  /*
   * 观察者
   */
  public static class ScrollViewObserver {
    List<OnScrollChangedListener> mList;

    public ScrollViewObserver() {
      super();
      mList = new ArrayList<OnScrollChangedListener>();
    }

    public void AddOnScrollChangedListener(OnScrollChangedListener listener) {
      mList.add(listener);
    }

    public void RemoveOnScrollChangedListener(OnScrollChangedListener listener) {
      mList.remove(listener);
    }

    public void NotifyOnScrollChanged(int l, int t, int oldl, int oldt) {
      if (mList == null || mList.size() == 0) {
        return;
      }
      for (int i = 0; i < mList.size(); i++) {
        if (mList.get(i) != null) {
          mList.get(i).onScrollChanged(l, t, oldl, oldt);
        }
      }
    }
  }
}
