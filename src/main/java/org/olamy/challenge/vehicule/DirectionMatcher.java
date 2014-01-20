package org.olamy.challenge.vehicule;

import java.util.List;

/**
 * FIXME we probably need a better data structure for direction
 * to indicate direction B is A then B where A is only A in the list
 * so at least it's delegated to this matcher so easy to enhance
 *
 * @author Olivier Lamy
 */
public interface DirectionMatcher
{
    boolean match( List<MarkHit> markHits );
}
