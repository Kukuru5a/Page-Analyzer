package hexlet.code;

import hexlet.code.models.Url;
import hexlet.code.repositories.UrlCheckRepository;
import hexlet.code.repositories.UrlRepository;
import io.javalin.http.Context;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AppTest {
    Javalin app;
    private final Context ctx = mock(Context.class);
    private MockWebServer mockWebServer;


    @BeforeEach
    public void setUpMock() throws IOException, SQLException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
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

    @Test
    public void testNotFoundUrlById() {
        JavalinTest.test(app, (server, client) -> {
            client.delete("/test/delete/777");
            var response =  client.get("/urls/777");
            assertThat(response.code()).isEqualTo(404);
        });
    }
    @Test
    public void testNotCorrectUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=notCorrectUrl";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testUniqUrlValidation() throws SQLException {
        var url = new Url("https://javalintest.io");
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://javalintest.io";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }
    @Test
    public void testNullUrlValidation() throws SQLException {

        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls", "");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }
    @Test
    public void testCheckShowUrl() throws SQLException {
        var url = new Url("https://javalintest.io");
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("Проверки")
                    .contains("https://javalintest.io");
        });
    }
    @Test
    public void testParsingResponse() throws SQLException, IOException {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(Files.readString(Paths.get("./src/test/resources/test-page.html")));

        mockWebServer.enqueue(mockResponse);
        var urlName = mockWebServer.url("/testParsingResponse");
        var url = new Url(urlName.toString());
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls/" + url.getId() + "/checks", "");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("Hello, World!</td>")
                    .contains("Sample Page</td>")
                    .contains("Open source Java</td>");
        });
    }
    @Test
    void testStore() throws SQLException {
        String url = mockWebServer.url("https://ru.hexlet.io/").toString().replaceAll("/$", "");
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=" + url;
            assertThat(client.post("/urls", requestBody).code()).isEqualTo(200);
            var actualUrl = UrlRepository.findByName(url);
            assertThat(actualUrl).isNotNull();
            assertThat(actualUrl.getName()).isEqualTo(url);

            client.post("/urls/" + actualUrl.getId() + "/checks", "");
            var response = client.get("/urls/" + actualUrl.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains(url);

            var actualCheckUrl = UrlCheckRepository
                    .findLatestChecks().get(actualUrl.getId());

            assertThat(actualCheckUrl).isNotNull();
            assertThat(actualCheckUrl.getStatusCode()).isEqualTo(200);
            assertThat(actualCheckUrl.getTitle())
                    .isEqualTo("Хекслет — онлайн-школа программирования, онлайн-обучение ИТ-профессиям");
            assertThat(actualCheckUrl.getH1())
                    .isEqualTo("Лучшая школа программирования по версии пользователей Хабра");
            assertThat(actualCheckUrl.getDescription())
                    .contains("Хекслет — лучшая школа программирования по версии пользователей Хабра. "
                            + "Авторские программы обучения с практикой и готовыми проектами в резюме. "
                            + "Помощь в трудоустройстве после успешного окончания обучения");
        });
    }
}
