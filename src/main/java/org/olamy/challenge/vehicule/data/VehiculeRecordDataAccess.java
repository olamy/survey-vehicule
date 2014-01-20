package org.olamy.challenge.vehicule.data;

import org.olamy.challenge.vehicule.VehiculeRecord;

import java.util.List;
import java.util.Map;

/**
 * @author Olivier Lamy
 */
public interface VehiculeRecordDataAccess
{
    List<VehiculeRecord> getAll();

    void add( VehiculeRecord vehiculeRecord );

    /**
     * find vehicules starting using a direction between two timestamp per day
     * we check only the first markHit
     *
     * @param direction
     * @param start
     * @param end
     * @return
     */
    Map<Integer, List<VehiculeRecord>> findVehicules( char direction, long start, long end );

    /**
     * return days with record (days start @ 1)
     *
     * @return
     */
    List<Integer> getDays();

    /**
     * return directions available in the data
     *
     * @return
     */
    List<Character> getDirections();

}
