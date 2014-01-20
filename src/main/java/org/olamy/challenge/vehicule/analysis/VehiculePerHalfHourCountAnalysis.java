package org.olamy.challenge.vehicule.analysis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

}
