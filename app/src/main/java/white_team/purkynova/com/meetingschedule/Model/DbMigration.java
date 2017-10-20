package white_team.purkynova.com.meetingschedule.Model;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by lukkra on 21.10.17.
 */

/**
 * Manage initializing the database and upgrading it.
 */
final class DbMigration {
    private String TAG = "DbMigration";

    /**
     * This method create all tables and insert all vital data, that are needed from the start of
     * the app.
     *
     * @param db the database to initialize
     */
    void onCreate(SQLiteDatabase db) {
        Log.v(EventModel.TAG, String.format("SQL:\n%s", EventModel.TABLE_CREATE));
        db.execSQL(EventModel.TABLE_CREATE);
    }

    /**
     * In case, that database model has been updated alongside application version, this method
     * change db model in device to the newest one.
     *
     * @param db the database to upgrade
     * @param oldVersion actual version of device
     * @param newVersion the newest database version available
     */
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(this.TAG, String.format("Updating database from %d to %d", oldVersion, newVersion));
        // For now there is just one version of database model so there is no need for onUpgrade
        // to do anything now.
    }
}
