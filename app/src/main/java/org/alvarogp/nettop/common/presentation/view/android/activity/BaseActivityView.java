package org.alvarogp.nettop.common.presentation.view.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.squareup.phrase.Phrase;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.common.presentation.view.View;

import javax.inject.Inject;

/**
 * Base class for activities that implement View (or a derivate one).
 * Provides utility methods for views.
 */
public class BaseActivityView extends BaseActivity implements View {
    private static final String TEMPLATE_PLACEHOLDER = "message";

    @Inject protected Logger logger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    @Override
    public void showError(String message) {
        logger.error(this, message);
        showToastMessage(formatTemplate(R.string.error_template, message));
    }

    @Override
    public void showWarning(String message) {
        logger.warn(this, message);
        showToastMessage(formatTemplate(R.string.warning_template, message));
    }

    @Override
    public void showInfo(String message) {
        logger.info(this, message);
        showToastMessage(formatTemplate(R.string.info_template, message), Toast.LENGTH_SHORT);
    }

    private CharSequence formatTemplate(int resId, String message) {
        return Phrase.from(this, resId)
                .put(TEMPLATE_PLACEHOLDER, message)
                .format();
    }

    protected void showToastMessage(CharSequence message) {
        showToastMessage(message, Toast.LENGTH_LONG);
    }

    protected void showToastMessage(CharSequence message, int duration) {
        Toast.makeText(this, message, duration).show();
    }
}
