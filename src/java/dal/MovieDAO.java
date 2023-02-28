package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import model.Genres;
import model.Movie;

/**
 *
 * @author Nhat Anh
 */
public class MovieDAO extends DBContext {
    //Lấy ra tất cả thể loại trong cơ sở dữ liệu 
    public ArrayList<Genres> getAllGenre() {
        ArrayList<Genres> listGenre = new ArrayList<>();
        try {
            String sql = "select * from Genres g";
            // Dua cau lenh sql vao de chuan bi thuc thi

            PreparedStatement stm = connection.prepareStatement(sql);

            //Thuc thi cau lenh sql de lay du lieu
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                listGenre.add(new Genres(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
        }
        return listGenre;
    }

    public ArrayList<Movie> getAllMovie() {
        ArrayList<Movie> listMovie = new ArrayList<>();
        try {
            String sql = "select m.MovieID,m.Title,m.Year,m.Image,CAST(m.Description AS NVARCHAR(100)) as Description,g.Description as Genre,Round(avg(r.NumericRating),2) as Rate from Movies m,Rates r,Genres g where m.MovieID = r.MovieID and m.GenreID = g.GenreID group by m.MovieID,m.Title,m.Year,m.Image,CAST(m.Description AS NVARCHAR(100)),g.Description";
            // Dua cau lenh sql vao de chuan bi thuc thi

            PreparedStatement stm = connection.prepareStatement(sql);

            //Thuc thi cau lenh sql de lay du lieu
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7));
                listMovie.add(movie);
            }
        } catch (SQLException e) {
        }
        return listMovie;
    }
    }
