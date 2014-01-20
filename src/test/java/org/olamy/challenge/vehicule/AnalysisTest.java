package org.olamy.challenge.vehicule;

import org.junit.Test;
import org.olamy.challenge.vehicule.analysis.DirectionTotalCountAnalysis;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.io.File;

/**
 * @author Olivier Lamy
 */
public class AnalysisTest
{
    @Test
    public void run_all_with_huge_data()
        throws Exception
    {

        VehiculeRecordAnalyzer.main( new String[]{
            System.getProperty( "basedir", "." ) + "/src/test/vehicle-survey-coding-challenge-sample-data.txt" } );

    }

    @Test
    public void run_vehicules_count_with_small_data()
        throws Exception
    {
        VehiculeRecordDataAccess vehiculeRecordDataAccess = VehiculeHitsReader.read(
            new File( System.getProperty( "basedir", "." ) + "/src/test/small-sample-data-with-day-change.txt" ) );

        DirectionTotalCountAnalysis directionCountAnalysis = new DirectionTotalCountAnalysis();

        directionCountAnalysis.displayResult( vehiculeRecordDataAccess );

    }

    @Test( expected = IllegalArgumentException.class )
    public void run_all_with_non_valid_file_path()
        throws Exception
    {

        VehiculeRecordAnalyzer.main( new String[]{ System.getProperty( "basedir", "." ) + "/src/beer.txt" } );

    }

    @Test( expected = IllegalArgumentException.class )
    public void run_all_with_empty_args()
        throws Exception
    {

        VehiculeRecordAnalyzer.main( new String[]{ } );

    }

    @Test( expected = IllegalArgumentException.class )
    public void run_all_with_null_args()
        throws Exception
    {

        VehiculeRecordAnalyzer.main( null );

    }

}
