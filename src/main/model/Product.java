package model;

import Validation.Ensurer;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable
{
    private int id;
    private String drinkName;
    private boolean withAlcohol;
    private  int ml;
    private static double maxprice = 50;
    private double price;

    private int count;

    public Product(int id, String drinkName, boolean withAlcohol, int ml, double price, int count) throws LocalException
    {
        setId(id);
        setDrinkName(drinkName);
        setWithAlcohol(withAlcohol);
        setMl(ml);
        setprice(price);
        setCount(count);
    }

    public Product()
    {

    }

    public int getId()
    {
        return id;
    }

    public String getDrinkName()
    {
        return drinkName;
    }

    public boolean isWithAlcohol()
    {
        return withAlcohol;
    }

    public int getMl()
    {
        return ml;
    }

    public static double getMaxprice()
    {
        if (setMaxprice(maxprice));
        return maxprice;
    }

    public double getPrice()
    {
        return price;
    }

    public int getCount()
    {
        return count;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setDrinkName(String drinkName)
    {
        this.drinkName = Ensurer.ensureNotNullNotBlank(drinkName);
    }

    public void setWithAlcohol(boolean withAlcohol)
    {
        this.withAlcohol = withAlcohol;
    }

    public void setMl(int ml)
    {
        Objects.requireNonNull(ml);
        this.ml = ml;
    }

    public static boolean setMaxprice(double maxprice)
    {
        Product.maxprice = maxprice;
        return true;
    }

    public void setprice(double price) throws LocalException
    {
        Objects.requireNonNull(price);
        if (price>=2 && price<=maxprice)
            this.price = price;
        else
            throw new LocalException("Invalid price");
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id
                && withAlcohol == product.withAlcohol
                && ml == product.ml
                && Double.compare(product.price, price) == 0
                && count == product.count
                && drinkName.equals(product.drinkName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, drinkName, withAlcohol, ml, price, count);
    }

    @Override
    public String toString()
    {
        return id + "\t" + "  " +
                  drinkName + "\t" + "  " +
                  withAlcohol + "\t" + "  " +
                  ml + " ml" + "\t" + "  " +
                  price + " €" + "\t" + "  " +
                 "count: " + count;
    }


}
