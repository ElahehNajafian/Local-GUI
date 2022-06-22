package model;

import Validation.Ensurer;

import java.io.*;
import java.util.*;

public class Local
{
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts()
    {
        return Collections.unmodifiableList(products);
    }

    //Variable number of argument
    public void addProducts(Product ...products) throws LocalException
    {
        for (Product p : products) {
            addProduct(p);
        }
    }
    // Paramete -> variable number
    public void addProducts(List<Product> products) throws LocalException
    {
        for (Product p : products) {
            addProduct(p);
        }
    }


    // Parameter -> is a collection
    public void addProduct(Product product) throws LocalException
    {
        Objects.requireNonNull(product, "Prouduct is null");
        Ensurer.checkState(! products.contains(product), "Product already exists!");
        Ensurer.checkState(products.size() < 100, "Product musst be max 50");

        products.add(product);

    }

    public void removeProduct(Product product)
    {
        Objects.requireNonNull(product, "Prouduct is null");

        products.remove(product);
    }

    public void save(String path) throws LocalException
    {
        try (var oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(products);
        }
          catch (IOException e) {
            throw new LocalException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked cast") //Yes compiler, I know its a List<Product>
    public void load(String path) throws LocalException
    {
        try (var ois = new ObjectInputStream(new FileInputStream(path))) {
            products = (List<Product>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new LocalException(e.getMessage());
        }

    }

       @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( products.size() + "  \n");
        for (Product p : products)
        {
            sb.append(p.toString()).append("\n");
//            sb.append(p);
        }

        return sb.toString();
   }

    public void sortName()
    {
        products.sort(Comparator.comparing(Product::getDrinkName).reversed());
    }

    public void sortPrice()
    {
        products.sort(Comparator.comparing(Product::getPrice));
    }
}
