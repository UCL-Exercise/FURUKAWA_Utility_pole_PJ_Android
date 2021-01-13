package furukawa.Up.PJ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewReportActivity extends AppCompatActivity {
    Button go_back_botton;
    Button take_media_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);
        initView();
        take_media_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        go_back_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_back_to_StartupActivity=new Intent(NewReportActivity.this,StartupActivity.class);
                startActivity(go_back_to_StartupActivity);
                finish();
            }
        });
    }

    public void initView() {
        go_back_botton=findViewById(R.id.go_back);
        take_media_button=findViewById(R.id.take_photo);
    }
}