package org.alvarogp.nettop.metric.presentation.view.android.renderer;

import android.view.View;

import org.alvarogp.nettop.common.domain.logger.Logger;

import javax.inject.Inject;

public class MetricRendererFactory {
    private final Logger logger;

    @Inject
    public MetricRendererFactory(Logger logger) {
        this.logger = logger;
    }

    public MetricRenderer createMetricRenderer(View itemView) {
        return new MetricRenderer(logger, itemView);
    }
}
