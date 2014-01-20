package org.olamy.challenge.vehicule.analysis;

import org.olamy.challenge.vehicule.data.VehiculeRecordDataAccess;

/**
 *
 */
public interface VehiculeRecordAnalysis
{

    /**
     * small description of what this analysis is doing
     * @return
     */
    String getTitle();

    /**
     * return an order when all analysis are process
     * @return
     */
    int getOrder();

    /**
     * display the analysis result in the console
     * @param vehiculeRecordDataAccess
     */
    void displayResult( VehiculeRecordDataAccess vehiculeRecordDataAccess );

}
