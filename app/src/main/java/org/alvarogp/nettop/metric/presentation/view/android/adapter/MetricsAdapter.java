package org.alvarogp.nettop.metric.presentation.view.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.metric.presentation.model.MetricUi;
import org.alvarogp.nettop.metric.presentation.view.android.renderer.MetricRenderer;
import org.alvarogp.nettop.metric.presentation.view.android.renderer.MetricRendererFactory;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MetricsAdapter extends RecyclerView.Adapter<MetricRenderer> {
    private static final int METRIC_LAYOUT_ID = R.layout.metrics_metric;

    private final LayoutInflater layoutInflater;
    private final MetricRendererFactory metricRendererFactory;

    private List<MetricUi> metrics = Collections.emptyList();

    @Inject
    public MetricsAdapter(LayoutInflater layoutInflater, MetricRendererFactory metricRendererFactory) {
        this.layoutInflater = layoutInflater;
        this.metricRendererFactory = metricRendererFactory;
        setHasStableIds(true); // @see getItemId()
    }

    @Override
    public MetricRenderer onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(METRIC_LAYOUT_ID, parent, false);
        return metricRendererFactory.createMetricRenderer(view);
    }

    @Override
    public void onBindViewHolder(MetricRenderer holder, int position) {
        MetricUi metric = metrics.get(position);
        holder.render(metric);
    }

    @Override
    public int getItemCount() {
        return metrics.size();
    }

    @Override
    public long getItemId(int position) {
        // Returns the id of the owner, which is the uid of the metric, so that metrics for
        // the same uid can be tracked (by the platform) between updates
        return metrics.get(position).getOwner().getId();
    }

    public void setMetrics(List<MetricUi> metrics) {
        this.metrics = metrics;
        notifyDataSetChanged();
    }
}
