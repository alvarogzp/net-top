package org.alvarogp.nettop.metric.presentation.view.android.renderer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.metric.presentation.model.MetricUi;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MetricRenderer extends RecyclerView.ViewHolder {
    private final MetricValueRenderer rxValueRenderer;
    private final MetricValueRenderer txValueRenderer;
    private final MetricValueRenderer totalValueRenderer;

    @Bind(R.id.metric_label) TextView label;

    @Bind(R.id.metric_rx_value_view) View rxValueView;
    @Bind(R.id.metric_tx_value_view) View txValueView;
    @Bind(R.id.metric_total_value_view) View totalValueView;

    @BindString(R.string.direction_rx) String rxDirectionText;
    @BindString(R.string.direction_tx) String txDirectionText;
    @BindString(R.string.direction_rx_tx) String rxTxDirectionText;

    public MetricRenderer(Logger logger, View itemView) {
        super(itemView);

        logger.debug(this, "Creating new MetricRenderer");

        ButterKnife.bind(this, itemView);

        rxValueRenderer = new MetricValueRenderer(rxValueView, rxDirectionText);
        txValueRenderer = new MetricValueRenderer(txValueView, txDirectionText);
        totalValueRenderer = new MetricValueRenderer(totalValueView, rxTxDirectionText);
    }

    public void render(MetricUi metric) {
        label.setText(metric.getOwner().getName());
        rxValueRenderer.render(metric.getRxValue());
        txValueRenderer.render(metric.getTxValue());
        totalValueRenderer.render(metric.getValue());
    }
}
