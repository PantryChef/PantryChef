package com.pantrychefapp.pantrychef.helper;

import com.pantrychefapp.pantrychef.exceptions.InvalidUnitException;

public class UnitConverter {
    // Enumerations
    public enum UnitVolume {ML, L, FLOZ, CUP, TSP, TBSP, GILL, PT, QT, GAL};
    public enum UnitMass {MG, G, KG, OZ, LB}

    // Constants
    private final double CONVERSION_L_TO_ML = 1000;
    private final double CONVERSION_FLOZ_TO_ML = 29.5735;
    private final double CONVERSION_CUP_TO_ML = 236.588;
    private final double CONVERSION_TSP_TO_ML = 4.92892;
    private final double CONVERSION_TBSB_TO_ML = 14.7868;
    private final double CONVERSION_GILL_TO_ML = 118.294;
    private final double CONVERSION_PT_TO_ML = 473.176;
    private final double CONVERSION_QT_TO_ML = 946.353;
    private final double CONVERSION_GAL_TO_ML = 3785.41;
    private final double CONVERSION_MG_TO_G = 0.001;
    private final double CONVERSION_KG_TO_G = 1000;
    private final double CONVERSION_OZ_TO_G = 28.3495;
    private final double CONVERSION_LB_TO_G = 453.592;

    public double convertToML(double amount, UnitVolume unit) throws InvalidUnitException {
        double conversionFactor = getVolumeConversionFactor(unit);
        return amount * conversionFactor;
    }

    public double convertFromML(double amount, UnitVolume unit) throws InvalidUnitException {
        double conversionFactor = getVolumeConversionFactor(unit);
        return amount / conversionFactor;
    }

    public double convertToGrams(double amount, UnitMass unit) throws InvalidUnitException {
        double conversionFactor = getMassConversionFactor(unit);
        return amount * conversionFactor;
    }

    public double convertFromGrams(double amount, UnitMass unit) throws InvalidUnitException {
        double conversionFactor = getMassConversionFactor(unit);
        return amount / conversionFactor;
    }

    private double getVolumeConversionFactor(UnitVolume unit) throws InvalidUnitException {
        if (unit == null) {
            throw new InvalidUnitException("This is not a valid volume unit.");
        }
        switch (unit) {
            case ML:
                return 1;
            case L:
                return CONVERSION_L_TO_ML;
            case FLOZ:
                return CONVERSION_FLOZ_TO_ML;
            case CUP:
                return CONVERSION_CUP_TO_ML;
            case TSP:
                return CONVERSION_TSP_TO_ML;
            case TBSP:
                return CONVERSION_TBSB_TO_ML;
            case GILL:
                return CONVERSION_GILL_TO_ML;
            case PT:
                return CONVERSION_PT_TO_ML;
            case QT:
                return CONVERSION_QT_TO_ML;
            case GAL:
                return CONVERSION_GAL_TO_ML;
            default:
                throw new InvalidUnitException("This is not a valid volume unit.");
        }
    }

    private double getMassConversionFactor(UnitMass unit) throws InvalidUnitException {
        if (unit == null) {
            throw new InvalidUnitException("This is not a valid volume unit.");
        }
        switch (unit) {
            case MG:
                return CONVERSION_MG_TO_G;
            case G:
                return 1;
            case KG:
                return CONVERSION_KG_TO_G;
            case OZ:
                return CONVERSION_OZ_TO_G;
            case LB:
                return CONVERSION_LB_TO_G;
            default:
                throw new InvalidUnitException("This is not a valid mass unit.");
        }
    }
}
