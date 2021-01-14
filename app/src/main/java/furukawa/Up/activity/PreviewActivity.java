package furukawa.Up.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import controller.CameraController;
import view.AutoFitTextureView;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start_taking_pictrue_button;
    private Button go_back_button;
    private AutoFitTextureView mTextureview;
    private Button mTakePictureBtn;//拍照
    private Button mVideoRecodeBtn;//开始录像
    private LinearLayout mVerticalLinear;
    private Button mTakePictureBtn2;//拍照 横,竖屏状态分别设置了一个拍照,录像的按钮
    private Button mVideoRecodeBtn2;//开始录像
    private LinearLayout mHorizontalLinear;
    private Button mVHScreenBtn;
    private CameraController mCameraController;
    private boolean mIsRecordingVideo; //开始停止录像
    public static String BASE_PATH = Environment.getExternalStorageDirectory() + "/AAA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏

        setContentView(R.layout.activity_preview);

        initView();
        start_taking_pictrue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        go_back_button.setOnClickListener(go_back_OnClickListener);
    }

    public void initView() {
        mTextureview = (AutoFitTextureView) findViewById(R.id.surface_view_camera2);
        go_back_button = findViewById(R.id.go_back);
        start_taking_pictrue_button = findViewById(R.id.start_taking_picture);
        mCameraController = CameraController.getmInstance(this);
        mCameraController.setFolderPath(BASE_PATH);
        mCameraController.InitCamera(mTextureview);
    }

    View.OnClickListener go_back_OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent goback = new Intent(PreviewActivity.this, NewReportActivity.class);
            startActivity(goback);
            finish();
        }
    };

    @Override
    public void onClick(View view) {

    }


}