package edu.remad.learnmustangxrechnung.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.mustangproject.ZUGFeRD.IZUGFeRDPaymentDiscountTerms;

public class FourWeeksTutoringPaymentDiscountTerms implements IZUGFeRDPaymentDiscountTerms {

	@Override
	public BigDecimal getCalculationPercentage() {
		return new BigDecimal(0);
	}

	@Override
	public Date getBaseDate() {
		return Date.from(LocalDateTime.now().plusDays(30L).atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public int getBasePeriodMeasure() {
		return 0;
	}

	@Override
	public String getBasePeriodUnitCode() {
		return "d";
	}

}
