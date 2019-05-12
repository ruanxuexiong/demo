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

package com.zftlive.android.sample.camera;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.zftlive.android.R;
import com.zftlive.android.library.Alert;
import com.zftlive.android.library.base.ui.CommonActivity;
import com.zftlive.android.library.tools.ToolPicture;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * 直接启动摄像机硬件拍照，不启动系统拍照程序样例
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
public class CameraPictureActivity extends CommonActivity {

	private SurfaceView surfaceView;
	private Button btnSave;
	private ImageView open_picIcon;
	private Camera camera;
	private SurfaceHolder surfaceHolder;
	private File picture;

	@Override
	public int bindLayout() {
		return R.layout.activity_camera_demo;
	}

	@Override
	public void initParams(Bundle parms) {
		
	}
	
	@SuppressLint("NewApi")
	@Override
	public void initView(View view) {
		surfaceView = (SurfaceView) findViewById(R.id.camera_preview);
		surfaceHolder = surfaceView.getHolder(); 
		surfaceHolder.addCallback(surfaceCallback);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		open_picIcon = (ImageView) findViewById(R.id.open_picIcon);
		btnSave = (Button) findViewById(R.id.save_pic);
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				surfaceView.setVisibility(View.VISIBLE);
				open_picIcon.setVisibility(View.GONE);
				takePic();
			}
		});
		
		//初始化带返回按钮的标题栏
		String strCenterTitle = getResources().getString(R.string.CameraPictureActivity);
//      ActionBarManager.initBackTitle(getContext(), getActionBar(), strCenterTitle);
		mWindowTitle.initBackTitleBar(strCenterTitle);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CAMERA
				|| keyCode == KeyEvent.KEYCODE_SEARCH) {
			takePic();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void takePic() {
		// TODO stop the preview if remove comment block will system error
		// camera.stopPreview();
		camera.takePicture(null, null, pictureCallback);
	}

	/**
	 * SurfaceHodler回调： 打开照相机、关闭照相机、设置照片尺寸等操作
	 */
	@SuppressLint("NewApi")
	SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {

		public void surfaceCreated(SurfaceHolder holder) {
			Log.i(TAG, "====surfaceCreated");

			try {
				camera = Camera.open();
				camera.setPreviewDisplay(holder);
			} catch (Exception e) {
				e.printStackTrace();
				if(null != camera){
					camera.release();
				}
				camera = null;
				Alert.dialog(getContext(), "错误提示", "启动相机失败",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						});
			}
		}

		@SuppressLint("NewApi")
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			Log.i(TAG, "====surfaceChanged");

			try {
				// 照相机参数
				Camera.Parameters parameters = camera.getParameters();
				parameters.setPictureFormat(PixelFormat.JPEG);
				parameters.set("rotation", 90);
				// TODO 三星PAD设置预览大小不正确会崩溃
				// parameters.setPreviewSize(480, 600);
				camera.setParameters(parameters);
				camera.setDisplayOrientation(90);
				camera.startPreview();
			}catch (Exception e){
				e.printStackTrace();
				Alert.dialog(getContext(), "错误提示", "启动相机失败",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
												int which) {
								finish();
							}
						});
			}

		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			Log.i(TAG, "====surfaceDestroyed");
			if(null == camera)return;

			camera.stopPreview();
			camera.release();
			camera = null;
		}
	};

	/**
	 * 图片回调函数
	 */
	Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			
			//等待提示
			getOperation().showLoading("正在处理，请稍后...");
			
			new SavePictureTask().execute(data);
			camera.startPreview();
		}
	};

	/**
	 * 保存图片异步任务
	 */
	protected class SavePictureTask extends AsyncTask<byte[], String, String> {

        /**
         * 运行在UI线程中，在调用doInBackground()之前执行
         */
        @Override
        protected void onPreExecute() {
        	getOperation().updateLoadingText("正在保存...");
        }
		
		@Override
		protected String doInBackground(byte[]... params) {
			
			String fname = DateFormat.format("yyyyMMddhhmmss", new Date()).toString() + ".jpg";
			picture = new File(Environment.getExternalStorageDirectory()+ "/images/" + fname);
			
			//判断文件夹是否存在
			File file = picture.getParentFile();
			if (!file.exists())
				file.mkdir();

			Log.i(TAG,"fname=" + fname + ";dir="+ Environment.getExternalStorageDirectory());
			
			try {
				FileOutputStream fos = new FileOutputStream(picture.getPath());
				fos.write(params[0]);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			//矫正图片旋转角度
			Bitmap bitmap = getDiskBitmap(picture.getPath());
			
			//预览图片
			surfaceView.setVisibility(View.GONE);
			open_picIcon.setVisibility(View.VISIBLE);
			open_picIcon.setImageBitmap(bitmap);
			
			getOperation().updateLoadingText("保存完成!");
			getOperation().closeLoading();
		}

	}

	/**
	 * 矫正旋转角度后的图像
	 * @param filePath 图片文件
	 * @return 矫正角度后的图像
	 */
	private Bitmap getDiskBitmap(String filePath) {
		Bitmap bitmap = null;
		try {
			File file = new File(filePath);
			if (file.exists())
				bitmap = BitmapFactory.decodeFile(filePath);

			// 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
			int degree = ToolPicture.gainPictureDegree(filePath);
			if (degree != 0)
				bitmap = ToolPicture.rotaingBitmap(degree, bitmap);

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		return bitmap;
	}
}
