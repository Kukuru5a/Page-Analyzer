package hexlet.code;

import hexlet.code.models.Url;
import hexlet.code.repositories.UrlCheckRepository;
import hexlet.code.repositories.UrlRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class AppTest {
    private static Javalin app;
    private static MockWebServer mockWebServer;
    private final String fistCorrectUrl = "https://ru.hexlet.io/programs/java";
    private final String secondCorrectUrl = "https://github.com/DaniilDeFacto/java-project-72";
    private final String incorrectUrl = "https//ru.hexlet.io/programs/java";

    @BeforeAll
    public static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        app = App.getApp();
    }

    @AfterAll
    public static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, ((server, client) -> {
            String requestBody = "url=" + fistCorrectUrl;
            var response = client.post("/urls", requestBody);
            var urls = UrlRepository.getUrls();
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://ru.hexlet.io");
            assertThat(urls.size()).isEqualTo(1);
            assertThat(urls.get(0).getName()).isEqualTo("https://ru.hexlet.io");
        }));
    }

    @Test
    public void testCreateIncorrectUrl() {
        JavalinTest.test(app, ((server, client) -> {
            String requestBody = "url=" + incorrectUrl;
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(UrlRepository.getUrls().size()).isEqualTo(0);
        }));
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, ((server, client) -> {
            Url url1 = new Url(fistCorrectUrl);
            Url url2 = new Url(secondCorrectUrl);
            UrlRepository.save(url1);
            UrlRepository.save(url2);
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("hexlet").contains("github");
            assertThat(UrlRepository.getUrls().size()).isEqualTo(2);
        }));
    }

    @Test
    public void testUrlPage() {
        JavalinTest.test(app, (server, client) -> {
            Url testUrl = new Url(secondCorrectUrl);
            UrlRepository.save(testUrl);
            var response = client.get("/urls/" + testUrl.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("github");
        });
    }

    @Test
    void testUrlNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void testMakeCheck() throws SQLException, IOException {
        String page = Files.readString(Paths.get("./src/test/resources/test.html"));
        MockResponse mockResponse = new MockResponse().setResponseCode(200).setBody(page);
        mockWebServer.enqueue(mockResponse);
        String urlString = mockWebServer.url("/").toString();
        Url testUrl = new Url(urlString);
        UrlRepository.save(testUrl);
        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls/" + testUrl.getId() + "/checks");
            assertThat(response.code()).isEqualTo(200);
            var lastCheck = UrlCheckRepository.getLastCheck(testUrl.getId()).get();
            assertThat(lastCheck.getStatusCode()).isEqualTo(200);
            assertThat(lastCheck.getTitle()).isEqualTo("Sample title");
            assertThat(lastCheck.getH1()).isEqualTo("Sample header");
            assertThat(lastCheck.getDescription()).contains("Sample description");
        });
    }
}
