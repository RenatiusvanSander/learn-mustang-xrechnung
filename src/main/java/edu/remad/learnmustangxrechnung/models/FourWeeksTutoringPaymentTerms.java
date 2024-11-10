package edu.remad.learnmustangxrechnung.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.mustangproject.ZUGFeRD.IZUGFeRDPaymentDiscountTerms;
import org.mustangproject.ZUGFeRD.IZUGFeRDPaymentTerms;

public class FourWeeksTutoringPaymentTerms implements IZUGFeRDPaymentTerms {

	@Override
	public String getDescription() {
		return "Sie zahlen Ihre Tutoring-Rechnung mit einem Zahlungsziel von 30 Tagen.";
	}

	@Override
	public Date getDueDate() {
		return Date.from(LocalDateTime.now().plusDays(30L).atZone(ZoneId.systemDefault()).toInstant());

	}

	@Override
	public IZUGFeRDPaymentDiscountTerms getDiscountTerms() {
		return null;
	}

}
