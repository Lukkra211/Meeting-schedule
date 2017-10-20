
package white_team.purkynova.com.meetingschedule.Model;

/**
 * Created by Lukáš Krajíček on 15.10.17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import white_team.purkynova.com.meetingschedule.Event.Event;

/**
 * Main database model. This have to call other "childOnCreate" and "childOnUpgrade"
 * functions from other models. Only this model should be visible out of the package. Other ones
 * should be accessible via this class from the outside.
 */
final class EventModel extends DbAdapter {
    private final String TABLE_NAME = "event";

    // Table columns
    private final String COL_ID = "id";
    private final String COL_NAME = "name";
    private final String COL_TYPE = "type";
    private final String COL_SINCE = "since";
    private final String COL_TILL = "till";
    private final String COL_INFO = "text_info";

    // String to identify this class in log
    private final String TAG = "EventModel";
    private final String TABLE_CREATE = String.format(
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

    @Override
    void childOnCreate(SQLiteDatabase db) {
        Log.i(this.TAG, String.format("Creating the `$s` table", this.TABLE_NAME));
        Log.v(this.TAG, String.format("SQL:\n%s", this.TABLE_CREATE));
        db.execSQL(TABLE_CREATE);
        // TODO: if other models files exists, their childOnCreate methods have to be called here
    }

    @Override
    void childOnUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(this.TAG, "Upgrading the database");
        Log.v(this.TAG, "Removing the old table");
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        this.childOnCreate(db);
        // TODO: if other models files exists, their childOnUpgrade methods have to be called here
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
        return null;
    }

    /**
     * Return list of events that take place in given date
     *
     * @param date given date by ISO 8601 standart: YYYY-MM-DD
     * @return the number of rows affected
     */
    public Event getEventsByDate(String date) {
        return null;
    }

    /**
     * Insert one event to the database
     *
     * @param event event to insert
     * @return int the number of events inserted (should be 1)
     */
    public int insertEvent(Event event) {
        return 0;
    }

    /**
     * Insert events to the database
     *
     * @param events list of events to be inserted
     * @return int the number of events inserted
     */
    public int insertEvents(Event[] events) {
        return 0;
    }
}
