package org.olamy.challenge.vehicule.analysis;

/**
 * @author Olivier Lamy
 */
public class VehiculePerHalfHourCountAnalysis
    extends AbstractCountPerTimeAnalysis
    implements VehiculeRecordAnalysis
{


    @Override
    public String getTitle()
    {
        return "Per half hour/Per direction for all days";
    }

    @Override
    public int getOrder()
    {
        return 2;
    }


    @Override
    protected long getIncrement()
    {
        return AnalysisConstants.MILLIS_PER_HOUR / 2;
    }

    @Override
    protected String formatTime( long time )
    {
        return Long.toString( time / AnalysisConstants.MILLIS_PER_HOUR );
    }
}
