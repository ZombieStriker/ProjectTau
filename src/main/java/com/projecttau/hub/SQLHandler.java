package com.projecttau.hub;

import net.minestom.server.entity.Player;

import java.sql.*;

public class SQLHandler {

    private static Connection connection;

    //TODO: Set up variables and set up SQL Database
    private static final String JDBC_URL = null;
    private static final String USERNAME = null;
    private static final String PASSWORD = null;

    /**
     * Closes the connection to the SQL Database
     */
    public static synchronized void closeConnection() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Opens the connection to the SQL Database
     */
    public static synchronized void openConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Returns if the player is in the database.
     * @param player
     * @return if the player is in the database
     */
    public static synchronized boolean isInDatabase(Player player) {
        try {
            PreparedStatement isInDatabaseStatement = connection.prepareStatement("SELECT * FROM `player_data` WHERE uuid=?;");
            isInDatabaseStatement.setString(1, player.getUuid().toString());
            ResultSet result = isInDatabaseStatement.executeQuery();
            boolean isindatabase = result.next();
            result.close();
            isInDatabaseStatement.close();
            return isindatabase;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the server that the player joined recently
     * @param player
     * @return
     */
    public static String getMostRecentServer(Player player){
        if(isInDatabase(player)){
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT recentserver FROM `player_data` WHERE uuid=?;");
                statement.setString(1,player.getUuid().toString());
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                String previousServer = resultSet.getString("recentserver");
                resultSet.close();
                statement.close();
                return previousServer;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }
}
