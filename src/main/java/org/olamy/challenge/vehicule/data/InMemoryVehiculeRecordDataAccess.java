package org.olamy.challenge.vehicule.data;

import org.olamy.challenge.vehicule.DirectionMatcherBuilder;
import org.olamy.challenge.vehicule.MarkHit;
import org.olamy.challenge.vehicule.VehiculeRecord;
import org.olamy.challenge.vehicule.analysis.AnalysisConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * So it's in memory but some data access performance could be improved with some indexing
 * but later :-)
 */
public class InMemoryVehiculeRecordDataAccess
    implements VehiculeRecordDataAccess
{

    private static final float WHEEL_BASE_SIZE = (float) 2.5;

    private List<VehiculeRecord> vehiculeRecords = new ArrayList<VehiculeRecord>();

    @Override
    public List<VehiculeRecord> getAll()
    {
        return vehiculeRecords;
    }

    @Override
    public void add( VehiculeRecord vehiculeRecord )
    {
        this.vehiculeRecords.add( vehiculeRecord );
    }

    @Override
    public Map<Integer, List<VehiculeRecord>> findVehiculeRecords( char direction, long start, long end )
    {
        Map<Integer, List<VehiculeRecord>> found = new TreeMap<Integer, List<VehiculeRecord>>();

        for ( VehiculeRecord vehiculeRecord : vehiculeRecords )
        {
            if ( !vehiculeRecord.getMarkHits().isEmpty() )
            {
                List<MarkHit> markHits = vehiculeRecord.getMarkHits();
                long firstTimestamp = markHits.get( 0 ).getTimestamp();

                if ( DirectionMatcherBuilder.build( direction ).match( markHits ) //
                    && ( firstTimestamp >= start && firstTimestamp < end ) )
                {
                    Integer day = Integer.valueOf( vehiculeRecord.getDay() );
                    List<VehiculeRecord> foundRecords = found.get( day );
                    if ( foundRecords == null )
                    {
                        foundRecords = new ArrayList<VehiculeRecord>();
                    }
                    foundRecords.add( vehiculeRecord );
                    found.put( day, foundRecords );
                }
            }
        }

        return found;
    }

    @Override
    public List<Integer> getDays()
    {
        Set<Integer> days = new HashSet<Integer>();
        for ( VehiculeRecord vehiculeRecord : vehiculeRecords )
        {
            Integer day = Integer.valueOf( vehiculeRecord.getDay() );
            if ( !days.contains( day ) )
            {
                days.add( day );
            }
        }

        return new ArrayList<Integer>( days );
    }

    @Override
    public List<Character> getDirections()
    {
        Set<Character> directions = new HashSet<Character>();
        for ( VehiculeRecord vehiculeRecord : vehiculeRecords )
        {
            for ( MarkHit markHit : vehiculeRecord.getMarkHits() )
            {
                Character direction = Character.valueOf( markHit.getDirection() );
                if ( !directions.contains( direction ) )
                {
                    directions.add( direction );
                }
            }
        }

        return new ArrayList<Character>( directions );
    }

    @Override
    public PeakResult findPeakVolumePeriod( long periodLength, char direction )
    {
        PeakResult peakResult = new PeakResult( 1, 0, 0 );
        for ( Integer day : getDays() )
        {
            for ( long currentTime = 0; currentTime < AnalysisConstants.DAYS_MILLIS; currentTime += periodLength )
            {
                // -1 because we want to stop just before next increment
                Map<Integer, List<VehiculeRecord>> found =
                    findVehiculeRecords( direction, currentTime, currentTime + periodLength - 1 );
                List<VehiculeRecord> vehiculeRecords = found.get( day );
                int number = vehiculeRecords == null ? 0 : vehiculeRecords.size();
                if ( number > peakResult.getNumber() )
                {
                    peakResult = new PeakResult( vehiculeRecords.get( 0 ).getDay(), currentTime, number );
                }
            }
        }

        return peakResult;

    }

    @Override
    public Map<Long, AverageResult> getCountAverages( long periodLength, char direction )
    {
        Map<Long, AverageResult> averageResults = new TreeMap<Long, AverageResult>();

        int days = getDays().size();

        for ( long currentTime = 0; currentTime < AnalysisConstants.DAYS_MILLIS; currentTime += periodLength )
        {
            // -1 because we want to stop just before next increment
            Map<Integer, List<VehiculeRecord>> found =
                findVehiculeRecords( direction, currentTime, currentTime + periodLength - 1 );
            long sum = 0;
            for ( Map.Entry<Integer, List<VehiculeRecord>> entry : found.entrySet() )
            {
                sum += entry.getValue() == null ? 0 : entry.getValue().size();
            }
            // we assume we have days :-) so no check of 0 divide
            averageResults.put( Long.valueOf( currentTime ), new AverageResult( currentTime, sum / days ) );

        }
        return averageResults;
    }

    @Override
    public Map<Long, AverageResult> getSpeedAverages( long periodLength, char direction )
    {
        Map<Long, AverageResult> averageResults = new TreeMap<Long, AverageResult>();

        for ( long currentTime = 0; currentTime < AnalysisConstants.DAYS_MILLIS; currentTime += periodLength )
        {
            // -1 because we want to stop just before next increment
            Map<Integer, List<VehiculeRecord>> found =
                findVehiculeRecords( direction, currentTime, currentTime + periodLength - 1 );
            long sum = 0;
            int speedEntriesNumber = 0;
            for ( Map.Entry<Integer, List<VehiculeRecord>> entry : found.entrySet() )
            {
                // adding speed for all VehiculeRecord
                for ( VehiculeRecord vehiculeRecord : entry.getValue() )
                {
                    long time = findTimeBetweenTwoHits( vehiculeRecord );

                    sum += ( ( WHEEL_BASE_SIZE / 1000 ) * AnalysisConstants.MILLIS_PER_HOUR ) / time;

                    speedEntriesNumber++;
                }
            }
            // we assume we have days :-) so no check
            averageResults.put( Long.valueOf( currentTime ),
                                new AverageResult( currentTime, sum / speedEntriesNumber ) );

        }
        return averageResults;
    }

    /**
     * we make it easy and just search the time between two hits of the same direction
     *
     * @param vehiculeRecord
     * @return
     */
    private long findTimeBetweenTwoHits( VehiculeRecord vehiculeRecord )
    {
        MarkHit firstMarkit = null;
        long time = 0;
        for ( MarkHit markHit : vehiculeRecord.getMarkHits() )
        {
            if ( firstMarkit == null )
            {
                firstMarkit = markHit;
            }
            else if ( markHit.getDirection() == firstMarkit.getDirection() )
            {
                return markHit.getTimestamp() - firstMarkit.getTimestamp();
            }

        }
        return time;
    }
}
