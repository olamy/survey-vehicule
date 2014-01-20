package org.olamy.challenge.vehicule;

/**
 *
 */
public class MarkHit
{
    // currently it's only a char
    private char direction;

    private long timestamp;

    public MarkHit( char direction, long timestamp )
    {
        this.direction = direction;
        this.timestamp = timestamp;
    }

    public char getDirection()
    {
        return direction;
    }

    public void setDirection( char direction )
    {
        this.direction = direction;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp( long timestamp )
    {
        this.timestamp = timestamp;
    }


    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "MarkHit{" );
        sb.append( "direction=" ).append( direction );
        sb.append( ", timestamp=" ).append( timestamp );
        sb.append( '}' );
        return sb.toString();
    }
}
