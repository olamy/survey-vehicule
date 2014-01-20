package org.olamy.challenge.vehicule.data;

import org.olamy.challenge.vehicule.VehiculeRecord;

import java.util.List;

/**
 * @author Olivier Lamy
 */
public interface VehiculeRecordDataAccess
{
    List<VehiculeRecord> getAll();

    void add( VehiculeRecord vehiculeRecord );

}
