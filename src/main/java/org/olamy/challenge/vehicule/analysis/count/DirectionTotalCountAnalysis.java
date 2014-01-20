package org.olamy.challenge.vehicule.analysis.count;

import org.olamy.challenge.vehicule.VehiculeRecord;
import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class DirectionTotalCountAnalysis
    implements VehiculeRecordAnalysis
{


    @Override
    public String getTitle()
    {
        return "Display per direction count";
    }

    @Override
    public int getOrder()
    {
        return 0;
    }

    @Override
    public void displayResult( VehiculeRecordDataAccess vehiculeRecordDataAccess )
    {
        System.out.println( "----------------------------------------" );
        System.out.println( "Start displaying total vehicules count" );
        System.out.println( "----------------------------------------" );
        List<Integer> days = vehiculeRecordDataAccess.getDays();
        List<Character> directions = vehiculeRecordDataAccess.getDirections();

        for ( Character direction : directions )
        {

            // morning first and evening
            Map<Integer, List<VehiculeRecord>> recordMorningPerDay =
                vehiculeRecordDataAccess.findVehiculeRecords( direction, 0, AnalysisConstants.HALF_DAYS_MILLIS );
            Map<Integer, List<VehiculeRecord>> recordEveningPerDay =
                vehiculeRecordDataAccess.findVehiculeRecords( direction, AnalysisConstants.HALF_DAYS_MILLIS,
                                                              AnalysisConstants.DAYS_MILLIS );

            System.out.println( "----------------------------------" );
            System.out.println( "Vehicules direction " + direction );
            System.out.println( "----------------------------------" );
            System.out.println( "---------------------------------------" );
            System.out.println( "|Day |  Morning Count | Evening Count |" );
            System.out.println( "---------------------------------------" );

            for ( Integer day : days )
            {
                List<VehiculeRecord> morning = recordMorningPerDay.get( day );
                int morningCount = morning == null ? 0 : morning.size();
                List<VehiculeRecord> evening = recordEveningPerDay.get( day );
                int eveningCount = evening == null ? 0 : evening.size();
                // FIXME spaces number must depend on result number size
                System.out.println(
                    "| " + day.toString() + "  |        " + morningCount + "       |       " + eveningCount
                        + "       |" );
            }

            System.out.println( "---------------------------------------" );
            System.out.println( "" );

        }


    }


}
