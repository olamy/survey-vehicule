package org.olamy.challenge.vehicule.analysis;

/**
 * @author Olivier Lamy
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
