package org.olamy.challenge.vehicule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class VehiculeRecord
{
    private int day;

    private List<MarkHit> markHits;

    public VehiculeRecord( int day )
    {
        this.day = day;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay( int day )
    {
        this.day = day;
    }

    /**
     *
     * @return never return <code>null</code>
     */
    public List<MarkHit> getMarkHits()
    {
        return markHits == null ? Collections.<MarkHit>emptyList():markHits;
    }

    public void setMarkHits( List<MarkHit> markHits )
    {
        this.markHits = markHits;
    }

    public VehiculeRecord addMarkHit( MarkHit markHit )
    {
        if ( this.markHits == null )
        {
            this.markHits = new ArrayList<MarkHit>();
        }
        this.markHits.add( markHit );
        return this;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "VehiculeRecord{" );
        sb.append( "day=" ).append( day );
        sb.append( ", markHits=" ).append( markHits );
        sb.append( '}' );
        return sb.toString();
    }
}
