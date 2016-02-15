package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

import rx.functions.Func1;

public interface MetricMapper extends Func1<Metric, Metric> {}
