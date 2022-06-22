package Validation;

import model.LocalException;

public abstract class Ensurer
{
    public static void checkState(boolean state, String message) throws LocalException
    {
        if (!state)
        {
            throw new LocalException(message);
        }
    }

    public static boolean isNotNullNotBlank(String str)
    {
        return str != null && ! str.isBlank();
    }

    public static String ensureNotNullNotBlank(String str)
    {
        if (isNotNullNotBlank(str))
            return str;
        else
            throw new NullPointerException("It can't be Null or Balnk");

    }

    public static boolean isValueInRange(int value, int min, int max)
    {
        return value >= min && value <= max;
    }

    public static int ensureValueInRange(int value, int min, int max, String attrName)
    {
        if (!isValueInRange(value, min, max))
            throw new IllegalArgumentException(
                    String.format("%s must be between %d and %d.", attrName, min, max));

        return value;
    }

//    public static boolean ensureTrue(boolean right) throws LocalException
//    {
//        if (!right)
//        {
//            return right;
//        }
//        else
//            throw new LocalException("Without alcohol");
//    }

}
