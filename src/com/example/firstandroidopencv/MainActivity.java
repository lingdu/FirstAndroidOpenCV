package com.example.firstandroidopencv;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	Button btnProcess;
	Bitmap srcBitmap; //ԭͼ
	Bitmap grayBitmap; //�Ҷ�ͼ
	ImageView imgLena;
	private static boolean flag = true;
	private static boolean isFirst = true;
	private static final String TAG = "MainActivity";
	
	//openCV����ز���ʼ���ɹ���Ļص�����
	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {  
		  
        @Override  
        public void onManagerConnected(int status) {  
            // TODO Auto-generated method stub  
            switch (status){  
            case BaseLoaderCallback.SUCCESS:  
                Log.i(TAG, "�ɹ�����");  
                break;  
            default:  
                super.onManagerConnected(status);  
                Log.i(TAG, "����ʧ��");  
                break;  
            }  
              
        }  
    };  
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }
    
    public boolean onCreatOptionMenu(Menu menu){
    	// Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.main, menu);  
        return true;  
    }


	public void initUI() {
		// TODO Auto-generated method stub
		final Button btnProcess = (Button)findViewById(R.id.btn_gray_process);
		imgLena = (ImageView)findViewById(R.id.img_lenac);
		Log.i(TAG, "initUI sucess");
		btnProcess.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {  
	            // TODO Auto-generated method stub  
	            if(isFirst)  
	            {  
	                procSrc2Gry();  
	                isFirst = false;  
	            }  
	            if(flag){  
	                imgLena.setImageBitmap(grayBitmap);  
	                btnProcess.setText("�鿴ԭͼ");  
	                flag = false;  
	            }  
	            else{  
	                imgLena.setImageBitmap(srcBitmap);  
	                btnProcess.setText("�ҶȻ�");  
	                flag = true;  
	            }  
			} 
		});
	}
		
	public void procSrc2Gry(){
		Mat rgbMat = new Mat();
		Mat grayMat = new Mat();
		srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lenac);
		grayBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
		Utils.bitmapToMat(srcBitmap, rgbMat);//convert original bitmap to Mat, R G B.  
        Imgproc.cvtColor(rgbMat, grayMat, Imgproc.COLOR_RGB2GRAY);//rgbMat to gray grayMat  
        Utils.matToBitmap(grayMat, grayBitmap); //convert mat to bitmap  
        Log.i(TAG, "procSrc2Gray sucess...");  
	} 
  
    @Override  
    protected void onResume() {  
        // TODO Auto-generated method stub  
        super.onResume();  
        //load OpenCV engine and init OpenCV library  
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11, getApplicationContext(), mLoaderCallback);  
        Log.i(TAG, "onResume sucess load OpenCV..."); 
        
        //��һ���ӳ٣�Ĭ�ϵȼ��������ʼ�����ٽ���ͼ����Ĳ���
//      new Handler().postDelayed(new Runnable(){  
//  
//          @Override  
//          public void run() {  
//              // TODO Auto-generated method stub  
//              procSrc2Gray();  
//          }  
//            
//      }, 1000);  
          
    }  



}
