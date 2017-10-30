package white_team.purkynova.com.meetingschedule.Model;

/**
 * Class that helps to translate type of events between int and String forms
 *
 * @author Lukáš Krajíček
 */
public final class EventTypeHelper {
    public static final String TYPE_LECTURE = "lecture";
    public static final String TYPE_FOOD = "food";
    public static final String TYPE_FREE_TIME = "free time";
    public static final String TYPE_EXCURSION = "excursion";

    private static final int TYPE_LECTURE_INDEX = 1;
    private static final int TYPE_FOOD_INDEX = TYPE_LECTURE_INDEX + 1;
    private static final int TYPE_FREE_TIME_INDEX = TYPE_FOOD_INDEX + 1;
    private static final int TYPE_EXCURSION_INDEX = TYPE_FREE_TIME_INDEX + 1;

    public static int stringToInt(String tag) {
        switch (tag) {
            case TYPE_LECTURE:
                return TYPE_LECTURE_INDEX;

            case TYPE_FOOD:
                return TYPE_FOOD_INDEX;

            case TYPE_FREE_TIME:
                return TYPE_FREE_TIME_INDEX;

            case TYPE_EXCURSION:
                return TYPE_EXCURSION_INDEX;

            default:
                throw new RuntimeException(String.format("No tag \"%s\" doesn't exists", tag));
        }
    }

    public static String intToString(int tagValue) {
        switch (tagValue) {
            case TYPE_LECTURE_INDEX:
                return TYPE_LECTURE;

            case TYPE_FOOD_INDEX:
                return TYPE_FOOD;

            case TYPE_FREE_TIME_INDEX:
                return TYPE_FREE_TIME;

            case TYPE_EXCURSION_INDEX:
                return TYPE_EXCURSION;

            default:
                throw new RuntimeException(String.format("No tag with value %d exists.", tagValue));
        }
    }
}
