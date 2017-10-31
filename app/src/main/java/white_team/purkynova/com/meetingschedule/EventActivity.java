package white_team.purkynova.com.meetingschedule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import white_team.purkynova.com.meetingschedule.Event.Event;
import white_team.purkynova.com.meetingschedule.Model.EventModel;


public class EventActivity extends AppCompatActivity {
    private EventModel eventModel;
    TextView pleace ;
    TextView time;
    TextView text;
    TextView lecture;
    TextView isLecture;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        pleace = (TextView) findViewById(R.id.textView2);
        time = (TextView) findViewById(R.id.textView3);
        text = (TextView) findViewById(R.id.textView);
        lecture= (TextView) findViewById(R.id.textView4);
        isLecture = (TextView) findViewById(R.id.textView5);
        eventModel = new EventModel(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Event event = this.eventModel.get(Value);
                if (event != null) {
                    pleace.setText(event.getName());
                    time.setText(event.getTimeSpan());
                    text.setText(event.getDescription());
                    if(event.isLecture()){
                        isLecture.setText("Lecture: YES");
                        lecture.setText(event.getLecturer());
                    }else {
                        isLecture.setText("");
                        lecture.setText("");
                    }

                }


                String nam = "U69";
                String phon = "16.00-17.00";
                String emai = "sogjsrohzjgerihrtsighrtuihrtuhgrůuhgjsrůgjrosůjgorjtsghůrtsjhghůsrtjhůsrtdjh";
                pleace.setText((CharSequence) phon);
                   /* pleace.setFocusable(false);
                    pleace.setClickable(false);*/

                time.setText((CharSequence) emai);
                   /* time.setFocusable(false);
                    time.setClickable(false);*/

                text.setText((CharSequence) nam);
                   /* text.setFocusable(false);
                    text.setClickable(false);*/


            }
        }


    }
    public  void materials(View view){
        Uri urim = Uri.parse("https://github.com/Honny1/Materials.git"); //open materials on github add alter
        Intent intent = new Intent(Intent.ACTION_VIEW, urim);
        startActivity(intent);
    }
    public  void feedback(View view){
        Uri urif = Uri.parse("http://www.sspbrno.cz/"); // open feedback found url for feedbacka
        Intent intent = new Intent(Intent.ACTION_VIEW, urif);
        startActivity(intent);
    }
}
