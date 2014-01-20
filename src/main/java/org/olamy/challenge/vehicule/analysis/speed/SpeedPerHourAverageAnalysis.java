package org.olamy.challenge.vehicule.analysis.speed;

import org.olamy.challenge.vehicule.analysis.AbstractAnalysis;
import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.data.AverageResult;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class SpeedPerHourAverageAnalysis
    extends AbstractSpeedPerTimeAverageAnalysis
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
        return "Average speed per hour/per direction";
    }

    @Override
    public int getOrder()
    {
        return 50;
    }
}
