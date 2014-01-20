package org.olamy.challenge.vehicule.analysis;

import java.util.Comparator;

/**
 * @author Olivier Lamy
 */
public class VehiculeRecordAnalysisComparator
    implements Comparator<VehiculeRecordAnalysis>
{

    public static final VehiculeRecordAnalysisComparator INSTANCE = new VehiculeRecordAnalysisComparator();

    @Override
    public int compare( VehiculeRecordAnalysis vehiculeRecordAnalysis, VehiculeRecordAnalysis vehiculeRecordAnalysis2 )
    {
        return Integer.valueOf( vehiculeRecordAnalysis.getOrder() ).compareTo(
            Integer.valueOf( vehiculeRecordAnalysis2.getOrder() ) );
    }
}
