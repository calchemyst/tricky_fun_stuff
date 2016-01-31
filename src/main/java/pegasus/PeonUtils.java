package pegasus;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Service;

import java.io.*;
import java.sql.*;
import java.util.List;

/**
 * Created by annafuller on 1/9/16.
 */
public class PeonUtils {

    private PeonUtils() {
        // Utility class, don't instantiate me!
    }

    public static Connection getSqlConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection("jdbc:h2:~/peon", "peon", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw Throwables.propagate(e);
        }
    }


    public static String getFileContents(Class<?> clazz, String name) {
        StringBuilder out = new StringBuilder();
        String line;
        try (InputStream is = clazz.getClassLoader().getSystemResourceAsStream(name);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ){
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return out.toString();

    }

    public static void executeSqlFile(String sqlFile) {
        try (
                BufferedReader reader = new BufferedReader(new StringReader(sqlFile));
        ){
            String line;
            Statement stmt = PeonUtils.getSqlConnection().createStatement();
            while ((line = reader.readLine()) != null) {
                String[] sqlStmts = line.split(";");
                for (String sql : sqlStmts) {
                    stmt.executeUpdate(sql);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            Throwables.propagate(e);
        }  catch (IOException e) {
            Throwables.propagate(e);
        }
    }

    public static Location getLocationFromName(String name) {
        Location location = null;
        try {
            Statement stmt = PeonUtils.getSqlConnection().createStatement();
            String sqlQuery = "select type from rooms where name = \'" + name + "\';";
            ResultSet results = stmt.executeQuery(sqlQuery);
            String type = "";
            if (results.next()) {
                type = results.getString("type");
                switch (type) {
                    case "tavern" :
                        location = new Tavern(name);
                        break;
                    case "farm" :
                        location = new Farm(name);
                        break;
                    case "store":
                        location = new Store(name);
                        break;
                    case "dungeon":
                        location = new Dungeon(name);
                        break;
                }
            }
            stmt.close();
        } catch ( SQLException e) {
            throw Throwables.propagate(e);
        }
        return location;
    }

    public static String move(String direction, String locationName) {
        try {
            Statement stmt = PeonUtils.getSqlConnection().createStatement();
            String sqlQuery = "select " + direction + " from rooms where name = \'" + locationName  + "\';";
            ResultSet results = stmt.executeQuery(sqlQuery);
            String location = "";
            if (results.next()) {
                location = results.getString(direction);
            }
            stmt.close();
            return location;
        } catch (SQLException e) {
            throw Throwables.propagate(e);
        }
    }

    public static List<String> executeSqlQuery(String sql, String columnName) {
        List<String> resultSet = Lists.newArrayList();
        try {
            Statement stmt = PeonUtils.getSqlConnection().createStatement();
            ResultSet results = stmt.executeQuery(sql);
            while (results.next()) {
                resultSet.add(results.getString(columnName));
            }
            stmt.close();
            return resultSet;
        } catch (SQLException e) {
            throw Throwables.propagate(e);
        }
    }

    public static Peon getPeon(String name) {
        try {
            String peonQuery = "SELECT *  FROM peons where NAME = \'" + name + "\';";
            Statement stmt = PeonUtils.getSqlConnection().createStatement();
            ResultSet results = stmt.executeQuery(peonQuery);
            results.next();
            int health = results.getInt("health");
            int money = results.getInt("money");
            String location = results.getString("location");
            stmt.close();
            return new Peon(name, health, money, location);
        } catch (SQLException e) {
            throw Throwables.propagate(e);
        }
    }

    public static void executeSqlUpdate(String sql) {
        try {
            Statement stmt = PeonUtils.getSqlConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            Throwables.propagate(e);
        }
    }

}
