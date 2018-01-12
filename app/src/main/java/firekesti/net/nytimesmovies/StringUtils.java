package firekesti.net.nytimesmovies;

import android.content.Context;
import android.text.TextUtils;

import java.util.Locale;

import firekesti.net.nytimesmovies.network.models.Result;

/**
 * A Utils class to manipulate strings
 */
public final class StringUtils {
    /**
     * Format string used for long time expressions which include hours: 1h 23m
     */
    private static final String LONG_WORDY_TIME_FORMAT = "%01dh %02dm";
    /**
     * Format string used for short time expressions which are less than an hour: 45m
     */
    private static final String SHORT_WORDY_TIME_FORMAT = "%01dm";

    private static final int MINUTES_IN_HOUR = 60;

    // Hide constructor for utility class
    private StringUtils() {
    }

    /**
     * If greater than or equal to 1 hour display hours and minutes, standardized on "2h 15m left"
     * If less than 60 minutes display minutes, rounded down (20 min 10 sec displays as 20) standardized on "20m left"
     * If less than 1 minute display "1m left"
     */
    public static String getHourMinuteFromMinutes(int minutes) {
        if (minutes >= MINUTES_IN_HOUR) {
            return String.format(Locale.US, LONG_WORDY_TIME_FORMAT, minutes / MINUTES_IN_HOUR, minutes % MINUTES_IN_HOUR);
        } else {
            return String.format(Locale.US, SHORT_WORDY_TIME_FORMAT, minutes);
        }
    }

    public static String getYearRatingRuntime(Result result, Context context) {
        String divider = context.getString(R.string.text_divider);
        // Set the year, rating, and runtime, like "2017  |  R  |  1h 23m"
        // Likewise, handle gracefully if one or more values are missing
        StringBuilder sb = new StringBuilder();
        if (result.getYear() != null) {
            sb.append(String.valueOf(result.getYear())).append(divider);
        }
        if (!TextUtils.isEmpty(result.getRating())) {
            sb.append(result.getRating()).append(divider);
        }
        if (result.getRuntimeUs() != null) {
            sb.append(StringUtils.getHourMinuteFromMinutes(result.getRuntimeUs()));
        }
        return sb.toString();
    }
}