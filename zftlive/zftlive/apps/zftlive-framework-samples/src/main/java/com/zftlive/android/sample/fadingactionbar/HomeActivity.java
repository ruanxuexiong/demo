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
package com.zftlive.android.sample.fadingactionbar;

import java.util.Arrays;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.zftlive.android.R;

public class HomeActivity extends ListActivity {
    private List<ActivityInfo> activitiesInfo = Arrays.asList(
            new ActivityInfo(ScrollViewActivity.class, R.string.activity_title_scrollview),
            new ActivityInfo(ListViewActivity.class, R.string.activity_title_listview),
            new ActivityInfo(LightBackgroundActivity.class, R.string.activity_title_light_bg),
            new ActivityInfo(LightActionBarActivity.class, R.string.activity_title_light_ab),
            new ActivityInfo(SampleFragmentActivity.class, R.string.activity_title_fragment),
            new ActivityInfo(NoParallaxActivity.class, R.string.activity_title_no_parallax),
            new ActivityInfo(NavigationDrawerActivity.class, R.string.activity_title_navigation),
            new ActivityInfo(HeaderOverlayActivity.class, R.string.activity_title_header_overlay),
            new ActivityInfo(WebViewActivity.class, R.string.activity_title_webview),
            new ActivityInfo(ShortContentActivity.class, R.string.activity_title_short_content));

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String[] titles = getActivityTitles();
        setListAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, android.R.id.text1, titles));
        //初始化带返回按钮的标题栏
//  		ActionBarManager.initBackTitle(this, getActionBar(), this.getClass().getSimpleName());  
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Class<? extends Activity> class_ = activitiesInfo.get(position).activityClass;
        Intent intent = new Intent(this, class_);
        startActivity(intent);
    }

    private String[] getActivityTitles() {
        String[] result = new String[activitiesInfo.size()];
        int i = 0;
        for (ActivityInfo info : activitiesInfo) {
            result[i++] = getString(info.titleResourceId);
        }
        return result;
    }
}
