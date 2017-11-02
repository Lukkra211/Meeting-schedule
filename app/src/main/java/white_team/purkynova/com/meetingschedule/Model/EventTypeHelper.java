package white_team.purkynova.com.meetingschedule.Model;

/**
 * Class that helps to translate type of events between int and String forms
 *
 * @author Lukáš Krajíček
 */
public final class EventTypeHelper {
    public static final String TYPE_LECTURE = "lecture";
    public static final String TYPE_PRESENTATION = "presentation";
    public static final String TYPE_WORKSHOP = "workshop";
    public static final String TYPE_WORK = "project work";
    public static final String TYPE_FOOD = "food";
    public static final String TYPE_FREE_TIME = "free time";


    public static final int TYPE_LECTURE_INDEX = 1;
    public static final int TYPE_PRESENTATION_INDEX = TYPE_LECTURE_INDEX + 1;
    public static final int TYPE_WORKSHOP_INDEX = TYPE_PRESENTATION_INDEX + 1;
    public static final int TYPE_WORK_INDEX = TYPE_WORKSHOP_INDEX + 1;
    public static final int TYPE_FOOD_INDEX = TYPE_WORK_INDEX + 1;
    public static final int TYPE_FREE_TIME_INDEX = TYPE_FOOD_INDEX + 1;

    public static int stringToInt(String tag) {
        switch (tag) {
            case TYPE_LECTURE:
                return TYPE_LECTURE_INDEX;

            case TYPE_PRESENTATION:
                return TYPE_PRESENTATION_INDEX;

            case TYPE_WORKSHOP:
                return TYPE_WORKSHOP_INDEX;

            case TYPE_WORK:
                return TYPE_WORK_INDEX;

            case TYPE_FOOD:
                return TYPE_FOOD_INDEX;

            case TYPE_FREE_TIME:
                return TYPE_FREE_TIME_INDEX;

            default:
                throw new RuntimeException(String.format("No tag \"%s\" doesn't exists", tag));
        }
    }

    public static String intToString(int tagValue) {
        switch (tagValue) {
            case TYPE_LECTURE_INDEX:
                return TYPE_LECTURE;

            case TYPE_PRESENTATION_INDEX:
                return TYPE_PRESENTATION;

            case TYPE_WORKSHOP_INDEX:
                return TYPE_WORKSHOP;

            case TYPE_WORK_INDEX:
                return TYPE_WORK;

            case TYPE_FOOD_INDEX:
                return TYPE_FOOD;

            case TYPE_FREE_TIME_INDEX:
                return TYPE_FREE_TIME;

            default:
                throw new RuntimeException(String.format("No tag with value %d exists.", tagValue));
        }
    }

    public static String getDrawableNameByType(String type) {
        switch (type) {
            case TYPE_LECTURE:
                return "ic_event_type_lecture_black";

            case TYPE_PRESENTATION:
                return "ic_event_type_presentation_black";

            case TYPE_WORKSHOP:
                return "ic_event_type_workshop_black";

            case TYPE_WORK:
                return "ic_event_type_work_black";

            case TYPE_FOOD:
                return "ic_event_type_food_black";

            case TYPE_FREE_TIME:
                return "ic_event_type_free_time_black";

            default:
                throw new RuntimeException("No tag resource for that type exists.");
        }
    }
}
