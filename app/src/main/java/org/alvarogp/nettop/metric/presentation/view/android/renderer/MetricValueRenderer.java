package org.alvarogp.nettop.metric.presentation.view.android.renderer;

import android.view.View;
import android.widget.TextView;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.metric.presentation.model.MetricUiValue;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MetricValueRenderer {
    private final View view;

    @Bind(R.id.metric_value) TextView value;
    @Bind(R.id.metric_unit) TextView unit;
    @Bind(R.id.metric_value_direction) TextView direction;

    public MetricValueRenderer(View view, CharSequence directionText) {
        this.view = view;
        ButterKnife.bind(this, view);
        direction.setText(directionText);
    }

    public void render(MetricUiValue valueUnit) {
        if (valueUnit.isDisplayable()) {
            view.setVisibility(View.VISIBLE);
            value.setText(valueUnit.getValue());
            unit.setText(valueUnit.getUnit());
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
