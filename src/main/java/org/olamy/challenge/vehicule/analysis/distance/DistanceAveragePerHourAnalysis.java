package org.olamy.challenge.vehicule.analysis.distance;

import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.analysis.speed.AbstractSpeedPerTimeAverageAnalysis;

/**
 *
 */
public class DistanceAveragePerHourAnalysis
    extends AbstractDistanceAveragePerTimeAnalysis
    implements VehiculeRecordAnalysis
{

    @Override
    protected long getIncrement()
    {
        return AnalysisConstants.MILLIS_PER_HOUR;
    }

    @Override
    public String getTitle()
    {
        return "Distance between vehicule in meters per hour/per direction";
    }

    @Override
    public int getOrder()
    {
        return 60;
    }
}
