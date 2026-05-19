package utils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;
import java.sql.ResultSet;

public class CleanupUtility {

    public static void revertTestData(){

        try{
            Connection conn = DBUtility.getConnection();

            Statement stmt = conn.createStatement();

            //Delete created accounts
            for(String accountNo: TestDataStore.createdAccounts){
                stmt.executeUpdate("DELETE FROM accounts " + "WHERE account_number='" +accountNo+"'");

                System.out.println(
                        "Deleting Account : "
                                + accountNo
                );
                System.out.println(
                        "Database reverted successfully"
                );
                ResultSet rs =
                        stmt.executeQuery(

                                "SELECT * FROM accounts " +
                                        "WHERE account_number='"
                                        +accountNo+"'"
                        );

                if(!rs.next()){

                    System.out.println(
                            "Account deleted from DB"
                    );
                }
            }

            //Restore updated accounts

            for(Map.Entry<String,String>

                    entry:

                    TestDataStore
                            .updatedAccounts
                            .entrySet()){

                System.out.println(

                        "Restoring Account : "
                                + entry.getKey()
                                + " To : "
                                + entry.getValue()
                );

                stmt.executeUpdate(

                        "UPDATE accounts "+
                                "SET account_type='"
                                +entry.getValue()+"' "+
                                "WHERE account_number='"
                                +entry.getKey()+"'"
                );
                ResultSet rs =
                        stmt.executeQuery(

                                "SELECT account_type " +
                                        "FROM accounts " +
                                        "WHERE account_number='"
                                        +entry.getKey()+"'"
                        );

                if(rs.next()){

                    System.out.println(
                            "DB Account Type : "
                                    + rs.getString(
                                    "account_type"
                            )
                    );
                }
            }

            //Restore closed accounts

            for(String accountNo:

                    TestDataStore
                            .closedAccounts){

                stmt.executeUpdate(

                        "UPDATE accounts "+
                                "SET status='ACTIVE' "+
                                "WHERE account_number='"
                                +accountNo+"'"
                );
            }

            System.out.println(
                    "Database reverted"
            );

        }

        catch(Exception e){

            e.printStackTrace();
        }
    }
}