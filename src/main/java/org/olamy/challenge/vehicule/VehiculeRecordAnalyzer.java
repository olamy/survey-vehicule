package org.olamy.challenge.vehicule;

import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysisComparator;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author Olivier Lamy
 */
public class VehiculeRecordAnalyzer
{

    /**
     * only one argument which is the data file
     *
     * @param args
     */
    public static void main( String[] args )
        throws Exception
    {
        if ( args == null || args.length < 1 )
        {
            String message = "Hey mate, At least one argument with the file path is needed. Try again!";
            System.out.println( message );
            throw new IllegalArgumentException( message );
        }
        File dataFile = new File( args[0] );
        if ( !dataFile.exists() )
        {
            String message = "Hey mate, the path to the file must be to an existing file. Try again!";
            System.out.println( message );
            throw new IllegalArgumentException( message );
        }
        VehiculeRecordDataAccess vehiculeRecordDataAccess = VehiculeHitsReader.read( dataFile );

        ServiceLoader<VehiculeRecordAnalysis> serviceLoader = ServiceLoader.load( VehiculeRecordAnalysis.class );

        List<VehiculeRecordAnalysis> analysisList = new ArrayList<VehiculeRecordAnalysis>();

        Iterator<VehiculeRecordAnalysis> iterator = serviceLoader.iterator();
        while ( iterator.hasNext() )
        {
            analysisList.add( iterator.next() );
        }

        Collections.sort( analysisList, VehiculeRecordAnalysisComparator.INSTANCE );

        System.out.println( "----------------------------------------------------------" );
        System.out.println( "So sit down have a drink and look at the analysis results " );
        System.out.println( "----------------------------------------------------------" );

        for ( VehiculeRecordAnalysis analysis : analysisList )
        {
            System.out.println( "--------------------------------------------------------" );
            System.out.println( "Displaying analysis: " + analysis.getTitle() );
            System.out.println( "--------------------------------------------------------" );
            analysis.displayResult( vehiculeRecordDataAccess );
        }

        System.out.println( "---------------------------------------------------" );
        System.out.println( "That's finished, we hope you got fun!");
        System.out.println( "Was too fast? So try again to finish your drink!!" );
        System.out.println( "---------------------------------------------------" );

    }

}
