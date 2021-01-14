package furukawa.Up.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewReportActivity extends AppCompatActivity {
    private Button go_back_botton;
    private Button take_media_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);
        initView();
        take_media_button.setOnClickListener(take_media_OnClickListener);
        go_back_botton.setOnClickListener(go_back_OnClickListener);
    }

    public void initView() {
        go_back_botton=findViewById(R.id.go_back);
        take_media_button=findViewById(R.id.take_photo);
    }
    View.OnClickListener go_back_OnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent go_back_to_StartupActivity=new Intent(NewReportActivity.this,StartupActivity.class);
            startActivity(go_back_to_StartupActivity);
            finish();
        }
    };
    View.OnClickListener take_media_OnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent take_media_Activity=new Intent(NewReportActivity.this,PreviewActivity.class);
            startActivity(take_media_Activity);
            finish();
        }
    };
}