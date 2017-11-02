package white_team.purkynova.com.meetingschedule.Model;

import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.util.ArrayList;

import white_team.purkynova.com.meetingschedule.Event.Event;

/**
 * Main database model. This have to call other "childOnCreate" and "childOnUpgrade"
 * functions from other models. Only this model should be visible out of the package. Other ones
 * should be accessible via this class from the outside.
 *
 * @author Lukáš Krajíček
 */
public final class EventModel extends DbAdapter {
    static final String TABLE_NAME = "event";

    // Table columns
    static final String COL_ID = "id";
    static final String COL_NAME = "name";
    static final String COL_TYPE = "type";
    static final String COL_PLACE = "place";
    static final String COL_SINCE = "since";
    static final String COL_TILL = "till";
    static final String COL_INFO = "text_info";
    static final String COL_LECTURER = "lecturer";

    // String to identify this class in log
    static final String TAG = "EventModel";
    static final String TABLE_CREATE = String.format(
            "CREATE TABLE if not exists `%s` (" +
            "`%s` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "`%s` INTEGER NOT NULL," +
            "`%s` TEXT NOT NULL," +
            "`%s` TEXT NOT NULL, " +
            "`%s` TEXT NOT NULL, " +
            "`%s` TEXT NOT NULL, " +
            "`%s` TEXT NOT NULL, " +
            "`%s` TEXT DEFAULT NULL);",
            TABLE_NAME, COL_ID, COL_TYPE, COL_PLACE, COL_NAME,
            COL_SINCE, COL_TILL, COL_INFO, COL_LECTURER
    );

    public EventModel(Context context) {
        super(context);
        dbOpen();
    }


    /*
     * =============================================================================================
     * Overriding abstract class
     * ---------------------------------------------------------------------------------------------
     * These methods are required from parent (DbAdapter). See their documentation for details.
     * =============================================================================================
     */

    @Override
    String getIdColumnName() {
        return COL_ID;
    }

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    /*
     * =============================================================================================
     * SQL methods
     * ---------------------------------------------------------------------------------------------
     * Below are methods that this class provides to programmers everywhere in the application.
     * This makes data processes, such as getting, updating, removing information from database, lot
     * easier and available everywhere without repetitive code.
     * =============================================================================================
     */

    /**
     * Return event by id
     *
     * @param id id of an event
     * @return {@link Event}
     * @throws ParseException
     */
    public Event get(int id) {
        Cursor rows = super._get(id);
        if (rows.moveToFirst()) {
            try {
                return this._createEventFromCursor(rows);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Return list of events that take place in given date
     *
     * @param date given date by ISO 8601 standart: YYYY-MM-DD
     * @return the number of rows affected
     */
    public ArrayList<Event> getEventsByDate(String date) {
        Cursor rows = this.db.rawQuery(
                String.format("SELECT * from `%s` WHERE `%s` LIKE ?", TABLE_NAME, COL_SINCE),
                new String[] {date + "%"}
        );

        if (rows.moveToFirst()) {
            ArrayList<Event> eventList = new ArrayList<>();
            do {
                try {
                    eventList.add(this._createEventFromCursor(rows));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (rows.moveToNext());

            return eventList;
        } else {
            return null;
        }
    }

    /**
     * Create Event object from given Cursor object
     *
     * Cursor object have to be already moved to some row
     * @param cursor Cursor object
     * @return Event object
     */
    private Event _createEventFromCursor(Cursor cursor) throws ParseException {
        int index_id = cursor.getColumnIndex(EventModel.COL_ID);
        int index_name = cursor.getColumnIndex(EventModel.COL_NAME);
        int index_since = cursor.getColumnIndex(EventModel.COL_SINCE);
        int index_till = cursor.getColumnIndex(EventModel.COL_TILL);
        int index_type = cursor.getColumnIndex(EventModel.COL_TYPE);
        int index_place = cursor.getColumnIndex(EventModel.COL_PLACE);
        int index_info = cursor.getColumnIndex(EventModel.COL_INFO);
        int index_lecturer = cursor.getColumnIndex(EventModel.COL_LECTURER);

        return new Event(
                cursor.getInt(index_id),
                cursor.getString(index_name),
                cursor.getString(index_since),
                cursor.getString(index_till),
                cursor.getString(index_type),
                cursor.getString(index_info),
                cursor.getString(index_place),
                cursor.getString(index_lecturer)
        );
    }
}
