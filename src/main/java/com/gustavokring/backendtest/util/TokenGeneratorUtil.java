package com.gustavokring.backendtest.util;

import java.security.SecureRandom;

public class TokenGeneratorUtil {

    protected static SecureRandom random = new SecureRandom();

    public synchronized String generateToken() {
        long longToken = Math.abs( random.nextLong() );
        return Long.toString( longToken, 16 );
    }
}
