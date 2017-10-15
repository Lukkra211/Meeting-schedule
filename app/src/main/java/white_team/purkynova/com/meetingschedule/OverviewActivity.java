package white_team.purkynova.com.meetingschedule;

/**
 * Hlavní aktivita aplikace. Při spuštění aplikace naběhne tato aktivita.
 *
 * Created by Lukáš Krajíček on 15.10.17.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }
}
