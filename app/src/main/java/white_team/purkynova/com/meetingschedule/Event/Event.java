package white_team.purkynova.com.meetingschedule.Event;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.String;
import java.util.Date;

public class Event {
    // Save values to variables
    private Date since;
    private Date till;
    private String place;
    private String description;
    private String lecturer;
    private int eventType;

    //Constructor for class (creates object)
    Event(String since, String till, String eventType, String description,String place , String lecturer){
            this.description = description;
            this.lecturer = lecturer;
            this.place = place;
            setType(eventType);
            setTime(till, since);
    }


    //Set type of event as intiger
    private void setType(String eventString) {
        String[] types = {"lecture", "food", "free_time"};
        for (int i=0; i< 2; i++) {
            if (eventString == types[i]) {
                this.eventType = i;
            }
        }
    }
    //Change type of tyme from yyy-mm-dd T hh:mm Z to yyyy-MM-dd HH:mm:ss
    private void setTime(String start, String end) {
        try {
            this.till = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    //Return true if event type is lecture
    Boolean isLecture() {
        if(this.lecturer != null & this.eventType == 0) {
            return true;
        } else {
            return  false;
        }
    }
    String getTimeSpan() {
        DateFormat df = new SimpleDateFormat("hh:mm");
        return df.format((this.since)) + " - " + df.format((this.till));
    }

    //Return Event type as String
    String getType() {
        String[] types = {"lecture", "food", "free_time"};
        return types[this.eventType];
    }
    //If event type is lecturer, then return it´s name
    String getLecturer() {
        if (isLecture()) {
            return this.lecturer;
        }
        else {
            return null;
        }
    }
    //Return place of event
    String getPlace(){
        return this.place;
    }

    //Return description of Event
    String getInfoText(){
        return this.description;
    }
}
