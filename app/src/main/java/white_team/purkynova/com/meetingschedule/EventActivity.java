package white_team.purkynova.com.meetingschedule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import white_team.purkynova.com.meetingschedule.Event.Event;
import white_team.purkynova.com.meetingschedule.Model.EventModel;
import white_team.purkynova.com.meetingschedule.Model.EventTypeHelper;


/**
 * Activity showing events details
 *
 * @author Honza Rodák
 * @author Lukáš Krajíček
 */
public class EventActivity extends AppCompatActivity {

    private final String TAG = "EventActivity";
    private final String FEEDBACK_HTTP = "http://projects.sspbrno.cz/feedback/%d";

    private EventModel eventModel;
    private Event event;

    private TextView textViewNameAndType;
    private TextView textViewTime;
    private TextView textViewPlace;
    private TextView textViewGuarantor;
    private TextView textViewDescription;
    private Button buttonFeedback;
    private ImageView typeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        try {
            this.initParams();
            this.initUI();
        } catch (IllegalStateException e) {
            finish();
        }
    }

    /**
     * Initialize parameters, finish activity with error log if necessary data weren't present
     *
     * finish method doesn't end activity immediately. It always is called after onCreate method
     */
    private void initParams() {
        this.eventModel = new EventModel(this);
        this.textViewNameAndType = (TextView) findViewById(R.id.eventNameAndType);
        this.textViewTime = (TextView) findViewById(R.id.eventTimeSpan);
        this.textViewPlace = (TextView) findViewById(R.id.eventPlace);
        this.textViewGuarantor = (TextView) findViewById(R.id.eventGuarantor);
        this.textViewDescription = (TextView) findViewById(R.id.eventDescription);
        this.buttonFeedback = (Button) findViewById(R.id.eventButtonFeedback);
        this.typeImage = (ImageView) findViewById(R.id.imageView);

        this.buttonFeedback.setOnClickListener(new FeedbackListener());

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new IllegalStateException("No parameter given to the activity.");
        }

        int id = extras.getInt("id");
        event = eventModel.get(id);
        if (event == null) {
            throw new IllegalStateException(String.format("No event with id %d exists..", id));
        }
    }

    /**
     * Initialize user interface
     */
    private void initUI() {
        this.textViewNameAndType.setText(getString(R.string.name_and_type,
                                                   this.event.getName(),
                                                   this.event.getType()));
        this.textViewTime.setText(getString(R.string.time_span, this.event.getTimeSpan()));
        this.textViewPlace.setText(getString(R.string.place, this.event.getPlace()));

        int type = EventTypeHelper.stringToInt(event.getType());
        typeImage.setImageResource(getResources().getIdentifier(
                EventTypeHelper.getDrawableNameByType(event.getType()),
                "drawable",
                getPackageName()
        ));

        if (this.event.getGuarantor() == null) {
            textViewGuarantor.setVisibility(View.GONE);
        } else {
            this.textViewGuarantor.setText(getString(R.string.guarantor, this.event.getGuarantor()));
        }

        if (this.event.getType().equals(EventTypeHelper.TYPE_FOOD)) {
            String[] foods = this.event.getDescription().split("; ");
            for (int i = 0; i < foods.length; i++) {
                foods[i] = String.format("%d) %s", i + 1, foods[i]);
            }

            String parsedFood = TextUtils.join("\n", foods);
            this.textViewDescription.setText(getString(R.string.description, parsedFood));
        } else {
            this.textViewDescription.setText(getString(R.string.description, this.event.getDescription()));
        }

        if (!event.isLecture()) {
            buttonFeedback.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.empty, android.R.anim.slide_out_right);
    }


    class FeedbackListener implements View.OnClickListener {

        private int extractTopicIdFromName(String title) {
            return Character.getNumericValue(title.charAt(6));
        }

        @Override
        public void onClick(View v) {
            String uri = String.format(FEEDBACK_HTTP, extractTopicIdFromName(event.getName()));

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }
    }
}
