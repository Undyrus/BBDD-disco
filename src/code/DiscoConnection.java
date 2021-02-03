/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author elram
 */
public class DiscoConnection {

    boolean connected = false;
    Connection connection = null;

    public int connectionPull() {
        BasicDataSource bdSource = new BasicDataSource();
        bdSource.setUrl("jdbc:mysql://localhost:3306/disco");
        bdSource.setUsername("root");
        bdSource.setPassword("");
        if (!connected) {
            try {
                connection = bdSource.getConnection();
                if (connection != null) {
                    connected = true;
                    return 0;
                }
            } catch (SQLException ex) {
                return 1;
            }
        }
        return 2;
    }

    public int disconnect() {
        if (connected) {
            try {
                connection.close();
                connected = false;
                return 0;
            } catch (SQLException ex) {
                return 1;
            }
        } else {
            return 2;
        }
    }

//    public int addColumn() {
//        if (connected) {
//            Statement sta;
//            try {
//                sta = connection.createStatement();
//                sta.executeUpdate("ALTER TABLE album ADD releaseDate YEAR;");
//                sta.close();
//                return 0;
//            } catch (SQLException ex) {
//                return 1;
//            }
//        } else {
//            return 2;
//        }
//    }
    public int addSong(String songName, int songAlbum, String songLength) {
        if (connected) {
            Statement sta;
            try {
                sta = connection.createStatement();
                sta.executeUpdate("(\"INSERT INTO songs (song_name, song_album ,song_length) "
                        + "VALUES('\" + songName + \"', '\" + songAlbum + \"','\" + songLength + \"');\");");
                sta.close();
                return 0;
            } catch (SQLException ex) {
                return 1;
            }
        } else {
            return 2;
        }
    }

    public int addAlbum(String albumName, int albumArtist, String releaseDate) {
        if (connected) {
            Statement sta;
            try {
                sta = connection.createStatement();
                sta.executeUpdate("INSERT INTO albumes (album_name, album_artist, release_date) "
                        + "VALUES('" + albumName + "', '" + albumArtist + "','" + releaseDate + "');");
                sta.close();
                return 0;
            } catch (SQLException ex) {
                return 1;
            }
        } else {
            return 2;
        }
    }

    public ArrayList<String> staQueryAlbum() {
        ArrayList<String> notConnected = new ArrayList<String>();
        notConnected.add("notConnected");
        ArrayList<String> error = new ArrayList<String>();
        error.add("error");
        if (connected) {
            ArrayList<String> album = new ArrayList<String>();
            Statement sta;
            try {
                sta = connection.createStatement();
                String query = "SELECT * from albums";
                ResultSet rs = sta.executeQuery(query);

                while (rs.next()) {
                    album.add(rs.getString("album_name"));
                    album.add(rs.getString("album_artist"));
                    album.add(rs.getString("release_date"));
                }
                return album;
            } catch (Exception e) {
                return error;
            }

        }
        return notConnected;
    }
   public void createDataBase() {
        Statement sta;
        try {
            sta = connection.createStatement();
            String[] query = {"DROP DATABASE IF EXISTS disco;",
                "CREATE DATABASE disco;",
                "use disco;",
                "CREATE TABLE IF NOT EXISTS songs (id_song int(11) NOT NULL AUTO_INCREMENT, song_name varchar(255) NOT NULL, song_album int(11) NOT NULL, song_length varchar (255) NOT NULL,PRIMARY KEY (id_song));",
                "CREATE TABLE IF NOT EXISTS albums (id_album int(11) NOT NULL AUTO_INCREMENT , album_name varchar(255) NOT NULL, album_artist varchar(255) NOT NULL,  release_Date DATE NOT NULL,PRIMARY KEY (id_album));",
                "INSERT INTO songs (song_name, song_album, song_length) VALUES( 'Never Meant to Know', 1 ,'3:40'),( '&', 1 ,'3:14'),( 'You & Me', 1 ,'2:52'),( 'Cannibal', 1 ,'3:28'),( 'Who You Are', 1 ,'3:40'),( 'Gimme Love', 1 ,'215'),('Sacred Beast', 1 ,'2:22'),( 'Hymn for a Scarecrow', 1 ,'4:50'),('A Lady', 1 ,'1:05'),('The Trap', 1 ,'4:31'),('Turn the Lights Off', 1 ,'2:56'),('Missery Fell', 1 ,'3:34'),('Out in the Twilight', 1 ,'2:51'),('You', 1 ,'2:57'),('Fate of the Stars', 1 ,'6:50'),('Introduction to the Snow', 2 ,'1:41'),('Isle Unto Thyself', 2 ,'3:46'),('Black Rainbows', 2 ,'2:30'),('White Ball', 2 ,'3:35'),('Murders', 2 ,'3:43'),('宇宙ステーションのレベル7', 2 ,'3:23'),('The Mind Electric', 2 ,'6:13'),('Labyrinth', 2 ,'2:33'),('Time Machine', 2 ,'4:12'),('Stranded Lullaby', 2 ,'3:43'),('Dream Sweet in Sea Major', 2 ,'7:00');",
                "INSERT INTO albums (album_name, album_artist,release_date) VALUES('Good & Evil', 'Tally Hall','201-06-21'),( 'Hawaii: Part II','Joe Hawley','2012-12-12');",
                "ALTER TABLE songs ADD FOREIGN KEY (song_album) REFERENCES albums(id_album);"};
            for (int i = 0; i < query.length; i++) {
                sta.executeUpdate(query[i]);
            }
            sta.close();
        } catch (Exception e) {

            System.err.println("No se pudo crear la base de datos" + e);
        }
    }
}
