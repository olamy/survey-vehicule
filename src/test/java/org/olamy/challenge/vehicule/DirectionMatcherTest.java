package org.olamy.challenge.vehicule;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Olivier Lamy
 */
public class DirectionMatcherTest
{
    @Test
    public void test_direction_a_match()
    {
        List<MarkHit> list = Arrays.asList( new MarkHit( DirectionConstants.DIRECTION_A, 0 ),
                                            new MarkHit( DirectionConstants.DIRECTION_A, 1 ) );

        Assert.assertTrue( DirectionMatcherBuilder.build( DirectionConstants.DIRECTION_A ).match( list ) );
        Assert.assertFalse( DirectionMatcherBuilder.build( DirectionConstants.DIRECTION_B ).match( list ) );
    }

    @Test
    public void test_direction_b_match()
    {
        List<MarkHit> list = Arrays.asList( new MarkHit( DirectionConstants.DIRECTION_A, 0 ),
                                            new MarkHit( DirectionConstants.DIRECTION_B, 1 ),
                                            new MarkHit( DirectionConstants.DIRECTION_A, 2 ),
                                            new MarkHit( DirectionConstants.DIRECTION_B, 3 ) );

        Assert.assertFalse( DirectionMatcherBuilder.build( DirectionConstants.DIRECTION_A ).match( list ) );
        Assert.assertTrue( DirectionMatcherBuilder.build( DirectionConstants.DIRECTION_B ).match( list ) );

    }

    @Test
    public void test_direction_a_b_not_match()
    {
        List<MarkHit> list = Arrays.asList( new MarkHit( DirectionConstants.DIRECTION_A, 0 ),
                                            new MarkHit( DirectionConstants.DIRECTION_B, 1 ) );

        Assert.assertFalse( DirectionMatcherBuilder.build( DirectionConstants.DIRECTION_A ).match( list ) );
        Assert.assertFalse( DirectionMatcherBuilder.build( DirectionConstants.DIRECTION_B ).match( list ) );
    }
}
