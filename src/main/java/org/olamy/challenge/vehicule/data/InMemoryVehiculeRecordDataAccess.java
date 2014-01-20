package org.olamy.challenge.vehicule.data;

import org.olamy.challenge.vehicule.DirectionConstants;
import org.olamy.challenge.vehicule.DirectionMatcherBuilder;
import org.olamy.challenge.vehicule.MarkHit;
import org.olamy.challenge.vehicule.VehiculeRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * So it's in memory but some data access performance could be improved with some indexing
 * but later :-)
 *
 * @author Olivier Lamy
 */
public class InMemoryVehiculeRecordDataAccess
    implements VehiculeRecordDataAccess
{

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
    public Map<Integer, List<VehiculeRecord>> findVehicules( char direction, long start, long end )
    {
        Map<Integer, List<VehiculeRecord>> found = new HashMap<Integer, List<VehiculeRecord>>();

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
}
