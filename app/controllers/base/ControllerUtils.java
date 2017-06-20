package controllers.base;

import controllers._shared.TaxesAndFeesBO;
import providers.FirebaseDatabaseProvider;
import providers.models.TaxItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControllerUtils {

    public static TaxesAndFeesBO calculateTaxesAndFees(float amount, String country, String state, String city) {
        // TODO add if taxes enabled or not
        List<TaxItem> taxItems = FirebaseDatabaseProvider.TAXES.stream()
                .filter(taxItem -> (city == null || Objects.equals(taxItem.getCity(), city)) && (state == null || Objects.equals(taxItem.getState(), state)) && Objects.equals(taxItem.getCountry(), country))
                .collect(Collectors.toList());

        if (taxItems.size() == 0) {
            taxItems = FirebaseDatabaseProvider.TAXES.stream()
                    .filter(taxItem -> (state == null || Objects.equals(taxItem.getState(), state)) && Objects.equals(taxItem.getCountry(), country))
                    .collect(Collectors.toList());
        }

        if (taxItems.size() == 0) {
            taxItems = FirebaseDatabaseProvider.TAXES.stream()
                    .filter(taxItem -> Objects.equals(taxItem.getCountry(), country))
                    .collect(Collectors.toList());
        }

        if (taxItems.size() > 0) {
            TaxItem selectedTax = taxItems.get(0);
            float taxes = amount * selectedTax.getSalesTax() / 100;
            float fees = amount * selectedTax.getWirelessTax() / 100;
            return new TaxesAndFeesBO(taxes, fees);
        } else {
            return new TaxesAndFeesBO(0, 0);
        }
    }

}
