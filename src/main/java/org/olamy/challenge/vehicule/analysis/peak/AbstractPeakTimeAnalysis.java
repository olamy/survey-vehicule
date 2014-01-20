package org.olamy.challenge.vehicule.analysis.peak;

import org.olamy.challenge.vehicule.VehiculeRecord;
import org.olamy.challenge.vehicule.analysis.AbstractAnalysis;
import org.olamy.challenge.vehicule.analysis.AnalysisConstants;
import org.olamy.challenge.vehicule.analysis.VehiculeRecordAnalysis;
import org.olamy.challenge.vehicule.data.PeakResult;
import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class AbstractPeakTimeAnalysis
    extends AbstractAnalysis
    implements VehiculeRecordAnalysis
{

    @Override
    public void displayResult( VehiculeRecordDataAccess vehiculeRecordDataAccess )
    {
        List<Character> directions = vehiculeRecordDataAccess.getDirections();
        long increment = getIncrement();
        for ( Character direction : directions )
        {
            PeakResult peakResult = vehiculeRecordDataAccess.findPeakVolumePeriod( increment, direction );
            System.out.println(
                "Found peak for direction " + direction + ": " + peakResult.getNumber() + ", day " + peakResult.getDay()
                    + " at " + formatTime( peakResult.getHour() ) );
        }

    }


    protected abstract long getIncrement();



}
