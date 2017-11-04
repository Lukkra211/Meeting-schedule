package white_team.purkynova.com.meetingschedule;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
 * @author Honza Rodák
 */
public class OverviewActivity extends ListActivity {

    private final String TAG = "OverviewActivity";

    private String date;

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

        try {
            initParams();
            initUI();
        } catch (IllegalStateException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, String.format("%s", e.getMessage()));
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initParams() {
        this.eventModel = new EventModel(this);
        this.eventIntent = new Intent(this, EventActivity.class);
        this.selfIntent = new Intent(this, this.getClass());

        this.listView = getListView();
        this.spinner = (Spinner) findViewById(R.id.day_spinner);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new IllegalStateException("No parameter given to the activity.");
        }

        this.date = extras.getString("date");
        if (this.date == null) {
            throw new IllegalStateException("No parameter named 'date' given.");
        }

        eventList = eventModel.getEventsByDate(this.date);
        if (this.eventList.size() == 0) {
            throw new IllegalStateException(String.format("No events found for the given day. %s given", this.date));
        }
    }

    private void initUI() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinnerType, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerListener());

        listView.setOnItemClickListener(new ListViewListener());
        EventAdapter eventAdapter = new EventAdapter(
                this,
                R.layout.overview_activity_list_row,
                this.eventList
        );
        setListAdapter(eventAdapter);
    }

    /**
     * Implementation of OnItemClickListener for ListView
     */
    private class ListViewListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)  {
            eventIntent.putExtra("id", eventList.get(position).getId());
            startActivity(eventIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.empty);
        }
    };

    /**
     * Implementation of OnItemSelectedListener for Spinner
     */
    private class SpinnerListener implements AdapterView.OnItemSelectedListener {

        private boolean initialized = false;

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (!initialized) {
                initialized = true;
                spinner.setSelection(this.dateToPosition(date));
                return;
            }

            if (position == this.dateToPosition(date)) {
                return;
            }

            switch (position) {
                case 0:
                    selfIntent.putExtra("date", "2017-11-06");
                    break;

                case 1:
                    selfIntent.putExtra("date", "2017-11-07");
                    break;

                case 2:
                    selfIntent.putExtra("date", "2017-11-08");
                    break;

                case 3:
                    selfIntent.putExtra("date", "2017-11-09");
                    break;

                case 4:
                    selfIntent.putExtra("date", "2017-11-10");
                    break;
            }

            startActivity(selfIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

        private int dateToPosition(String date) {
            switch (date) {
                case "2017-11-06":
                    return 0;

                case "2017-11-07":
                    return 1;

                case "2017-11-08":
                    return 2;

                case "2017-11-09":
                    return 3;

                case "2017-11-10":
                    return 4;

                default:
                    return 0;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }
}
