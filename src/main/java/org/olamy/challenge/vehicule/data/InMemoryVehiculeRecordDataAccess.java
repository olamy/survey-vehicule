package org.olamy.challenge.vehicule.data;

import org.olamy.challenge.vehicule.VehiculeRecord;

import java.util.ArrayList;
import java.util.List;

/**
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
}
