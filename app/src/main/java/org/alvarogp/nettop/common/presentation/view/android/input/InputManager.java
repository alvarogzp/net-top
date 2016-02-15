package org.alvarogp.nettop.common.presentation.view.android.input;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

public class InputManager {
    private final InputMethodManager inputMethodManager;

    @Inject
    public InputManager(InputMethodManager inputMethodManager) {
        this.inputMethodManager = inputMethodManager;
    }

    /**
     * Request to hide the soft input method (IME).
     *
     * You must pass the {@link View} object that is currently accepting input to authenticate
     * with the IME service.
     */
    public void hideSoftInputFrom(View inputView) {
        inputMethodManager.hideSoftInputFromWindow(inputView.getWindowToken(), 0);
    }
}
