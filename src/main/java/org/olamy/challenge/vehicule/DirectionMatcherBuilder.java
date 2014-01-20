package org.olamy.challenge.vehicule;

import java.util.List;

/**
 *
 */
public class DirectionMatcherBuilder
{

    private static final DirectionMatcher A_MATCHER = new DirectionMatcher()
    {
        @Override
        public boolean match( List<MarkHit> markHits )
        {
            return markHits.size() == 2 && markHits.get( 0 ).getDirection() == DirectionConstants.DIRECTION_A &&
                markHits.get( 1 ).getDirection() == DirectionConstants.DIRECTION_A;
        }
    };

    private static final DirectionMatcher B_MATCHER = new DirectionMatcher()
    {
        @Override
        public boolean match( List<MarkHit> markHits )
        {
            return markHits.size() == 4 && markHits.get( 0 ).getDirection() == DirectionConstants.DIRECTION_A &&
                markHits.get( 1 ).getDirection() == DirectionConstants.DIRECTION_B;
        }
    };

    public static DirectionMatcher build( char direction )
    {
        switch ( direction )
        {
            case DirectionConstants.DIRECTION_A:
                return A_MATCHER;
            case DirectionConstants.DIRECTION_B:
                return B_MATCHER;
            default:
                throw new IllegalArgumentException( "no available direction matcher for '" + direction + "'" );
        }
    }

}
