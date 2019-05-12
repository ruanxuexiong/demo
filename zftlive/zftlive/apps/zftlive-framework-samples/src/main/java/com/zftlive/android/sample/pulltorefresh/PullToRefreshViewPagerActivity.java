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
package com.zftlive.android.sample.pulltorefresh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.zftlive.android.R;
import com.zftlive.android.library.widget.pulltorefresh.PullToRefreshBase;
import com.zftlive.android.library.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.zftlive.android.library.widget.pulltorefresh.extras.viewpager.PullToRefreshViewPager;

public class PullToRefreshViewPagerActivity extends Activity implements OnRefreshListener<ViewPager> {

	private PullToRefreshViewPager mPullToRefreshViewPager;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_viewpager);

		mPullToRefreshViewPager = (PullToRefreshViewPager) findViewById(R.id.pull_refresh_viewpager);
		mPullToRefreshViewPager.setOnRefreshListener(this);

		ViewPager vp = mPullToRefreshViewPager.getRefreshableView();
		vp.setAdapter(new SamplePagerAdapter());
        //初始化带返回按钮的标题栏
//  		ActionBarManager.initBackTitle(this, getActionBar(), this.getClass().getSimpleName()); 
	}

	@Override
	public void onRefresh(PullToRefreshBase<ViewPager> refreshView) {
		new GetDataTask().execute();
	}

	static class SamplePagerAdapter extends PagerAdapter {

		private static int[] sDrawables = { R.drawable.anl_common_default_picture, R.drawable.anl_common_default_picture, R.drawable.anl_common_default_picture,
				R.drawable.anl_common_default_picture, R.drawable.anl_common_default_picture, R.drawable.anl_common_default_picture};

		@Override
		public int getCount() {
			return sDrawables.length;
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(container.getContext());
			imageView.setImageResource(sDrawables[position]);

			// Now just add ImageView to ViewPager and return it
			container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}

	private class GetDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mPullToRefreshViewPager.onRefreshComplete();
			super.onPostExecute(result);
		}
	}

}
