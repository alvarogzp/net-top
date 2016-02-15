package org.alvarogp.nettop.metric.domain.model.metric.list;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.metric.transform.comparator.MetricComparator;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.MetricFilter;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.MetricMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MetricList {
    private Metric totalMetric;
    private List<Metric> metrics = new ArrayList<>();

    public Metric getTotalMetric() {
        return totalMetric;
    }

    public List<Metric> getMetrics() {
        return Collections.unmodifiableList(metrics);
    }

    public void setTotalMetric(Metric totalMetric) {
        this.totalMetric = totalMetric;
    }

    public void addMetric(Metric metric) {
        metrics.add(metric);
    }

    public void map(MetricMapper metricMapper) {
        totalMetric = metricMapper.call(totalMetric);
        for (int i = 0; i < metrics.size(); i++) {
            Metric metric = metrics.get(i);
            Metric mappedMetric = metricMapper.call(metric);
            metrics.set(i, mappedMetric);
        }
    }

    public void filter(MetricFilter metricFilter) {
        // totalMetric is never filtered
        for (int i = 0; i < metrics.size(); i++) {
            Metric metric = metrics.get(i);
            boolean keepMetric = metricFilter.call(metric);
            if (!keepMetric) {
                metrics.remove(i);
                i--; // do not advance counter
            }
        }
    }

    public void sort(MetricComparator metricComparator) {
        Collections.sort(metrics, metricComparator);
    }
}
