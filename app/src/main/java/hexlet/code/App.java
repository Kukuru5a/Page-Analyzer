package hexlet.code;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.controllers.UrlController;
import hexlet.code.repositories.BaseRepository;
import hexlet.code.routes.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static hexlet.code.AppUtils.createTemplateEngine;


@Slf4j
public class App {

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    public static void main(String[] args) throws SQLException, IOException {
        var app = getApp();
        app.start(getPort());
    }

    public static Javalin getApp() throws IOException, SQLException {
//        System.setProperty("h2.traceLevel", "TRACE_LEVEL_SYSTEM_OUT=4");
//  configuring jdbc with local DB
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");

        var dataSource = new HikariDataSource(hikariConfig);
        var sql = getResourceFileAsString("schema.sql");
//      conn
        log.info(sql);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.before(ctx -> {
            ctx.contentType("text/html; charset=utf-8");
        });

        //GET
        app.get("/", ctx -> {
            ctx.render("index.jte");
        });
        app.get("/urls", UrlController::urlList);
        app.get(NamedRoutes.sitePagePath("{id}"), UrlController::showUrl);
        //POST
        app.post("/urls", UrlController::addUrl);
        app.post("/urls/{id}/checks", UrlController::checks);
        JavalinJte.init(createTemplateEngine());
        return app;

    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader cl = App.class.getClassLoader();
        return cl.getResourceAsStream(fileName);
    }

    public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);

        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String) reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }
}
