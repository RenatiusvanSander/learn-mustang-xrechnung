package edu.remad.learnmustangxrechnung;

import org.mustangproject.*;
import org.mustangproject.ZUGFeRD.*;
import org.slf4j.LoggerFactory;

import edu.remad.learnmustangxrechnung.models.FourWeeksTutoringPaymentTerms;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class XRechnungExample {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(XRechnungExample.class);

	public static void main(String[] args) {
		LOGGER.info("Logger started");
		
		TradeParty recipient = new TradeParty("Max Mustermann", "Mustermann Str. 12", "DE-22359", "Musterstadt", "DE")
				.setEmail("max.mustermann@mustermann.de").setCountry("Germany");
		
		Product product = new Product("Mathe-Nachhilfe", "Explaination", "C62", BigDecimal.ZERO);
		Item invoiceRowItem = new Item(product,	new BigDecimal(13), new BigDecimal(1.0));
		
		Invoice invoice = new Invoice().setDocumentName("Rechnung").setDueDate(new Date()).setIssueDate(new Date()).setDeliveryDate(new Date())
				.setSender(new TradeParty("Remy Meier", "Irgendeine Strasse 10", "DE-22359", "Mustercity", "DE")
						.addTaxID("65314709782").addVATID("none")
						.setContact(new org.mustangproject.Contact("Remy Meier", "+4917661362253", "remad@web.de"))
						.addBankDetails(new org.mustangproject.BankDetails("DE62120300001071064966", "BYLADEM1001"))
						.setEmail("remad@web.de").setZIP("22359").setLocation("Hamburg"))
				.setRecipient(recipient).setReferenceNumber("991-01484-64")// leitweg-id
				.setNumber("123").addItem(invoiceRowItem).addLegalNote(SubjectCode.ABL.name()).setPaymentTerms(new FourWeeksTutoringPaymentTerms());

		ZUGFeRD2PullProvider zf2p = new ZUGFeRD2PullProvider();
		zf2p.setProfile(Profiles.getByName("XRechnung"));
		zf2p.generateXML(invoice);
		String theXML = new String(zf2p.getXML(), StandardCharsets.UTF_8);
		System.out.println(theXML);
	}
}
