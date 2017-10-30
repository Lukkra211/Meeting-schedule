package white_team.purkynova.com.meetingschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * @author Zdeňka Škrdlová
 */
public class OverviewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        initParams();
    }

    private void initParams() {
        intent = new Intent(this, EventActivity.class);
        // TODO: doplnit id po vytvoreni XML
        //spinner = (Spinner) findViewById(0);

        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinnerType, android.R.layout.simple_spinner_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 1:
                intent.putExtra("date", "2017-11-06");
                break;

            case 2:
                intent.putExtra("date", "2017-11-07");
                break;

            case 3:
                intent.putExtra("date", "2017-11-08");
                break;

            case 4:
                intent.putExtra("date", "2017-11-09");
                break;

            case 5:
                intent.putExtra("date", "2017-11-10");
                break;

            case 6:
                intent.putExtra("date", "2017-11-11");
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
