package furukawa.Up.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartupActivity extends AppCompatActivity {
    private Button new_report_btn;
    private Button report_list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        initView();
        new_report_btn.setOnClickListener(new_report_OnClickListener);
        report_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void initView(){
        new_report_btn=findViewById(R.id.new_report_btn);
        report_list_btn=findViewById(R.id.report_list_btn);
    }
    View.OnClickListener new_report_OnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent go_to_NewReportActivity=new Intent(StartupActivity.this,NewReportActivity.class);
            startActivity(go_to_NewReportActivity);
            finish();
        }
    };
}