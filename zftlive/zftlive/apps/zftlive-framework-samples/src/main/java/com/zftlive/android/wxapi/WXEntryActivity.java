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

package com.zftlive.android.wxapi;

//import cn.sharesdk.wechat.utils.WXAppExtendObject;
//import cn.sharesdk.wechat.utils.WXMediaMessage;
//import cn.sharesdk.wechat.utils.WechatHandlerActivity;

/**
 * TODO 微信客户端回调activity示例
 */
//public class WXEntryActivity extends WechatHandlerActivity {
public class WXEntryActivity {
//	/**
//	 * 处理微信发出的向第三方应用请求app message
//	 * <p>
//	 * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中
//	 * 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
//	 * 做点其他的事情，包括根本不打开任何页面
//	 */
//	public void onGetMessageFromWXReq(WXMediaMessage msg) {
//		Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(getPackageName());
//		startActivity(iLaunchMyself);
//	}
//
//	/**
//	 * 处理微信向第三方应用发起的消息
//	 * <p>
//	 * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
//	 * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信
//	 * 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作
//	 * 回调。
//	 * <p>
//	 * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
//	 */
//	public void onShowMessageFromWXReq(WXMediaMessage msg) {
//		if (msg != null && msg.mediaObject != null
//				&& (msg.mediaObject instanceof WXAppExtendObject)) {
//			WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
//			Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
//		}
//	}

}
