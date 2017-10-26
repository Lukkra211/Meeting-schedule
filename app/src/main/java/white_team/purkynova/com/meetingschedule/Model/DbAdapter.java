package white_team.purkynova.com.meetingschedule.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class is a parent to all other models.
 *
 * @author Lukáš Krajíček
 */
abstract class DbAdapter {
    private final String DATABASE_NAME = "event_database";
    private final int DATABASE_VERSION = 1;

    private final String CHILD_TABLE_NAME;
    private final String CHILD_COL_ID;

    /** @brief object that calls callbacks on specifics event, see detailed object documentation */
    private DatabaseHelper databaseHelper;

    /** @brief db object that is used to manipulating data in database */
    protected SQLiteDatabase db;

    private final String SEARCH_QUERY = "SELECT * FROM `%s` WHERE %s = ?";


    DbAdapter(Context context) {
        this.CHILD_TABLE_NAME = getTableName();
        this.CHILD_COL_ID = getIdColumnName();
        this.databaseHelper = new DatabaseHelper(context);
    }


    /**
     * Create writable database
     */
    protected void dbOpen() {
        this.db = this.databaseHelper.getWritableDatabase();
    }

    /**
     * Close database if created
     */
    protected void dbClose() {
        if (this.db != null) {
            this.db.close();
        }
    }


    /*
     * =============================================================================================
     * Services for childes
     * ---------------------------------------------------------------------------------------------
     * Methods below are methods that every class - that inherit from DbAdapter - cat use. This
     * should reduce repetitive code in case more database models are needed.
     * =============================================================================================
     */

    /**
     * Return parameter with table prefix, column_name -> `table_name`.`column_name`
     *
     * @param columnName column name
     * @return colum name with table prefix
     */
    protected String t(String columnName) {
        return String.format("`%s`.`%s`", CHILD_TABLE_NAME, columnName);
    }

    /**
     * Get row by id
     *
     * @param id id of the database row
     * @return {@link Cursor}
     */
    protected Cursor _get(int id) {
        return this.db.rawQuery(
                String.format(this.SEARCH_QUERY, this.CHILD_TABLE_NAME, this.CHILD_COL_ID),
                new String[] {String.valueOf(id)}
        );
    }


    /*
     * =============================================================================================
     * Abstract getters
     * ---------------------------------------------------------------------------------------------
     * This model provides some very basic operation with table of the child class. In order to
     * be able to provide such service, the class needs bunch of information about child. These
     * information handover are handled by function calls, that <strong>have to</strong> return
     * proper information.
     * =============================================================================================
     */

    /**
     * @return child's id-column name
     */
    abstract String getIdColumnName();

    /**
     * @return child's table name
     */
    abstract String getTableName();


	/*
     * =============================================================================================
     * Database migration callbacks
     * =============================================================================================
     */

	/**
	 * A class that call abstract methods as callbacks when database is not present and needs to be
     * created or database model is old and needs to be upgraded.
	 */
    private class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * This method is triggered when database hasn't been created yet. The method should create all
         * necessary tables and insert vital information in it.
         *
         * @param db the database to initialize
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("DbAdapter", "Creating the database");
            DbMigration dbMigration = new DbMigration();
            dbMigration.onCreate(db);
        }

        /**
         * This method is triggered when app is using new database model but device has the old one. The
         * method should do changes that upgrade old database model to the new one.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("DbAdapter", "Upgrading the database");
            DbMigration dbMigration = new DbMigration();
            dbMigration.onUpgrade(db, oldVersion, newVersion);
        }
    }
}
