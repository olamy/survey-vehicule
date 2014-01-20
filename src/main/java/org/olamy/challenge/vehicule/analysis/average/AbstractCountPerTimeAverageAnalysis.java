package org.olamy.challenge.vehicule.analysis.average;

import org.olamy.challenge.vehicule.VehiculeRecord;
import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class AbstractCountPerTimeAverageAnalysis
    implements VehiculeRecordAnalysis
{
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm" );

    @Override
    public void displayResult( VehiculeRecordDataAccess vehiculeRecordDataAccess )
    {
        List<Integer> days = vehiculeRecordDataAccess.getDays();
        List<Character> directions = vehiculeRecordDataAccess.getDirections();
        long increment = getIncrement();

        System.out.println( "-------------------------------------------------------" );
        System.out.print( "|Hour |" );

        for ( Character direction : directions )
        {
            System.out.print( direction + "|" );
        }
        for ( long currentTime = 0; currentTime < AnalysisConstants.DAYS_MILLIS; currentTime += increment )
        {
            System.out.println( "" );

            System.out.print( "| " + formatTime( currentTime ) + "  |" );

            for ( Character direction : directions )
            {
                // -1 because we want to stop just before next increment
                Map<Integer, List<VehiculeRecord>> found =
                    vehiculeRecordDataAccess.findVehiculeRecords( direction, currentTime, currentTime + increment - 1 );
                long sum = 0;
                for ( Map.Entry<Integer, List<VehiculeRecord>> entry : found.entrySet() )
                {
                    sum += ( entry.getValue() == null ? 0 : entry.getValue().size() );
                }

                System.out.print( "" + ( sum / days.size() ) + "|" );

            }
            System.out.println( "" );

            System.out.println( "-------------------------------------------------------" );
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
