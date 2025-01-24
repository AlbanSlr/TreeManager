package fr.treemanager.models.payment;

public enum PaymentState {
    PENDING,
    PAID,
    CANCELED

    @Override
    public String toString() {
        return switch (this) {
            case PENDING -> "En attente";
            case PAID -> "Payé";
            case CANCELED -> "Annulé";
        };
    }
}
