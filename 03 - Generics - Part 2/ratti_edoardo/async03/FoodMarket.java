package async03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Food products
abstract class Food {
    public void eat() {
        System.out.printf("Eating %s%n", this);
    }
}

class IceCream extends Food {
    public void freeze() {
        System.out.println("Freezing " + this);
    }

    @Override
    public String toString() {
        return String.format("IceCream %d", hashCode());
    }
}

abstract class Bakery extends Food {
    public void bake() {
        System.out.println("Baking " + this);
    }
}

class Cake extends Bakery {
    @Override
    public String toString() {
        return String.format("Cake %d", hashCode());
    }
}

class Bread extends Bakery {
    @Override
    public String toString() {
        return String.format("Bread %d", hashCode());
    }
}

// Producers
abstract class FoodManufacturer<T extends Food> {
    protected abstract T produce();

    public void addToStock(List<? super T> stock) {
        stock.add(produce());
    }
}

class IceCreamMaker extends FoodManufacturer<IceCream> {
    @Override
    protected IceCream produce() {
        IceCream product = new IceCream();
        product.freeze();
        System.out.printf("Produced %s%n", product);
        return product;
    }
}

class PastryChef extends FoodManufacturer<Cake> {
    @Override
    protected Cake produce() {
        Cake product = new Cake();
        product.bake();
        System.out.printf("Produced %s%n", product);
        return product;
    }
}

class Baker extends FoodManufacturer<Bread> {
    @Override
    protected Bread produce() {
        Bread product = new Bread();
        product.bake();
        System.out.printf("Produced %s%n", product);
        return product;
    }
}

public class FoodMarket {
    public static void main(String[] args) {
        final IceCreamMaker iceMaker = new IceCreamMaker();
        final PastryChef pastryChef = new PastryChef();
        final Baker baker = new Baker();

        final List<IceCream> iceCreamSalesCounter = new ArrayList<>();
        final List<Cake> cakeSalesCounter = new ArrayList<>();
        final List<Bread> breadSalesCounter = new ArrayList<>();

        // Simulate production
        System.out.println("Production");
        for (int i = 0; i < 10; i++) {
            iceMaker.addToStock(iceCreamSalesCounter);
            pastryChef.addToStock(cakeSalesCounter);
            baker.addToStock(breadSalesCounter);
        }

        // Simulate charity box
        System.out.println();
        System.out.println("Charity");
        final List<Food> charityBox = new ArrayList<>();
        iceMaker.addToStock(charityBox);
        pastryChef.addToStock(charityBox);
        baker.addToStock(charityBox);

        for (final Food food : charityBox)
            food.eat();
    }
}
