package org.olamy.challenge.vehicule.analysis.speed;

import org.olamy.challenge.vehicule.analysis.AbstractAnalysis;
import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.data.AverageResult;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public abstract class AbstractSpeedPerTimeAverageAnalysis
    extends AbstractAnalysis
    implements VehiculeRecordAnalysis
{

    @Override
    public void displayResult( VehiculeRecordDataAccess vehiculeRecordDataAccess )
    {
        List<Character> directions = vehiculeRecordDataAccess.getDirections();
        long increment = getIncrement();

        System.out.println( "-------------------------------------------------------" );
        System.out.print( "|Hour |" );

        Map<Character, Map<Long, AverageResult>> resultsPerDirection =
            new TreeMap<Character, Map<Long, AverageResult>>();

        for ( Character direction : directions )
        {
            System.out.print( direction + "|" );

            resultsPerDirection.put( direction, vehiculeRecordDataAccess.getSpeedAverages( increment, direction ) );
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
