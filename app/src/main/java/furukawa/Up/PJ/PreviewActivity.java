package furukawa.Up.PJ;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreviewActivity extends AppCompatActivity {
    private Button start_taking_pictrue_button;
    private Button go_back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        initView();
        start_taking_pictrue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        go_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void initView(){
        go_back_button=findViewById(R.id.go_back);
        start_taking_pictrue_button=findViewById(R.id.start_taking_picture);
    }
}