package hexlet.code;


import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppUtils {
//    static String getDataBaseUrl() {
//        return System.getenv().getOrDefault(
//                "JDBC_DATABASE_URL",
//                "jdbc:h2:mem:my_db"
//                );
//    }

//    private static Connection connection;
//public static Connection getConnection() throws SQLException {
//    if (connection == null || connection.isClosed()) {
//        String url = System.getenv("JDBC_DATABASE_URL");
//        if (url == null) {
//            // Local development, use H2 in-memory database
//            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
//        } else {
//            // Production, use PostgreSQL database
//            connection = DriverManager.getConnection(url);
//        }
//    }
//    return connection;
//}

    static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

}
