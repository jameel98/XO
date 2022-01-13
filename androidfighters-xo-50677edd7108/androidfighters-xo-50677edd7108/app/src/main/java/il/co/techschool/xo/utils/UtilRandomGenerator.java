package il.co.techschool.xo.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Admin on 6/23/2017.
 */

public final class UtilRandomGenerator
{
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}

