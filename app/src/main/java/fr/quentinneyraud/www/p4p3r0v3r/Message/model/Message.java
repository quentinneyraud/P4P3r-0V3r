package fr.quentinneyraud.www.p4p3r0v3r.Message.model;

import android.util.Log;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import org.spongycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.quentinneyraud.www.p4p3r0v3r.utils.Crypto;

/**
 * Created by quentin on 11/10/2016.
 */

public class Message {

    static final String TAG = "=== Message ===";

    private String uid;
    private String message;
    private String userUid;
    private String createdAt;
    private byte[] salt;

    public Message() {
    }

    public Message(String uid, String message, String userUid, String timestamp) {
        this.uid = uid;
        this.message = message;
        this.userUid = userUid;
        this.createdAt = timestamp;
    }

    public Message(String message, String userUid, String createdAt) {
        this.message = message;
        this.userUid = userUid;
        this.createdAt = createdAt;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String timestamp) {
        this.createdAt = timestamp;
    }

    public String getSalt() {
        return Base64.toBase64String(salt);
    }

    public void setSalt(String salt) {
        this.salt = Base64.decode(salt);
    }

    public void generateSalt () {
        this.salt = Crypto.getInstance().generateSalt();
    }

    public String getFormattedDate(String format) {
        Date d = new Date();
        d.setTime(Long.parseLong(this.createdAt));
        return new SimpleDateFormat(format, Locale.FRANCE).format(d);
    }

    public void encryptMessage() {
        if (this.getSalt().equals("")) {
            Log.d("Message", "Cannot get salt");
        }

        AesCbcWithIntegrity.SecretKeys keys = Crypto.getInstance().getSecretKeys("very long passphrase", this.salt);
        AesCbcWithIntegrity.CipherTextIvMac encrypted = null;
        try {
            encrypted = AesCbcWithIntegrity.encrypt(this.getMessage(), keys);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (encrypted == null) throw new AssertionError();

        this.setMessage(encrypted.toString());
    }

    public void decryptMessage () {
        AesCbcWithIntegrity.SecretKeys keysDecrypt;
        keysDecrypt = Crypto.getInstance().getSecretKeys("very long passphrase", this.salt);

        AesCbcWithIntegrity.CipherTextIvMac dataToDecrypt = new AesCbcWithIntegrity.CipherTextIvMac(this.getMessage());

        String decrypted = null;
        try {
            decrypted = AesCbcWithIntegrity.decryptString(dataToDecrypt, keysDecrypt);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (decrypted != null) {
            this.setMessage(decrypted);
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "uid='" + uid + '\'' +
                ", message='" + message + '\'' +
                ", userUid='" + userUid + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
