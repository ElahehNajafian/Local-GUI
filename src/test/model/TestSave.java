package model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TestSave
{
    public static void main(String[] args)
    {
        try
        {
            var cola = new Product(1,  "Cola", false, 100, 2.45, 200 );
            var bier = new Product(2,  "Bier",      true, 400, 3.5, 500 );
            var water = new Product(3,  "Water",     false, 200, 2.0, 300 );
            var pepsi = new Product(4,  "Pepsi",     false, 1000, 2.99, 600);
            var redWine = new Product(5,  "Rose",  true, 250, 7.99, 300 );
            var wine = new Product(6,  "Wine",  true, 100,  6.80,250);
            var champion = new Product(7,  "Vodka",  true, 500,12.80,450  );


            var local = new Local();
            local.addProducts(cola, bier,water,pepsi,redWine,wine, champion );

            List<Product> products = List.of(cola, bier,water,pepsi,redWine,wine, champion);
            // local.addProducts(products);

//            Files.createDirectory(Path.of(path).getParent());
            local.save("C:\\Users\\Elaheh\\OneDrive - HTL Spengergasse\\Desktop\\College\\Java_InteliJ\\Local-GUI/local.ser");
            System.out.println("Save OK..");

        } catch (LocalException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
