package white_team.purkynova.com.meetingschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LogoActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        start();
    }
    private void start(){
        this.intent = new Intent(this, OverviewActivity.class);
        intent.putExtra("date", "2017-11-06");
        startActivity(intent);
    }
}
