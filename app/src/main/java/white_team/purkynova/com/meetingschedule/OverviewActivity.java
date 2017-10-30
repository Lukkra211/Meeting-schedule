package white_team.purkynova.com.meetingschedule;

/**
 * Hlavní aktivita aplikace. Při spuštění aplikace naběhne tato aktivita.
 *
 * Created by Zdeňka Škrdlová on 15.10.17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class OverviewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }

    protected void initParams() {
        intent = new Intent(this, Activity2.class);
        spinner = (Spinner) findViewById(R.id.days);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinnerTyp, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 1:
                intent.putExtra();
                break;

            case 2:
                intent.putExtra();
                break;

            case 3:
                intent.putExtra();
                break;

            case 4:
                intent.putExtra();
                break;

            case 5:
                intent.putExtra();
                break;

            case 6:
                intent.putExtra();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
