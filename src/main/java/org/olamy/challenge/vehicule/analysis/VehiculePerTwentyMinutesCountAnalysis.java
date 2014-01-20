package org.olamy.challenge.vehicule.analysis;

/**
 * @author Olivier Lamy
 */
public class VehiculePerTwentyMinutesCountAnalysis
    extends AbstractCountPerTimeAnalysis
    implements VehiculeRecordAnalysis
{

    @Override
    public String getTitle()
    {
        return "Per 20 minutes/Per direction for all days";
    }

    @Override
    public int getOrder()
    {
        return 4;
    }


    @Override
    protected long getIncrement()
    {
        return AnalysisConstants.MILLIS_PER_HOUR / 3;
    }

}
