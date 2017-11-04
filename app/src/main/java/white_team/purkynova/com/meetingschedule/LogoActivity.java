package white_team.purkynova.com.meetingschedule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import white_team.purkynova.com.meetingschedule.Model.EventModel;


/**
 * Besides showing the logo of the application, this activity have 2 purposes. First is to set right
 * date of OverviewActivity (see initParams) and second is to initialize the database if was not
 * already.
 *
 * Just by creating a instance of {@link white_team.purkynova.com.meetingschedule.Model.EventModel}
 * the database is checked and the migration might begin. That means that DELAY should be big
 * enough to init the database in time.
 *
 * @author Lukáš Krajíček
 */
public class LogoActivity extends AppCompatActivity {

    /** Delay in milliseconds */
    private int DELAY = 3000;
    /** Date of the day when meeting start */
    private final String FIRST_DAY = "2017-11-06";
    /** Date of the day when meeting end */
    private final String LAST_DAY = "2017-11-10";

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private Intent intent;

    /** Animation that slowly change alpha value */
    private Animation fadeIn = new AlphaAnimation(0, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        setAnimations();
        initParams();

        new Handler().postDelayed(TimeHandler, DELAY);

        // Create instance of EventModel because database is checked when creating DatabaseHelper
        // inside the EventModel class
        new EventModel(this);
    }

    /**
     * Initialize animations and link them to the xml objects
     */
    private void setAnimations() {
        this.fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(DELAY / 2);

        findViewById(R.id.logo_activity_textView_appName).setAnimation(fadeIn);
        findViewById(R.id.logo_activity_textView_smalltext).setAnimation(fadeIn);
    }

    /**
     * Set OverviewActivity's date to current date if fits between FIRST_DAY and LAST_DAY, otherwise
     * set to FIRST_DATE
     */
    private void initParams() {
        this.intent = new Intent(this, OverviewActivity.class);

        intent.putExtra("date", FIRST_DAY);
        try {
            Date current = Calendar.getInstance().getTime();
            Date first = format.parse(FIRST_DAY);
            Date last = format.parse(LAST_DAY);

            if (first.getTime() <= current.getTime() && current.getTime() <= last.getTime()) {
                intent.putExtra("date", format.format(current));
            }
        } catch (ParseException ignored) {}
    }

    /**
     * Object implementing Runnable - it contains run method that can be executed by Handler object
     */
    private Runnable TimeHandler = new Runnable() {

        @Override
        public void run() {
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    };
}
