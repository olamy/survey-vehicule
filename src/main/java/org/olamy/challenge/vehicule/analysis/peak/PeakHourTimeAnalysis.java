package org.olamy.challenge.vehicule.analysis.peak;

import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.peak.AbstractPeakTimeAnalysis;

/**
 * @author Olivier Lamy
 */
public class PeakHourTimeAnalysis
    extends AbstractPeakTimeAnalysis
{
    @Override
    public String getTitle()
    {
        return "Found hour peak time";
    }

    @Override
    public int getOrder()
    {
        return 15;
    }

    @Override
    protected long getIncrement()
    {
        return AnalysisConstants.MILLIS_PER_HOUR;
    }
}
