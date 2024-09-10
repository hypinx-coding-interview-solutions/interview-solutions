package com.hypinx.coding.capitalone.codesignal.data.analyst;

import com.hypinx.coding.misc.TestCaseValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Question1 {

    private static Connection connection;

    /**
     * Solution to problem
     */
    private static String solutionProcedure = """
            SELECT
                p.category AS product_category,
                IFNULL(MIN(CASE WHEN stars >= 4 THEN price ELSE NULL END), 0) AS price
            FROM
                products p
            LEFT JOIN
                purchases pu ON p.id = pu.product_id
            GROUP BY
                p.category
            ORDER BY
                p.category ASC
            """;

    private static String expectedProcedure = """
            SELECT * FROM results
            """;

    // Static block sets up test tables and expected result table
    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            setupDatabase(connection);
        } catch (Exception e) {
            System.out.println("Exception occurred setting up in memory H2 db");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        try {

            // Execute solution procedure and store results in list with : as delimiter
            List<String> formattedResults = storeAndFormatResults(connection);

            // Execute expected results procedure and store in list with : as delimiter
            List<String> expectedResults = storeAndFormatExpectedResults(connection);

            // Compare both lists
            TestCaseValidator.validateTestCase("1", formattedResults, expectedResults);

            // Close the connection
            connection.close();
        } catch (Exception e) {
            throw new Exception("Failed to execute stored procedure");
        }
    }

    private static void setupDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        // Create products table
        statement.execute("""
                CREATE TABLE products (
                    id INT PRIMARY KEY,
                    name VARCHAR(255),
                    category VARCHAR(255),
                    price DECIMAL(10,2)
                )
                        """
        );

        statement.execute("""
                CREATE TABLE purchases (
                    id INT PRIMARY KEY,
                    product_id INT,
                    stars INT,
                    FOREIGN KEY (product_id) REFERENCES products(id)
                )
                """
        );

        // Insert sample data
        statement.execute("INSERT INTO products VALUES (1, 'Cripps Pink', 'apple', 10.00)");
        statement.execute("INSERT INTO products VALUES (2, 'Navel Orange', 'orange', 12.00)");
        statement.execute("INSERT INTO products VALUES (3, 'Golden Delicious', 'apple', 6.00)");
        statement.execute("INSERT INTO products VALUES (4, 'Clementine', 'orange', 14.00)");
        statement.execute("INSERT INTO products VALUES (5, 'Pinot Noir', 'grape', 20.00)");
        statement.execute("INSERT INTO products VALUES (6, 'Bing Cherries', 'cherry', 36.00)");

        statement.execute("INSERT INTO purchases VALUES (1, 1, 2)");
        statement.execute("INSERT INTO purchases VALUES (2, 3, 3)");
        statement.execute("INSERT INTO purchases VALUES (3, 2, 2)");
        statement.execute("INSERT INTO purchases VALUES (4, 4, 4)");
        statement.execute("INSERT INTO purchases VALUES (5, 6, 5)");
        statement.execute("INSERT INTO purchases VALUES (6, 6, 4)");

        /* Create the result table - stored price as VARCHAR for consistency since output from solution is
            apple:0
            cherry:36.00
            grape:0
            orange:14.00

            But when we query the expected results table we get

            apple:0.00
            cherry:36.00
            grape:0.00
            orange:14.00

            So the 0 is 0.00
         */
        statement.execute("""
        CREATE TABLE results (
            product_category VARCHAR(255),
            price VARCHAR(10) -- Store as a VARCHAR to ensure consistent formatting
        )
    """);

        // Insert expected data with formatted price values
        statement.execute("INSERT INTO results VALUES ('apple', '0')");
        statement.execute("INSERT INTO results VALUES ('cherry', '36.00')");
        statement.execute("INSERT INTO results VALUES ('grape', '0')");
        statement.execute("INSERT INTO results VALUES ('orange', '14.00')");

        statement.close();
    }

    private static List<String> storeAndFormatResults(Connection connection) throws SQLException {
        List<String> formattedResults = new ArrayList<>();
        Statement statement = connection.createStatement();

        // Query to fetch and format the actual results
        ResultSet rs = statement.executeQuery(solutionProcedure);

        // Iterate through the result set and format each row
        while (rs.next()) {
            String category = rs.getString("product_category");
            String price = rs.getString("price");
            formattedResults.add(category + ":" + price);
        }

        rs.close();
        statement.close();

        return formattedResults;
    }

    private static List<String> storeAndFormatExpectedResults(Connection connection) throws SQLException {
        List<String> expectedFormattedResults = new ArrayList<>();
        Statement statement = connection.createStatement();

        // Query to fetch and format the actual results
        ResultSet rs = statement.executeQuery(expectedProcedure);

        // Iterate through the result set and format each row
        while (rs.next()) {
            String category = rs.getString("product_category");
            String priceValue = rs.getString("price");
            expectedFormattedResults.add(category + ":" + priceValue);
        }

        rs.close();
        statement.close();

        return expectedFormattedResults;
    }
}
