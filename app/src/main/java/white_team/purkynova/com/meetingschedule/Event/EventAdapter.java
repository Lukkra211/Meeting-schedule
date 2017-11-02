package white_team.purkynova.com.meetingschedule.Event;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import white_team.purkynova.com.meetingschedule.Model.EventTypeHelper;
import white_team.purkynova.com.meetingschedule.OverviewActivity;
import white_team.purkynova.com.meetingschedule.R;


/**
 *
 */
public class EventAdapter extends ArrayAdapter {
    private ArrayList<Event> eventList;

    public EventAdapter(@NonNull Context context, int resource, ArrayList<Event> objects) {
        super(context, resource, objects);
        this.eventList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.overview_activity_list_row, null);
        }

        Event event = this.eventList.get(position);

        if (event != null) {
            TextView timeTextView = (TextView) v.findViewById(R.id.overview_activity_list_time);
            TextView nameTextView = (TextView) v.findViewById(R.id.overview_activity_list_name);
            ImageView typeImageView = (ImageView) v.findViewById(R.id.overview_activity_list_type);

            timeTextView.setText(getContext().getString(R.string.list_time_span, event.getTimeSpan()));
            nameTextView.setText(getContext().getString(R.string.name, event.getName()));

            typeImageView.setImageResource(getContext().getResources().getIdentifier(
                    EventTypeHelper.getDrawableNameByType(event.getType()),
                    "drawable",
                    getContext().getPackageName()
            ));


        }

        return v;
    }
}
