package org.olamy.challenge.vehicule.analysis.count;

import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;

/**
 *
 */
public class VehiculePerQuarterHourCountAnalysis
    extends AbstractCountPerTimeAnalysis
    implements VehiculeRecordAnalysis
{

    @Override
    public String getTitle()
    {
        return "Per quarter hour/Per direction for all days";
    }

    @Override
    public int getOrder()
    {
        return 7;
    }


    @Override
    protected long getIncrement()
    {
        return AnalysisConstants.MILLIS_PER_HOUR / 4;
    }

}
