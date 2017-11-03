package white_team.purkynova.com.meetingschedule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import white_team.purkynova.com.meetingschedule.Event.Event;
import white_team.purkynova.com.meetingschedule.Model.EventModel;


/**
 * @author Honza Rodák
 * #HonyIsGod
 */
public class EventActivity extends AppCompatActivity {

    private final String TAG = "EventActivity";

    private EventModel eventModel;
    private Event event;

    private TextView textViewNameAndType;
    private TextView textViewTime;
    private TextView textViewPlace;
    private TextView textViewGuarantor;
    private TextView textViewDescription;

    private String url/**="www.google.com"*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        this.initParams();

        checkType();
        setButtonMaterials();

        if (!this.isFinishing()) {
            this.initUI();
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

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e(TAG, "Activity didn't get id of the event.");
        } else {
            try {
                int id =extras.getInt("id");
                this.event = this.eventModel.get(id);

                if (this.event == null) {
                    Log.e(TAG, String.format("Event doesn't exists (id = %d).", id));
                } else {
                    return; // only option how not to call finish is to return Event object
                }
            } catch (NullPointerException e) {
                Log.e(TAG, "No id was given to the activity.");
            }
        }
        this.finish();
    }

    /**
     * Initialize user interface
     */
    private void initUI() {
        if(this.event.getType()=="food"){
            this.textViewNameAndType.setText(getString(R.string.name_and_type,
                    this.event.getName(),
                    this.event.getType()));
            this.textViewTime.setText(getString(R.string.time_span, this.event.getTimeSpan()));
            this.textViewPlace.setText(getString(R.string.place, this.event.getPlace()));

            if(this.event.getGuarantor()==null){
                textViewGuarantor.setVisibility(View.INVISIBLE);
            }else{
                this.textViewGuarantor.setText(getString(R.string.guarantor, this.event.getGuarantor()));
            }

            String food1 = getString(R.string.description, this.event.getDescription());
            String[] parts = food1.split(";");
            String[] foods=new String[parts.length];
            for (int i = 0; i < parts.length; i++) {
                String foodNoNumber = parts[i];
                String foodYesNumber = "\n"+String.valueOf(i+1)+")"+foodNoNumber;
                foods[i]=foodYesNumber;
            }
            String str = Arrays.toString(foods);
            String cleanfood=str.replaceAll("^\\[|\\]$", "");

            this.textViewDescription.setText(cleanfood);

        }else{
        this.textViewNameAndType.setText(getString(R.string.name_and_type,
                                                   this.event.getName(),
                                                   this.event.getType()));
        this.textViewTime.setText(getString(R.string.time_span, this.event.getTimeSpan()));
        this.textViewPlace.setText(getString(R.string.place, this.event.getPlace()));
        if(this.event.getGuarantor()==null){
                textViewGuarantor.setVisibility(View.INVISIBLE);
        }else{
            this.textViewGuarantor.setText(getString(R.string.guarantor, this.event.getGuarantor()));
        }
        this.textViewDescription.setText(getString(R.string.description, this.event.getDescription()));
        }
    }

    public void materials(View view) {
        Uri urim = Uri.parse(url); //open materials on github add alter
        Intent intent = new Intent(Intent.ACTION_VIEW, urim);
        startActivity(intent);
    }
    public void feedback(View view) {
        Uri urif = Uri.parse("http://www.sspbrno.cz/"); // open feedback found url for feedbacka
        Intent intent = new Intent(Intent.ACTION_VIEW, urif);
        startActivity(intent);
    }
    public  void checkType(){
        if(this.event.getType()!="lecture"){
        /*Button b = (Button)findViewById(R.id.eventButtonMaterials);
        b.setVisibility(View.INVISIBLE);*/
        Button b1 = (Button)findViewById(R.id.eventButtonFeedback);
        b1.setVisibility(View.INVISIBLE);
        }
    }
    public void setButtonMaterials(){
       if(this.event.getMaterialLink==null){
            Button b = (Button)findViewById(R.id.eventButtonMaterials);
            b.setVisibility(View.INVISIBLE);
        }else{
            url=this.event.getMaterialLink;
        }
    }
}
