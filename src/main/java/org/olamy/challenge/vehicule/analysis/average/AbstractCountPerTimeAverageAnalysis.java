package org.olamy.challenge.vehicule.analysis.average;

import org.olamy.challenge.vehicule.VehiculeRecord;
import org.olamy.challenge.vehicule.analysis.AbstractAnalysis;
import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.data.AverageResult;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public abstract class AbstractCountPerTimeAverageAnalysis
    extends AbstractAnalysis
    implements VehiculeRecordAnalysis
{

    @Override
    public void displayResult( VehiculeRecordDataAccess vehiculeRecordDataAccess )
    {
        List<Integer> days = vehiculeRecordDataAccess.getDays();
        List<Character> directions = vehiculeRecordDataAccess.getDirections();
        long increment = getIncrement();

        System.out.println( "-------------------------------------------------------" );
        System.out.print( "|Hour |" );

        Map<Character, Map<Long, AverageResult>> resultsPerDirection =
            new TreeMap<Character, Map<Long, AverageResult>>();

        for ( Character direction : directions )
        {
            System.out.print( direction + "|" );

            resultsPerDirection.put( direction, vehiculeRecordDataAccess.getAverages( increment, direction ) );
        }

        for ( long currentTime = 0; currentTime < AnalysisConstants.DAYS_MILLIS; currentTime += increment )
        {
            System.out.println( "" );

            System.out.print( "| " + formatTime( currentTime ) + "  |" );

            for ( Character direction : directions )
            {
                AverageResult averageResult = resultsPerDirection.get( direction ).get( Long.valueOf( currentTime ) );

                System.out.print( "" + averageResult.getAverage() + "|" );
            }
            System.out.println( "" );

            System.out.println( "-------------------------------------------------------" );
        }

    }

    protected abstract long getIncrement();

}
