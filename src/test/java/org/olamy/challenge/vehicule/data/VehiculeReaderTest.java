package org.olamy.challenge.vehicule.data;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.core.Condition;
import org.junit.Test;
import org.olamy.challenge.vehicule.DirectionConstants;
import org.olamy.challenge.vehicule.MarkHit;
import org.olamy.challenge.vehicule.NonValidLineException;
import org.olamy.challenge.vehicule.VehiculeHitsReader;
import org.olamy.challenge.vehicule.VehiculeRecord;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.io.File;
import java.util.List;
import java.util.Map;

public class VehiculeReaderTest
{
    @Test
    public void read_small_data()
        throws Exception
    {
        VehiculeRecordDataAccess vehiculeRecordDataAccess = VehiculeHitsReader.read(
            new File( System.getProperty( "basedir", "." ) + "/src/test/small-sample-data.txt" ) );

        List<VehiculeRecord> all = vehiculeRecordDataAccess.getAll();

        Assertions.assertThat( all ).isNotNull().isNotEmpty().hasSize( 8 );

        // control some entries
        Assertions.assertThat( all.get( 0 ).getMarkHits() ).isNotNull().isNotEmpty().hasSize( 2 );
        Assertions.assertThat( all.get( 0 ) ).has( new Condition<VehiculeRecord>()
        {
            @Override
            public boolean matches( VehiculeRecord vehiculeRecord )
            {
                //A98186
                //A98333
                MarkHit first = vehiculeRecord.getMarkHits().get( 0 );
                MarkHit second = vehiculeRecord.getMarkHits().get( 1 );
                return vehiculeRecord.getDay() == 1 && first.getDirection() == DirectionConstants.DIRECTION_A
                    && first.getTimestamp() == 98186 && second.getDirection() == DirectionConstants.DIRECTION_A
                    && second.getTimestamp() == 98333;
            }
        } );

        Assertions.assertThat( all.get( 2 ).getMarkHits() ).isNotNull().isNotEmpty().hasSize( 4 );
        Assertions.assertThat( all.get( 2 ) ).has( new Condition<VehiculeRecord>()
        {
            @Override
            public boolean matches( VehiculeRecord vehiculeRecord )
            {
                //A638379
                //B638382
                //A638520
                //B638523
                MarkHit first = vehiculeRecord.getMarkHits().get( 0 );
                MarkHit second = vehiculeRecord.getMarkHits().get( 1 );
                MarkHit third = vehiculeRecord.getMarkHits().get( 2 );
                MarkHit fourth = vehiculeRecord.getMarkHits().get( 3 );
                return vehiculeRecord.getDay() == 1 && first.getDirection() == DirectionConstants.DIRECTION_A
                    && first.getTimestamp() == 638379 && second.getDirection() == DirectionConstants.DIRECTION_B
                    && second.getTimestamp() == 638382 && third.getDirection() == DirectionConstants.DIRECTION_A
                    && third.getTimestamp() == 638520 && fourth.getDirection() == DirectionConstants.DIRECTION_B
                    && fourth.getTimestamp() == 638523;
            }
        } );

    }

    @Test( expected = NonValidLineException.class )
    public void read_bad_content_expect_exception()
        throws Exception
    {
        VehiculeHitsReader.read( new File( System.getProperty( "basedir", "." ) + "/src/test/crappy-data.txt" ) );
    }

    @Test( expected = NonValidLineException.class )
    public void read_bad_sequence_expect_exception()
        throws Exception
    {
        VehiculeHitsReader.read( new File( System.getProperty( "basedir", "." ) + "/src/test/bad-sequence-data.txt" ) );
    }

    @Test
    public void read_huge_file()
        throws Exception
    {
        VehiculeRecordDataAccess vehiculeRecordDataAccess = VehiculeHitsReader.read( new File(
            System.getProperty( "basedir", "." ) + "/src/test/vehicle-survey-coding-challenge-sample-data.txt" ) );
        System.out.println( "Huge file entries number : " + vehiculeRecordDataAccess.getAll().size() );
    }

    @Test
    public void read_small_data_with_day_change()
        throws Exception
    {
        VehiculeRecordDataAccess vehiculeRecordDataAccess = VehiculeHitsReader.read(
            new File( System.getProperty( "basedir", "." ) + "/src/test/small-sample-data-with-day-change.txt" ) );

        List<VehiculeRecord> all = vehiculeRecordDataAccess.getAll();

        Assertions.assertThat( all ).isNotNull().isNotEmpty().hasSize( 8 );

        //control days
        Assertions.assertThat( all.get( 0 ).getDay() ).isEqualTo( 1 );
        Assertions.assertThat( all.get( 1 ).getDay() ).isEqualTo( 2 );
        Assertions.assertThat( all.get( 2 ).getDay() ).isEqualTo( 3 );
        Assertions.assertThat( all.get( 5 ).getDay() ).isEqualTo( 4 );
    }

    @Test
    public void found_data_between_timestamps_directionA()
        throws Exception
    {
        VehiculeRecordDataAccess vehiculeRecordDataAccess = VehiculeHitsReader.read(
            new File( System.getProperty( "basedir", "." ) + "/src/test/small-sample-data-with-day-change.txt" ) );

        Map<Integer, List<VehiculeRecord>> records =
            vehiculeRecordDataAccess.findVehiculeRecords( DirectionConstants.DIRECTION_A, 4000000, 5000000 );

        Assertions.assertThat( records ).isNotNull().isNotEmpty().hasSize( 3 );

        Assertions.assertThat( vehiculeRecordDataAccess.getDays() ).hasSize( 5 );

        Assertions.assertThat( vehiculeRecordDataAccess.getDirections() ).hasSize( 2 ).contains( 'A', 'B' );

    }

    @Test
    public void not_found_data_between_timestamps()
        throws Exception
    {
        VehiculeRecordDataAccess vehiculeRecordDataAccess = VehiculeHitsReader.read(
            new File( System.getProperty( "basedir", "." ) + "/src/test/small-sample-data-with-day-change.txt" ) );

        Map<Integer, List<VehiculeRecord>> records =
            vehiculeRecordDataAccess.findVehiculeRecords( DirectionConstants.DIRECTION_A, 1000000, 2000000 );

        Assertions.assertThat( records ).isNotNull().isEmpty();

    }

}
