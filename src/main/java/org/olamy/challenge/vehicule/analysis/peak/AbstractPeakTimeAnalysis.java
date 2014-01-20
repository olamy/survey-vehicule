package org.olamy.challenge.vehicule.analysis.peak;

import org.olamy.challenge.vehicule.VehiculeRecord;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.data.PeakResult;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class AbstractPeakTimeAnalysis
    implements VehiculeRecordAnalysis
{
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm" );


    @Override
    public void displayResult( VehiculeRecordDataAccess vehiculeRecordDataAccess )
    {
        List<Character> directions = vehiculeRecordDataAccess.getDirections();
        long increment = getIncrement();
        for ( Character direction : directions )
        {
            PeakResult peakResult = vehiculeRecordDataAccess.findPeakVolumePeriod( increment, direction );
            System.out.println(
                "Found peak for direction " + direction + ": " + peakResult.getNumber() + ", day " + peakResult.getDay()
                    + " at " + formatTime( peakResult.getHour() ) );
        }

    }


    protected abstract long getIncrement();

    protected String formatTime( long time )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set( Calendar.HOUR_OF_DAY, 0 );
        calendar.set( Calendar.MINUTE, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
        return simpleDateFormat.format( new Date( calendar.getTime().getTime() + time ) );
    }

}
