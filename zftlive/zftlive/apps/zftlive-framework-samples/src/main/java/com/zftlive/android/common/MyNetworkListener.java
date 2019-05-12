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

package com.zftlive.android.common;

import com.zftlive.android.GlobalApplication;
import com.zftlive.android.library.Alert;
import com.zftlive.android.library.common.NetworkStateService;

/**
 * 网络监听Service
 * @author 曾繁添
 * @version 1.0
 *
 */
public class MyNetworkListener extends NetworkStateService {

	@Override
	public void onNoNetwork() {
		Alert.toastShort(GlobalApplication.gainContext(), "OMG 木有网络了~~");
	}

	@Override
	public void onNetworkChange(String networkType) {
		Alert.toastShort(GlobalApplication.gainContext(), "当前网络："+networkType);
	}

}
