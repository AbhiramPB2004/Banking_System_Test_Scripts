package utils;

import java.util.*;

public class TestDataStore {

    // Accounts created during automation
    public static List<String> createdAccounts =
            new ArrayList<>();

    // accountNo -> original account type
    public static Map<String,String> updatedAccounts =
            new HashMap<>();

    // Closed account numbers
    public static List<String> closedAccounts =
            new ArrayList<>();
}