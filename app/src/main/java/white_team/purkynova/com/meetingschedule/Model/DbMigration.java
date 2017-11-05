package white_team.purkynova.com.meetingschedule.Model;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import white_team.purkynova.com.meetingschedule.Event.Event;

/**
 * Manage initializing the database and upgrading it.
 *
 * @author Lukáš Krajíček
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

        this.insertData(db);
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

    private void insertData(SQLiteDatabase db) {
        String SQL = String.format(
                "INSERT INTO `%s` (`%s`, `%s`, `%s`, `%s`, `%s`, `%s`, `%s`, `%s`) VALUES " +
                        "('Welcome', 'presentation', 'School canteen', '2017-11-06T08:00', '2017-11-06T08:20', 'Here we will be welcoming you and the principal of our school together with mayor of the local city part will do the speech.', 'Elena Tomanová (teacher)', NULL), " +
                        "('Organization: additional information', 'presentation', 'School canteen', '2017-11-06T08:20', '2017-11-06T08:30', 'Marek Havel and Lukáš Krajíček will tell you some additional information that you should know.', 'Marek Havel (student), Lukáš Krajíček (student)', NULL), " +
                        "('Project Management for Programmers', 'presentation', 'School canteen', '2017-11-06T08:30', '2017-11-06T09:00', 'Wolfgang will tell you about how you are going to manage your own project during meeting.', 'Wolfgang Bay (teacher)', NULL), " +
                        "('Organization: project diary, discussion', 'presentation', 'School canteen', '2017-11-06T09:00', '2017-11-06T09:30', 'Wolfgang will introduce the organization of meeting and the goal to the students. Here you can ask Wolfgang everything you need to know about meeting.', 'Wolfgang Bey (teacher)', NULL), " +
                        "('Topic 0: Introduction to databases', 'lecture', 'School canteen', '2017-11-06T09:30', '2017-11-06T11:00', 'Jan Šťastný will describe you a concept of databases and what the database is.', 'Jan Šťastný (Redhat)', NULL), " +
                        "('School tour, refreshment', 'free time', 'The school and Common room', '2017-11-06T11:00', '2017-11-06T12:00', 'Our students from your team will show you the school. Then, you will have a break - you know, some coffee, tee + cake and pizza rolls.', 'Jan Šafář (teacher), Nikol Hegyiová (student)', NULL), " +
                        "('Topic 1: Relational database', 'lecture', 'U01', '2017-11-06T12:00', '2017-11-06T13:30', 'Lukáš Macek and Vítek Hoch will tell you about basics, some terminology and rules of relation databases.', 'Lukáš Macek (student); Vítek Hoch (student)', NULL), " +
                        "('Lunch', 'food', 'School canteen', '2017-11-06T13:30', '2017-11-06T14:00', 'Slice of pork, pasta, gravy; Stewed vegetables (peppers and tomatoes) with eggs, sausage and bread', NULL, NULL), " +
                        "('Topic 2: SQLite, creating tables, SQL', 'lecture', 'U01', '2017-11-06T14:00', '2017-11-06T15:30', 'Rostislav and Petr will tell you the basics about database SQL language. This lecture is not about Android database yet, but it is the need to know how to do it in general.', 'Rosťislav Drápela (student), Petr Němec (student)', NULL), " +
                        "('What to store to database in our project', 'project work', 'U39, U47', '2017-11-06T15:30', '2017-11-06T16:30', 'First team work. Your team will consider what the application should store in your database and then you will create a database scheme. Also you will have some time to work on the application itself.', 'Lukáš Macek (student); Rosťa Drápela (student)', NULL), " +
                        "('Presenting the results of team work', 'presentation', 'School canteen', '2017-11-06T16:30', '2017-11-06T17:00', 'Teams will present their database model and the logic behind the model.', 'Lenka Hrušková (teacher); Teams', NULL), " +
                        "('Brno city task-based sightseeing in groups', 'free time', 'Brno center', '2017-11-06T17:00', '2017-11-06T19:00', 'A bit more entertaining sightseeing.', 'Franta Soudek (student)', NULL), " +
                        "('Dinner (just teachers, students in teams)', 'food', 'Brno center', '2017-11-06T19:00', '2017-11-06T21:00', 'Students in team will choose any restaurant in Brno.', 'Elena Tomanová (teacher)', NULL), " +
                        "('Topic 3: Relational databases in Android', 'lecture', 'U01', '2017-11-07T08:00', '2017-11-07T10:00', 'Lukáš Krajíček will will create very basics database model.', 'Lukáš Krajíček (student)', NULL), " +
                        "('Refreshment', 'free time', 'Common room', '2017-11-07T10:00', '2017-11-07T10:30', 'Pause to coffee or tee or both! + cake, apple pie and some fruits.', 'Nikol Hegyiová (student); Zdeňka Škrdlová (student)', NULL), " +
                        "('Data in our projects', 'project work', 'U39, U47', '2017-11-07T10:30', '2017-11-07T11:40', 'Based on the previous lectures, your team will create database model for your application.', 'Lenka Hrušková (teacher); Teams', NULL), " +
                        "('Lunch', 'food', 'School canteen', '2017-11-07T11:40', '2017-11-07T12:10', 'Filled pork fillet, mashed potatoes, red cabbage salad; Pork with sauerkraut, dumplings', NULL, NULL), " +
                        "('Topic 4: Arduino measurements', 'lecture', 'U01', '2017-11-07T12:10', '2017-11-07T13:00', 'Small lecture about how we will be using Arduino in the trip - how we will be measuring temperature, humidity, atmospheric measurements.', 'Petr Pernes (teacher), Jan Rodák (student), Petr Němec (student)', NULL), " +
                        "('Trip – Moravian Krast, the Macocha cave', 'free time', 'Moravian Krast and Macocha cave', '2017-11-07T13:00', '2017-11-07T17:00', 'On Tuesday afternoon we will goby coach  for a field trip to Moravian carst. We will go there to monitor the temperature and humidity in the caves and surrounding which will be used in some of your applications. We wil visit the Punkva cave, Macocha Abyss and surrounding. The trip will include some walking in the countryside, so don´t forget good walking boots. At the end we well go for dinner to a nearby restaurant Skalní Mlýn.', 'Elena Tomanová (teacher); Petr Pernes (teacher)', NULL), " +
                        "('Dinner – Skalní mlýn', 'food', 'Blansko', '2017-11-07T17:00', '2017-11-07T19:00', 'Grilled Punkva trout, boiled potatoes with butter approx; Grilled Punkva trout, boiled potatoes with butter approx; Pork schnitzel, boiled potatoes with butter approx; Roast chicken leg, rice, cabbage salad approx; Fried cheese, chips, tartar sauce approx', 'Elena Tomanová (teacher)', NULL), " +
                        "('Topic 5: Redhat: OS Unix/Linux', 'lecture', 'School canteen', '2017-11-08T08:00', '2017-11-08T09:30', 'Lukáš Růžička will present you what unix/linux is, how it looks and why you should use one.', 'Lukáš Růžička (Redhat)', NULL), " +
                        "('Refreshment', 'free time', 'Common room', '2017-11-08T09:30', '2017-11-08T10:00', 'Another refreshments + cake and apple pie.', 'Nikol Hegyiová (student); Zdeňka Škrdlová (student)', NULL), " +
                        "('Workshop in Red Hat: OS Unix/Linux', 'workshop', 'Red Hat', '2017-11-08T10:00', '2017-11-08T12:30', 'Redhat and Linux. What it is and why you should try it.', 'Lenka Hrušková (teacher); Marek Havel (student)', NULL), " +
                        "('Lunch', 'food', 'School canteen', '2017-11-08T12:50', '2017-11-08T13:20', 'Roast pork on vegetables, potatoes, cucumber salad; Smoked pork with pie mash, pickles, bread', NULL, NULL), " +
                        "('Implementation databases to projects', 'project work', 'U39, U47', '2017-11-08T14:00', '2017-11-08T16:00', 'Next block for you to implement the model and work on project.', 'Lenka Hrušková (teacher); Teams', NULL), " +
                        "('Bowling with dinner', 'free time', 'Brno city - street: Jugoslávská', '2017-11-08T18:00', '2017-11-08T22:00', 'All participants can play bowling for free. It is already paid for 2 hours. There are also another games you can try, but they are not paid from the project.', 'Elena Tomanová (teacher)', NULL), " +
                        "('Topic 6: Raspberry vs Arduino', 'lecture', 'U01', '2017-11-09T08:00', '2017-11-09T08:30', 'Introduction of the Raspberry.', 'Martin Nečas (student); Jan Rodák (student)', NULL), " +
                        "('Working on project', 'project work', 'U01', '2017-11-09T08:30', '2017-11-09T10:00', 'Extra time that you can spend on the project.', 'Teams', NULL), " +
                        "('Refreshment', 'free time', 'Common room', '2017-11-09T10:00', '2017-11-09T10:30', 'A refreshments between two lectures to relax. There will be cake and muffins.', 'Nikol Hegyiová (student); Zdeňka Škrdlová (student)', NULL), " +
                        "('Topic 7: Database security', 'lecture', 'U01', '2017-11-09T10:30', '2017-11-09T12:00', 'Stefan Densborn will teach you about database security.', 'Stefan Densborn', NULL), " +
                        "('Lunch', 'food', 'School canteen', '2017-11-09T12:00', '2017-11-09T12:30', 'Milano spaghetti with cheese; Vienna schnitzel, potatoes, cucumber salad with chickpeas', NULL, NULL), " +
                        "('Preparation of presentation in teams', 'project work', 'U39, U47', '2017-11-09T13:00', '2017-11-09T14:30', 'Your team will create presentation of work you did. Also you can also use the time to fix some bugs or complete some features of the application.', 'Teams', NULL), " +
                        "('Sightseeing with guide', 'free time', 'Brno city', '2017-11-09T15:00', '2017-11-09T18:00', 'Time for you to chat with others, go somewhere to drink or to buy some nice things or you can also go sightseeing if you want to.', 'Elena Tomanová (teacher)', NULL), " +
                        "('Preparation of presentation', 'project work', 'U39, U47', '2017-11-10T08:00', '2017-11-10T10:00', 'Time to finish all your work.', 'Teams', NULL), " +
                        "('Refreshment', 'free time', 'Common room', '2017-11-10T10:00', '2017-11-10T10:30', 'Here you can prepare yourselves before presenting or just relax. There will be cakes and apple pie.', 'Nikol Hegyiová (student); Zdeňka Škrdlová (student)', NULL), " +
                        "('Presentations', 'presentation', 'U01', '2017-11-10T10:30', '2017-11-10T12:20', 'Presentation of results your team had done during this meeting.', 'Teams', NULL), " +
                        "('Lunch', 'food', 'School cafeteria', '2017-11-10T12:20', '2017-11-10T13:00', 'Chicken leg, rice, mixed salad; Mix of turkey meat, potatoes and carrots, bread', NULL, NULL), " +
                        "('Vida centrum', 'free time', 'Brno city', '2017-11-10T14:00', '2017-11-10T18:00', 'Vida centrum is a science centrum with lot of interesting scientific things. There are over 170 exhibits separated into 4 different topics: Planet, Civilisation, Human and Microworld.', 'Elena Tomanová (teacher)', NULL), " +
                        "('Dinner', 'food', 'Brno city', '2017-11-10T18:00', '2017-11-10T20:00', 'Chicken breast on grilled vegies (aubergine, courgetter, petter, potatoes, cherry tomatoes) with basil pesto; Pork fillet with beans baked on bacon, mashed potatoes; Ceasar salad with chicken meat and parmasan; Tagliatelle with tomato sauce, fresh basil and parmesan', 'Elena Tomanová (teacher)', NULL);",
                EventModel.TABLE_NAME, EventModel.COL_NAME, EventModel.COL_TYPE,
                EventModel.COL_PLACE, EventModel.COL_SINCE, EventModel.COL_TILL,
                EventModel.COL_INFO, EventModel.COL_LECTURER, EventModel.COL_MATERIAL
        );
        Log.v(this.TAG, "Inserting data to the database");
        db.execSQL(SQL);
    }
}
