import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.String;
import java.util.Date;

//HOTOVO AZ NEBUDOU KOMENTARE
public class Event {
//Vyzkouset
	private Date since;
	private Date till;
	private String description;
	private String lecturer;
	private int eventType;
    //HOTOVO
    private void setType(String eventString){
        String[] types = {"lecture", "food", "free_time"};
        for (int i=0; i< 2; i++) {
            if (eventString == types[i]) {
                this.eventType = i;
            }
        }
    }
    String getInfoText(){

        return this.description;
    }

    Boolean isLecture(){
        if(this.lecturer != null & this.eventType == 0) {
            return true;
        } else {
            return  false;
        }
    }

    String getType(){
        String[] types = {"lecture", "food", "free_time"};
        return types[this.eventType];
    }

    String getLecturer(){
        if (isLecture()) {
            return this.lecturer;
        }
        else{
            return null;
        }
    }

	//DOKONCIT
    //zjistit jak se ma vratit cas
	String getTimeSpan() {
        DateFormat df = new SimpleDateFormat("hh:mm");
		return df.format((this.since)) + " - " + df.format((this.till));
    }

	private void setTime(String start, String end){
        //2017-10-17 T16:28Z
	     try {
	            //inputDate = "2010-01-04 01:32:27 UTC";
	            this.till = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(end);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	}
	
	Event(String since, String till, String eventType, String description, String lecturer){
		this.description = description;
        this.lecturer = lecturer;
        setType(eventType);
        setTime(till, since);
	}
	
	
}

