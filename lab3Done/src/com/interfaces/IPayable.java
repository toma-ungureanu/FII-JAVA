package com.interfaces;

/**
 * @author Toma-Florin Ungureanu
 */
public interface IPayable
{
    /**
     * Method to set the fee of the payable object
     *
     * @param fee the fee to be set
     */
    void setFee(int fee);

    /**
     * Function to get the fee of the payable object
     *
     * @return the fee of the payable object
     */
    int getFee();
}
