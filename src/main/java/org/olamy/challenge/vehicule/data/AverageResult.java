package org.olamy.challenge.vehicule.data;

import java.math.BigDecimal;

/**
 * Data model for an average result (will be used for average count and speed)
 */
public class AverageResult
{

    private long hour;

    private float average;

    public AverageResult( long hour, float average )
    {
        this.hour = hour;
        this.average = average;
    }

    public long getHour()
    {
        return hour;
    }

    public void setHour( long hour )
    {
        this.hour = hour;
    }

    public float getAverage()
    {
        return average;
    }

    public void setAverage( float average )
    {
        this.average = average;
    }
}
