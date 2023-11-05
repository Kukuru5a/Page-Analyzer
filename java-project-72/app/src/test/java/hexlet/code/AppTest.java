package hexlet.code;

import gg.jte.Content;
import hexlet.code.controllers.UrlController;
import hexlet.code.models.Url;
import hexlet.code.repositories.UrlRepository;
import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.testtools.JavalinTest;
import org.h2.engine.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    Javalin app;
    private final Context ctx = mock(Context.class);


    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }
    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        });
    }
    @Test
    public void testHomePage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        }));
    }
    @Test
    public void testUrlPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testPostUrlPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.post("/urls");
            assertThat(response.code()).isEqualTo(200);
        }));
    }

    @Test
    public void testRightUrlIdPage() throws SQLException {
        var url = new Url("https://www.vk.com");
        UrlRepository.save(url);
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
        }));
    }
    @Test
    public void testWrongUrlIdPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/10000");
            assertThat(response.code()).isEqualTo(404);
        }));
    }
    @Test
    public void testAddUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "name=https://www.vk.com";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("name");
        });
    }
}
