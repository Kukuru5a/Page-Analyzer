package hexlet.code.repositories;

import hexlet.code.models.Url;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static hexlet.code.repositories.BaseRepository.dataSource;

public class UrlRepository {
    public static void save (Url url) throws SQLException {
        var sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
        try(var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,"name");
            stmt.setString(2, "description");
            stmt.executeUpdate();
            var genKeys = stmt.getGeneratedKeys();
            // install id into created entities
            if(genKeys.next()){
                url.setId(genKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<Url> find(Long id) throws SQLException {
        var sql = "SELECT * FROM courses WHERE id=?";
        try(var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resSet = stmt.executeQuery();
            if(resSet.next()) {
                var name = resSet.getString("name");
                var url = new Url(name);
                url.setId(id);
                return Optional.of(url);
            }
            return Optional.empty();
        }
    }

    public static Url findByName(String urlName) throws SQLException {
        var sql = "SELECT * FROM urls WHERE name = ?";

        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, urlName);
            var resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                var id = resultSet.getLong("id");
                var url = new Url(urlName);
                var createdAt = resultSet.getTimestamp("created_at");

                url.setId(id);
                url.setCreatedAt(createdAt);
                return url;
            }
            return null;
        }
    }

    public static List<Url> getEntities() throws SQLException {
        var sql = "SELECT * FROM courses";
        try(var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)) {
            var resSet = stmt.executeQuery();
            var res = new ArrayList<Url>();
            while(resSet.next()) {
                var id =resSet.getLong("id");
                var name = resSet.getString("name");
                var url = new Url(name);
                url.setId(id);
                res.add(url);
            }
            return res;
        }
    }
}
