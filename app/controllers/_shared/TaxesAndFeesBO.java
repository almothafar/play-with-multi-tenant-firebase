package controllers._shared;

/**
 * Created by almothafar on 5/24/2017.
 */
public class TaxesAndFeesBO {
    private float taxes;
    private float fees;

    public TaxesAndFeesBO(float taxes, float fees) {
        this.taxes = taxes;
        this.fees = fees;
    }

    public float getTaxes() {
        return taxes;
    }

    public TaxesAndFeesBO setTaxes(float taxes) {
        this.taxes = taxes;
        return this;
    }

    public float getFees() {
        return fees;
    }

    public TaxesAndFeesBO setFees(float fees) {
        this.fees = fees;
        return this;
    }
}
