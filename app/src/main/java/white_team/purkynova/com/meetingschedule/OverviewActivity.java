package white_team.purkynova.com.meetingschedule;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import white_team.purkynova.com.meetingschedule.Event.Event;
import white_team.purkynova.com.meetingschedule.Event.EventAdapter;
import white_team.purkynova.com.meetingschedule.Model.EventModel;

/**
 * @author Zdeňka Škrdlová
 * @author Lukáš Krajíček
 */
public class OverviewActivity extends ListActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private final String TAG = "OverviewActivity";

    private Intent eventIntent;
    private Intent selfIntent;

    private Spinner spinner;
    private ListView listView;

    private EventModel eventModel;
    private ArrayList<Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        initParams();
        if (!isFinishing()) {
            initUI();
        }
    }

    private void initParams() {
        this.eventModel = new EventModel(this);

        this.eventIntent = new Intent(this, EventActivity.class);
        this.selfIntent = new Intent(this, this.getClass());

        this.listView = getListView();
        this.listView.setOnItemClickListener(new ListViewOnClickListener());

        //spinner = (Spinner) findViewById(0);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinnerType, android.R.layout.simple_spinner_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e(TAG, "Activity didn't get id of the event.");
            this.finish();
        } else {
            eventList = eventModel.getEventsByDate(extras.getString("date"));

            if (this.eventList.size() == 0) {
                Toast.makeText(this, "No events found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initUI() {
        EventAdapter eventAdapter = new EventAdapter(
                this,
                R.layout.overview_activity_list_row,
                this.eventList
        );
        setListAdapter(eventAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                this.selfIntent.putExtra("date", "2017-11-06");
                break;

            case 2:
                this.selfIntent.putExtra("date", "2017-11-07");
                break;

            case 3:
                this.selfIntent.putExtra("date", "2017-11-08");
                break;

            case 4:
                this.selfIntent.putExtra("date", "2017-11-09");
                break;

            case 5:
                this.selfIntent.putExtra("date", "2017-11-10");
                break;

            case 6:
                this.selfIntent.putExtra("date", "2017-11-11");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    /**
     * Implementation of OnItemClickListener for ListView
     */
    private class ListViewOnClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)  {
            Log.w(TAG, String.valueOf(position));
            Log.w(TAG, String.valueOf(eventList));
            eventIntent.putExtra("id", eventList.get(position).getId());
            startActivity(eventIntent);
        }
    }
}
