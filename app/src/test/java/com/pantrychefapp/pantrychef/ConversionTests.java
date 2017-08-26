package com.pantrychefapp.pantrychef;

import com.pantrychefapp.pantrychef.exceptions.InvalidUnitException;
import com.pantrychefapp.pantrychef.helper.UnitConverter;

import org.junit.Test;

import static com.pantrychefapp.pantrychef.helper.UnitConverter.UnitVolume.*;
import static com.pantrychefapp.pantrychef.helper.UnitConverter.UnitMass.*;
import static org.junit.Assert.*;

public class ConversionTests {
    @Test
    public void testVolumeConversions() {
        UnitConverter converter = new UnitConverter();
        try {
            assertEquals(.51, converter.convertToML(.51, ML), .01);
            assertEquals(510, converter.convertToML(.51, L), .01);
            assertEquals(15.08, converter.convertToML(.51, FLOZ), .01);
            assertEquals(120.66, converter.convertToML(.51, CUP), .01);
            assertEquals(2.51, converter.convertToML(.51, TSP), .01);
            assertEquals(7.54125, converter.convertToML(.51, TBSP), .01);
            assertEquals(60.33, converter.convertToML(.51, GILL), .01);
            assertEquals(241.32, converter.convertToML(.51, PT), .01);
            assertEquals(482.64, converter.convertToML(.51, QT), .01);
            assertEquals(1930.56, converter.convertToML(.51, GAL), .01);

            assertEquals(847.2, converter.convertFromML(847.2, ML), .01);
            assertEquals(0.847, converter.convertFromML(847.2, L), .001);
            assertEquals(28.65, converter.convertFromML(847.2, FLOZ), .01);
            assertEquals(3.58, converter.convertFromML(847.2, CUP), .01);
            assertEquals(171.88, converter.convertFromML(847.2, TSP), .01);
            assertEquals(57.29, converter.convertFromML(847.2, TBSP), .01);
            assertEquals(7.16, converter.convertFromML(847.2, GILL), .01);
            assertEquals(1.79, converter.convertFromML(847.2, PT), .01);
            assertEquals(.89, converter.convertFromML(847.2, QT), .01);
            assertEquals(.224, converter.convertFromML(847.2, GAL), .001);
        } catch (InvalidUnitException e) {
            fail(e.getMessage());
        }
    }

	@Test
	public void testVolumeConversionToInvalidUnit() {
		UnitConverter converter = new UnitConverter();
		try {
			assertEquals(.51, converter.convertToML(.51, null), .01);
			fail("Should have thrown an exception.");
		} catch (InvalidUnitException e) {
			// We should catch an exception.
		}
	}

	@Test
	public void testVolumeConversionFromInvalidUnit() {
		UnitConverter converter = new UnitConverter();
		try {
			assertEquals(.51, converter.convertFromML(.51, null), .01);
			fail("Should have thrown an exception.");
		} catch (InvalidUnitException e) {
			// We should catch an exception.
		}
	}

	@Test
	public void testMassConversion() {
		UnitConverter converter = new UnitConverter();
		try {
			assertEquals(.00051, converter.convertToGrams(.51, MG), .00001);
			assertEquals(.51, converter.convertToGrams(.51, G), .01);
			assertEquals(510, converter.convertToGrams(.51, KG), .01);
			assertEquals(14.45826, converter.convertToGrams(.51, OZ), .01);
			assertEquals(231.3321, converter.convertToGrams(.51, LB), .01);

			assertEquals(847200, converter.convertFromGrams(847.2, MG), .01);
			assertEquals(847.2, converter.convertFromGrams(847.2, G), .01);
			assertEquals(.8472, converter.convertFromGrams(847.2, KG), .0001);
			assertEquals(29.884101, converter.convertFromGrams(847.2, OZ), .01);
			assertEquals(1.8678, converter.convertFromGrams(847.2, LB), .001);
		} catch (InvalidUnitException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testMassConversionToInvalidUnit() {
		UnitConverter converter = new UnitConverter();
		try {
			assertEquals(.51, converter.convertToGrams(.51, null), .01);
			fail("Should have thrown an exception.");
		} catch (InvalidUnitException e) {
			// We should catch an exception.
		}
	}

	@Test
	public void testMassConversionFromInvalidUnit() {
		UnitConverter converter = new UnitConverter();
		try {
			assertEquals(.51, converter.convertFromGrams(.51, null), .01);
			fail("Should have thrown an exception.");
		} catch (InvalidUnitException e) {
			// We should catch an exception.
		}
	}
}
