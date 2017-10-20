
package white_team.purkynova.com.meetingschedule.Model;

/**
 * Created by Lukáš Krajíček on 15.10.17.
 */

import android.content.Context;

import white_team.purkynova.com.meetingschedule.Event.Event;

/**
 * Main database model. This have to call other "childOnCreate" and "childOnUpgrade"
 * functions from other models. Only this model should be visible out of the package. Other ones
 * should be accessible via this class from the outside.
 */
final class EventModel extends DbAdapter {
    static final String TABLE_NAME = "event";

    // Table columns
    static final String COL_ID = "id";
    static final String COL_NAME = "name";
    static final String COL_TYPE = "type";
    static final String COL_SINCE = "since";
    static final String COL_TILL = "till";
    static final String COL_INFO = "text_info";

    // String to identify this class in log
    static final String TAG = "EventModel";
    static final String TABLE_CREATE = String.format(
            "CREATE TABLE if not exists `%s` (" +
            "`%s` INTEGER PRIMARY KEY, " +
            "`%s` INTEGER NOT NULL," +
            "`%s` TEXT NOT NULL, " +
            "`%s` TEXT NOT NULL, ",
            "`%s` TEXT NOT NULL, ",
            "`%s` TEXT NOT NULL);",
            TABLE_NAME, COL_ID, COL_TYPE, COL_NAME, COL_SINCE, COL_TILL, COL_INFO
    );

    EventModel(Context context) {
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
        // TODO: write method
        return "";
    }

    @Override
    String getTableName() {
        // TODO: write method
        return "";
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

    // TODO: write the body of these SQL methods
    // NOTE: don't forget that you can use methods 'get' and 'delete' from DbAdapter

    /**
     * Return event by id
     *
     * @param id id of an event
     * @return {@link Event}
     */
    public Event get(int id) {
        // TODO: write method
        return null;
    }

    /**
     * Return list of events that take place in given date
     *
     * @param date given date by ISO 8601 standart: YYYY-MM-DD
     * @return the number of rows affected
     */
    public Event getEventsByDate(String date) {
        // TODO: write method
        return null;
    }

    /**
     * Insert one event to the database
     *
     * @param event event to insert
     * @return int the number of events inserted (should be 1)
     */
    public int insertEvent(Event event) {
        // TODO: write method
        return 0;
    }

    /**
     * Insert events to the database
     *
     * @param events list of events to be inserted
     * @return int the number of events inserted
     */
    public int insertEvents(Event[] events) {
        // TODO: write method
        return 0;
    }
}
