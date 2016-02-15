package org.alvarogp.nettop.metric.presentation.parser;

import android.content.Context;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.common.presentation.parser.Parser;
import org.alvarogp.nettop.common.presentation.parser.parsed.Parsed;
import org.alvarogp.nettop.common.presentation.parser.parsed.ParsedFactory;

import javax.inject.Inject;

public class UpdateIntervalParser implements Parser<String, Long> {
    private static final int MILLIS_PER_SECOND = 1000;
    private static final int TOO_LOW_THRESHOLD_MILLIS = 500;

    private final Context context;
    private final ParsedFactory parsedFactory;

    @Inject
    public UpdateIntervalParser(Context context, ParsedFactory parsedFactory) {
        this.context = context;
        this.parsedFactory = parsedFactory;
    }

    @Override
    public Parsed<Long> parse(String seconds) {
        try {
            float parsedSeconds = Float.parseFloat(seconds);
            long millis = (long) (parsedSeconds * MILLIS_PER_SECOND);
            if (isPositiveOrZero(millis)) {
                if (isTooLow(millis)) {
                    return parsedFactory.createValidWithWarning(millis, getString(R.string.warning_message_update_interval_too_low_number));
                }
                return parsedFactory.createValid(millis);
            } else {
                return parsedFactory.createInvalid(getString(R.string.error_message_update_interval_negative_number));
            }
        } catch (NumberFormatException e) {
            return parsedFactory.createInvalid(getString(R.string.error_message_update_interval_invalid_number));
        }
    }

    private boolean isPositiveOrZero(long millis) {
        return millis >= 0;
    }

    private boolean isTooLow(long millis) {
        return millis < TOO_LOW_THRESHOLD_MILLIS;
    }

    private String getString(int resId) {
        return context.getString(resId);
    }
}
