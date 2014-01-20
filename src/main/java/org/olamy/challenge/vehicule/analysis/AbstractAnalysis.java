package org.olamy.challenge.vehicule.analysis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public abstract class AbstractAnalysis
    implements VehiculeRecordAnalysis
{
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat( AnalysisConstants.HOUR_PATTERN );

    protected String formatTime( long time )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set( Calendar.HOUR_OF_DAY, 0 );
        calendar.set( Calendar.MINUTE, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
        return simpleDateFormat.format( new Date( calendar.getTime().getTime() + time ) );
    }
}
