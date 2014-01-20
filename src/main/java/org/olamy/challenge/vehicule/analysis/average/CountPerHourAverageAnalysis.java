package org.olamy.challenge.vehicule.analysis.average;

import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.average.AbstractCountPerTimeAverageAnalysis;

/**
 *
 */
public class CountPerHourAverageAnalysis
    extends AbstractCountPerTimeAverageAnalysis
{
    @Override
    public String getTitle()
    {
        return "Average per hour/Per direction for all days";
    }

    @Override
    public int getOrder()
    {
        return 10;
    }

    @Override
    protected long getIncrement()
    {
        return AnalysisConstants.MILLIS_PER_HOUR;
    }
}
