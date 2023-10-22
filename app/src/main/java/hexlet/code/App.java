package hexlet.code;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.repositories.BaseRepository;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.stream.Collectors;


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
//         System.setProperty("h2.traceLevel", "TRACE_LEVEL_SYSTEM_OUT=4");


        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:project:DB_CLOSE_DELAY=-1");
        var dataSource = new HikariDataSource(hikariConfig);
        //routing
        var url = App.class.getClassLoader().getResource("schema.sql");
        var file = new File(url.getFile());
        var sql = Files.lines(file.toPath())
                .collect(Collectors.joining("\n"));
//        //conn

        log.info(sql);
        try(var conn = dataSource.getConnection();
            var statement = conn.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.before(ctx -> {
            ctx.contentType("text/html; charset=utf-8");
        });
        app.get("/", ctx -> {
            ctx.result("Hello, World");
        });

        return app;
    }
}