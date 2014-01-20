package org.olamy.challenge.vehicule;

import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ServiceLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * In case of non valid line the whole file won't be read
 * with an exception {@link org.olamy.challenge.vehicule.NonValidLineException}
 * <p/>
 * TODO vehicule hits reader can be an interface too with various implementations
 *
 * @author Olivier Lamy
 */
public class VehiculeHitsReader
{

    public static VehiculeRecordDataAccess read( File file )
        throws IOException, NonValidLineException
    {
        // we assume there is only one implementation
        // TODO control there is only one
        ServiceLoader<VehiculeRecordDataAccess> serviceLoader = ServiceLoader.load( VehiculeRecordDataAccess.class );
        VehiculeRecordDataAccess vehiculeRecordDataAccess = serviceLoader.iterator().next();

        // we assume this pattern (could be configurable)
        Pattern linePattern = Pattern.compile( "([a-zA-Z])(\\d+)" );

        String line = "";
        long lineNumber = 1;
        // we start days @ 1
        int day = 1;

        FileReader fileReader = null;
        BufferedReader br = null;
        try
        {
            fileReader = new FileReader( file );
            br = new BufferedReader( fileReader );

            // track the last one to control direction and create a new one or not
            // and change the day
            VehiculeRecord vehiculeRecord = null;
            // to keep track of changes
            MarkHit lastMarkHit = null;

            while ( ( line = br.readLine() ) != null )
            {
                if ( line == null || line.isEmpty() )
                {
                    continue;
                }
                Matcher matcher = linePattern.matcher( line );
                if ( matcher.matches() )
                {
                    long time = Long.parseLong( matcher.group( 2 ) );
                    char direction = matcher.group( 1 ).toUpperCase( Locale.getDefault() ).charAt( 0 );
                    if ( direction != DirectionConstants.DIRECTION_A && direction != DirectionConstants.DIRECTION_B )
                    {
                        throw new NonValidLineException(
                            "line " + lineNumber + " contains non valid value for direction'" + line + "'",
                            lineNumber );
                    }

                    MarkHit markHit = new MarkHit( direction, time );

                    // control data to change or not Vehicule record entry
                    // we strictly follow specs and assume we have only 2 directions see constants
                    // and with the possible order AA or ABAB and a maximum of 4 hits!

                    if ( vehiculeRecord == null || ( vehiculeRecord.getMarkHits().size() == 4 ) )
                    {
                        // check if any day change
                        if ( lastMarkHit != null && markHit.getTimestamp() < lastMarkHit.getTimestamp() )
                        {
                            // we are sure here to have a VehiculeRecord so no need of null check
                            day++;
                        }
                        vehiculeRecord = new VehiculeRecord( day ).addMarkHit( markHit );
                        lastMarkHit = markHit;
                    }
                    else
                    {
                        if ( lastMarkHit == null )
                        {
                            // so that's the first record no more control
                            vehiculeRecord.addMarkHit( markHit );
                        }
                        else
                        {
                            if ( lastMarkHit.getDirection() == markHit.getDirection()
                                && markHit.getDirection() == DirectionConstants.DIRECTION_A )
                            {
                                // we have the AA sequence so record this vehicule and reset it for next vehicule
                                vehiculeRecord.addMarkHit( markHit );
                                vehiculeRecord = null;
                                lastMarkHit = markHit;
                                continue;
                            }
                            else if ( ( lastMarkHit.getDirection() == DirectionConstants.DIRECTION_A
                                && markHit.getDirection() == DirectionConstants.DIRECTION_B ) || (
                                lastMarkHit.getDirection() == DirectionConstants.DIRECTION_B
                                    && markHit.getDirection() == DirectionConstants.DIRECTION_A ) )
                            {
                                lastMarkHit = markHit;
                                vehiculeRecord.addMarkHit( markHit );
                                continue;
                            }
                            else
                            {
                                // non valid values here
                                throw new NonValidLineException(
                                    "line " + lineNumber + " contains non valid sequence '" + line + "'", lineNumber );
                            }
                        }
                    }
                    vehiculeRecordDataAccess.add( vehiculeRecord );
                }
                else
                {
                    throw new NonValidLineException( "line " + lineNumber + " contains non valid value '" + line + "'",
                                                     lineNumber );
                }
                lineNumber++;
            }
        }
        catch ( NumberFormatException e )
        {
            throw new NonValidLineException( "line " + lineNumber + " contains non valid value for time'" + line + "'",
                                             lineNumber );
        }
        finally
        {
            closeQuietly( br );
            closeQuietly( fileReader );
        }

        return vehiculeRecordDataAccess;
    }

    private static void closeQuietly( Closeable closeable )
    {
        if ( closeable != null )
        {
            try
            {
                closeable.close();
            }
            catch ( IOException e )
            {
                // ignore issue when closing
            }
        }
    }

}
