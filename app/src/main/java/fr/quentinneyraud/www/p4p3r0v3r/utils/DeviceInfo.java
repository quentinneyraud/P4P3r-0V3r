package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Patterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeviceInfo {

    private static DeviceInfo instance;

    private DeviceInfo() {

    }

    public static DeviceInfo getInstance() {
        if (instance == null) {
            instance = new DeviceInfo();
        }

        return instance;
    }

    public ArrayList<String> getAccounts(Context context) {
        final Account[] accounts = AccountManager.get(context).getAccounts();
        final Set<String> emailSet = new HashSet<String>();
        for (Account account : accounts) {
            if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches()) {
                emailSet.add(account.name);
            }
        }
        return new ArrayList<>(emailSet);
    }
}
