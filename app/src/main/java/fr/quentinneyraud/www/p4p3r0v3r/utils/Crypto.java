package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.support.annotation.NonNull;

import com.tozny.crypto.android.AesCbcWithIntegrity;
import java.security.GeneralSecurityException;

/**
 * Created by quentin on 22/10/2016.
 */

public class Crypto {

    private static Crypto instance;

    private Crypto() {

    }

    public static Crypto getInstance() {
        if (instance == null) {
            instance = new Crypto();
        }

        return instance;
    }

    public byte[] generateSalt() {
        byte[] salt = new byte[0];

        try {
            salt = AesCbcWithIntegrity.generateSalt();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (salt.length <= 0) throw new AssertionError();

        return salt;
    }

    @NonNull
    public AesCbcWithIntegrity.SecretKeys getSecretKeys(String password, byte[] salt) {
        AesCbcWithIntegrity.SecretKeys keys = null;

        try {
            keys = AesCbcWithIntegrity.generateKeyFromPassword(password, salt);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        // keys generation failed
        if (keys == null) throw new AssertionError();

        return keys;
    }
}
