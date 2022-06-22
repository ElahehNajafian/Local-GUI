//package model;
//
//import Validation.Ensurer;
//
//import java.util.Objects;
//
//public class Drink extends Product
//{
//    private String drinkName;
//    private boolean withAlcohol;
//    private  int ml;
//    private int count;
//
//    public Drink(int id, double price, String drinkName, boolean withAlcohol, int ml, int count) throws LocalException
//    {
//        super(id, price);
//        setDrinkName(drinkName);
//        setWithAlcohol(withAlcohol);
//        setMl(ml);
//        setCount(count);
//    }
//
//    //-----Getter----------------------------
//
//    public String getDrinkName()
//    {
//        return drinkName;
//    }
//
//    public boolean isWithAlcohol()
//    {
//        return withAlcohol;
//    }
//
//    public int getMl()
//    {
//        return ml;
//    }
//
//    public int getCount()
//    {
//        return count;
//    }
//
//    //-----Setter----------------------------
//
//    public void setDrinkName(String drinkName)
//    {
//        this.drinkName = Ensurer.ensureNotNullNotBlank(drinkName);
//    }
//
//    public void setWithAlcohol(boolean withAlcohol)
//    {
//        this.withAlcohol = withAlcohol;
//    }
//
//    public void setMl(int ml)
//    {
//        this.ml = ml;
//    }
//
//    public void setCount(int count)
//    {
//        this.count = count;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (!(o instanceof Drink)) return false;
//        if (!super.equals(o)) return false;
//        Drink drink = (Drink) o;
//        return withAlcohol == drink.withAlcohol && ml == drink.ml && count == drink.count && Objects.equals(drinkName, drink.drinkName);
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(super.hashCode(), drinkName, withAlcohol, ml, count);
//    }
//
//    @Override
//    public String toString()
//    {
//        return super.toString() + "  " +
//                  drinkName + "\t\t" +
//                  withAlcohol + "\t" +
//                  ml + " ml" + "\t" +
//                  "count: " + count;
//    }
//}
