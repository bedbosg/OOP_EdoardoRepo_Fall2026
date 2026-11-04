package payment;

interface PaymentMethod {
    void processPayment(double amount);
}

abstract class CardPayment implements PaymentMethod {
    private String cardNumber;
    private String expiryDate;

    CardPayment(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}

class CreditCard extends CardPayment {
    CreditCard(String cardNumber, String expiryDate) {
        super(cardNumber, expiryDate);
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

class DebitCard extends CardPayment {
    DebitCard(String cardNumber, String expiryDate) {
        super(cardNumber, expiryDate);
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing debit card payment of $" + amount);
    }
}

class GiftCard extends CardPayment {
    private double balance;

    GiftCard(String cardNumber, String expiryDate, double balance) {
        super(cardNumber, expiryDate);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void processPayment(double amount) {
        if (balance >= amount) {
            System.out.println("Processing gift card payment of $" + amount);
            balance -= amount;
            System.out.println("Remaining balance: $" + balance);
        } else {
            System.out.println("Insufficient balance on gift card.");
        }
    }
}

class CashPayment implements PaymentMethod {
    private double cashProvided;

    CashPayment(double cashProvided) {
        this.cashProvided = cashProvided;
    }

    @Override
    public void processPayment(double amount) {
        if (cashProvided >= amount) {
            System.out.println("Processing cash payment of $" + amount);
            double change = cashProvided - amount;
            if (change > 0) {
                System.out.println("Returning change: $" + change);
            }
        } else {
            System.out.println("Insufficient cash provided.");
        }
    }
}

class VoucherPayment implements PaymentMethod {
    private final String voucherId;

    VoucherPayment(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherId() {
        return voucherId;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing voucher payment of $" + amount + " using voucher ID: " + voucherId);
    }
}

public class PaymentDemo {
    private static void process(PaymentMethod method, double amount) {
        double fee;
        if (method instanceof CreditCard) {
            CreditCard cc = (CreditCard) method;
            fee = amount * 0.02;

        } else if (method instanceof DebitCard) {
            DebitCard dc = (DebitCard) method;
            fee = amount * 0.01;

        } else if (method instanceof GiftCard) {
            GiftCard gc = (GiftCard) method;
            fee = 0.0;

        } else if (method instanceof CashPayment) {
            CashPayment cp = (CashPayment) method;
            fee = 0.0;

        } else if (method instanceof VoucherPayment) {
            VoucherPayment vp = (VoucherPayment) method;
            fee = 0.05;

        } else {
            throw new IllegalStateException("Unexpected payment method: " + method);
        }

        double total = amount + fee;
        method.processPayment(total);
    }

    public static void main(String[] args) {
        // Credit Card Payment
        process(new CreditCard("1234567812345678", "12/24"), 100.50);

        // Debit Card Payment
        process(new DebitCard("8765432187654321", "11/25"), 50.50);

        // Gift Card Payment
        PaymentMethod giftCard = new GiftCard("5555444433332222", "01/26", 150.00);
        process(giftCard, 75.00);
        process(giftCard, 80.00);

        // Cash Payment
        process(new CashPayment(200.00), 180.00);

        // Voucher Payment
        process(new VoucherPayment("VOUCHER-12345"), 50.00);
    }
}
