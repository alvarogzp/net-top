package org.alvarogp.nettop.metric.data.android.metric;

import android.net.TrafficStats;

import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TrafficInfo {
    @Inject
    public TrafficInfo() {}

    public long getRxValue(int uid) {
        return TrafficStats.getUidRxBytes(uid);
    }

    public long getTxValue(int uid) {
        return TrafficStats.getUidTxBytes(uid);
    }

    public long getTotalRxValue() {
        return TrafficStats.getTotalRxBytes();
    }

    public long getTotalTxValue() {
        return TrafficStats.getTotalTxBytes();
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public MetricUnit getUnit() {
        return MetricUnit.BYTES_UNIT;
    }
}
