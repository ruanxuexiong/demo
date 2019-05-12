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

package com.zftlive.android.sample.animation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zftlive.android.R;
import com.zftlive.android.library.base.ui.CommonActivity;
import com.zftlive.android.library.widget.dialog.NiftyDialogBuilder;
import com.zftlive.android.library.widget.dialog.NiftyDialogBuilder.Effectstype;

/**
 * dialog各种特效DEMO
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
public class DialogEffectActivity extends CommonActivity {

	private Effectstype effect;

	@Override
	public int bindLayout() {
		return R.layout.activity_dialog_effects;
	}

	@Override
	public void initParams(Bundle parms) {

	}

	@Override
	public void initView(View view) {

	}

	@Override
	public void doBusiness(Context mContext) {

		// 初始化带返回按钮的标题栏
		String strCenterTitle = getResources().getString(R.string.DialogEffectActivity);
//      ActionBarManager.initBackTitle(getContext(), getActionBar(), strCenterTitle);
		mWindowTitle.initBackTitleBar(strCenterTitle);
	}

	public void dialogShow(View v) {
		NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);

		switch (v.getId()) {
		case R.id.fadein:
			effect = Effectstype.Fadein;
			break;
		case R.id.slideright:
			effect = Effectstype.Slideright;
			break;
		case R.id.slideleft:
			effect = Effectstype.Slideleft;
			break;
		case R.id.slidetop:
			effect = Effectstype.Slidetop;
			break;
		case R.id.slideBottom:
			effect = Effectstype.SlideBottom;
			break;
		case R.id.newspager:
			effect = Effectstype.Newspager;
			break;
		case R.id.fall:
			effect = Effectstype.Fall;
			break;
		case R.id.sidefall:
			effect = Effectstype.Sidefill;
			break;
		case R.id.fliph:
			effect = Effectstype.Fliph;
			break;
		case R.id.flipv:
			effect = Effectstype.Flipv;
			break;
		case R.id.rotatebottom:
			effect = Effectstype.RotateBottom;
			break;
		case R.id.rotateleft:
			effect = Effectstype.RotateLeft;
			break;
		case R.id.slit:
			effect = Effectstype.Slit;
			break;
		case R.id.shake:
			effect = Effectstype.Shake;
			break;
		}

		dialogBuilder
				.withTitle("Modal Dialog")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage("This is a modal Dialog.")
				// .withMessage(null) no Msg
				.withMessageColor("#FFFFFFFF")
				// def | withMessageColor(int resid)
				.withDialogColor("#FFE74C3C")
				// def | withDialogColor(int resid) //def
				.withIcon(
						getResources().getDrawable(
								R.drawable.ic_favorite_white_48dp))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(700) // def
				.withEffect(effect) // def Effectstype.Slidetop
				.withButton1Text("OK") // def gone
				.withButton2Text("Cancel") // def gone
				.setCustomView(R.layout.activity_dialog_effects_custom_view,
						v.getContext()) // .setCustomView(View or ResId,context)
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(v.getContext(), "i'm btn1",
								Toast.LENGTH_SHORT).show();
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(v.getContext(), "i'm btn2",
								Toast.LENGTH_SHORT).show();
					}
				}).show();

	}
}
