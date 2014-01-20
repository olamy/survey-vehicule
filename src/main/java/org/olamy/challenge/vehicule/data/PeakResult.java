package org.olamy.challenge.vehicule.data;

/**
 * Data model for a peak traffic
 */
public class PeakResult
{
    private int day;

    private long hour;

    private int number;

    public PeakResult( int day, long hour, int number )
    {
        this.day = day;
        this.hour = hour;
        this.number = number;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay( int day )
    {
        this.day = day;
    }

    public long getHour()
    {
        return hour;
    }

    public void setHour( long hour )
    {
        this.hour = hour;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber( int number )
    {
        this.number = number;
    }
}
