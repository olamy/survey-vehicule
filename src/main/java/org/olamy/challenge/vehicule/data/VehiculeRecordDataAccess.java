package org.olamy.challenge.vehicule.data;

import org.olamy.challenge.vehicule.VehiculeRecord;

import java.util.List;
import java.util.Map;

/**
 *
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
    Map<Integer, List<VehiculeRecord>> findVehiculeRecords( char direction, long start, long end );

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

    /**
     * to find the peak volume for a period length for a direction
     *
     * @param periodLength
     * @param direction
     * @return
     */
    PeakResult findPeakVolumePeriod( long periodLength, char direction );

    /**
     * to retrieve average number of vehicule
     *
     * @param periodLength
     * @param direction
     * @return Map with entry period start time timestamp since day start, faster access with this key
     */
    Map<Long, AverageResult> getCountAverages( long periodLength, char direction );

    /**
     * to retrieve average speed of vehicules
     *
     * @param periodLength
     * @param direction
     * @return Map with entry period start time timestamp since day start, faster access with this key
     */
    Map<Long, AverageResult> getSpeedAverages( long periodLength, char direction );

    /**
     * to retrieve average distance in meters between vehicules
     *
     * @param periodLength
     * @param direction
     * @return Map with entry period start time timestamp since day start, faster access with this key
     */
    Map<Long, AverageResult> getDistanceAverages( long periodLength, char direction );

}
