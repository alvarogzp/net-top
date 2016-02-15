package org.alvarogp.nettop.common.presentation.presenter;

import org.alvarogp.nettop.common.presentation.view.View;

public interface Presenter<T extends View> {
    void initialize(T view);
    void resume();
    void pause();
    void destroy();
}
