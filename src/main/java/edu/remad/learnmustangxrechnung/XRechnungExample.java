package edu.remad.learnmustangxrechnung;

import org.mustangproject.*;
import org.mustangproject.ZUGFeRD.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class XRechnungExample {

	public static void main(String[] args) {
		TradeParty recipient = new TradeParty("Max Mustermann", "Mustermann Str. 12", "DE-22359", "Musterstadt", "DE")
				.setEmail("max.mustermann@mustermann.de");
		Invoice invoice = new Invoice().setDueDate(new Date()).setIssueDate(new Date()).setDeliveryDate(new Date())
				.setSender(new TradeParty("Remy Meier", "Irgendeine Strasse 10", "DE-22359", "Mustercity", "DE")
						.addTaxID("65314709782").addVATID("none")
						.setContact(new org.mustangproject.Contact("Remy Meier", "+4917661362253", "remad@web.de"))
						.addBankDetails(new org.mustangproject.BankDetails("DE62120300001071064966", "BYLADEM1001"))
						.setEmail("info@example.org"))
				.setRecipient(recipient).setReferenceNumber("991-01484-64")// leitweg-id
				.setNumber("123").addItem(new Item(new Product("Testprodukt", "", "C62", BigDecimal.ZERO),
						new BigDecimal("1.00"), new BigDecimal(1.0)));

		ZUGFeRD2PullProvider zf2p = new ZUGFeRD2PullProvider();
		zf2p.setProfile(Profiles.getByName("XRechnung"));
		zf2p.generateXML(invoice);
		String theXML = new String(zf2p.getXML(), StandardCharsets.UTF_8);
		System.out.println(theXML);
	}
}
