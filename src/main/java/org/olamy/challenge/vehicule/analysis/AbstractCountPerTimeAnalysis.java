package org.olamy.challenge.vehicule.analysis;

import org.olamy.challenge.vehicule.VehiculeRecord;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Olivier Lamy
 */
public abstract class AbstractCountPerTimeAnalysis
    implements VehiculeRecordAnalysis
{

    @Override
    public void displayResult( VehiculeRecordDataAccess vehiculeRecordDataAccess )
    {
        List<Integer> days = vehiculeRecordDataAccess.getDays();
        List<Character> directions = vehiculeRecordDataAccess.getDirections();
        long increment = getIncrement();
        for ( Integer day : days )
        {
            System.out.println( "-------------------------------------------------------" );
            System.out.println( "Day " + day );
            System.out.println( "-------------------------------------------------------" );
            System.out.print( "|Hour |" );

            for ( Character direction : directions )
            {
                System.out.print( direction + "|" );
            }

            System.out.println( "" );
            for ( long currentTime = 0; currentTime < AnalysisConstants.DAYS_MILLIS; currentTime += increment )
            {
                System.out.print( "| " + formatTime( currentTime ) + "  |" );

                for ( Character direction : directions )
                {
                    // -1 because we want to stop just before next increment
                    Map<Integer, List<VehiculeRecord>> found =
                        vehiculeRecordDataAccess.findVehicules( direction, currentTime, currentTime + increment - 1 );
                    List<VehiculeRecord> vehiculeRecords = found.get( day );
                    int number = vehiculeRecords == null ? 0 : vehiculeRecords.size();
                    System.out.print( "" + number + "|" );

                }
                System.out.println( "" );
            }
            System.out.println( "-------------------------------------------------------" );

        }

    }

    protected abstract long getIncrement();

    protected abstract String formatTime( long time );

}
