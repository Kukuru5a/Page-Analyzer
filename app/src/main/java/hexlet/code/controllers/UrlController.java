package hexlet.code.controllers;

import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPg;
import hexlet.code.models.Url;
import hexlet.code.models.UrlCheck;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.repositories.UrlCheckRepository;
import hexlet.code.repositories.UrlRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UrlController {

//    public static void create(Context context) throws SQLException {
//        var name = context.formParam("name");
//
//        var url = new Url(name);
//        UrlRepository.save(url);
//        context.redirect("/urls");
//    }
//
//    public static void add1(Context ctx) throws SQLException {
//        try {
//            var name = ctx.formParamAsClass("url", String.class)
//                    .check(value -> value != null, "field must not be empty")
//                    .get();
//            var url = new Url(name);
//            UrlRepository.save(url);
//            ctx.sessionAttribute("flash", "Пост был успешно создан!");
//            ctx.sessionAttribute("flash-type", "success");
//            ctx.redirect("/");
//
//        } catch (ValidationException e) {
//            var name = ctx.formParam("name");
//            var page = new UrlsPage(name, e.getErrors());
//            ctx.render("urls/urls.jte", Collections.singletonMap("page", page)).status(422);
//        }
//    }
//
//    public static void listOfUrls(Context ctx) throws SQLException {
//        List<Url> urls = UrlRepository.getEntities();
//
//        ctx.attribute("urls", urls);
//        ctx.render("urls/urls.jte");
//    }
//    public static String parseUrl(String transmittedUrl) {
//        try {
//            URL url = new URL(transmittedUrl);
//            String urlProtocol = url.getProtocol();
//            String urlAuthority = url.getAuthority();
//            return urlProtocol + "://" + urlAuthority;
//        } catch (MalformedURLException e) {
//            return null;
//        }
//    }
//
//
//
//
//
//    public static void index(Context ctx) throws SQLException {
//        var urls = UrlRepository.getEntities();
//        var page = new UrlsPage(urls);
//        ctx.render("urls/urls.jte");
//    }
//
//    public static void show(Context ctx) throws SQLException {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        var url = UrlRepository.find(id)
//                .orElseThrow(() -> new NotFoundResponse("Post not found"));
//
//        var page = new UrlPage(url);
//        ctx.render("show.jte", Collections.singletonMap("page", page));
//    }

    public static void addUrl(Context ctx) throws SQLException {
        String urlName;
        urlName = ctx.formParam("url");
        URL url;

        try {
            url = new URL(urlName);
        } catch (MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.redirect("/");
            return;
        }

        String normalizedUrl = url.getProtocol() + "://" + url.getAuthority();
        Url urlFromDb = UrlRepository.findByName(normalizedUrl);

        if (urlFromDb != null) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.sessionAttribute("flash-type", "info");
            ctx.redirect("/urls");
            return;
        }

        Url urlForSave = new Url(normalizedUrl);
        UrlRepository.save(urlForSave);

        ctx.sessionAttribute("flash", "Страница успешно добавлена");
        ctx.sessionAttribute("flash-type", "success");
        ctx.redirect("/urls");
    }
//    public static void urlAdd(Context ctx) throws SQLException {
//        var name = ctx.formParam("url");
//        Url url = new Url(name);
//
//        UrlRepository.save(url);
//        ctx.redirect("/urls");
//    }

//    public static void add(Context ctx) throws SQLException {
//        var name = ctx.formParam("url");
//
//        URL url1;
//        try{
//            url1 = new URL(name);
//        }catch (MalformedURLException e) {
//            ctx.sessionAttribute("flash", "Wrong URL");
//            ctx.sessionAttribute("flash-type", "danger");
//            ctx.redirect("/");
//            return;
//        }
//        String normUrl = url1.getProtocol() + "://" + url1.getAuthority();
//        Url urlFromDB = UrlRepository.findByName(normUrl);
//        if(urlFromDB != null) {
//            ctx.sessionAttribute("flash", "URL is already exists");
//            ctx.sessionAttribute("flash-type", "alert");
//            ctx.redirect("/urls");
//        }
//            Url urlToSave = new Url(normUrl);
//            UrlRepository.save(urlToSave);
//
//            ctx.sessionAttribute("flash", "URL added successfully");
//            ctx.sessionAttribute("flash-type", "success");
//            ctx.redirect("/urls");
//    }

//    public static void listOfUrls(Context ctx) throws SQLException {
//        List<Url> urls = UrlRepository.getEntities();
////        Map<Long, UrlCheck> urlChecks = UrlCheckRepository.findLatestChecks();
////
//        ctx.attribute("urls", urls);
////        ctx.attribute("urlChecks", urlChecks);
//        ctx.render("urls/urls.jte");
//    }

    //adding list OK
    public static void urlList(Context ctx) throws SQLException {
        List<Url> urls = UrlRepository.getEntities();
        var page = new UrlsPg(urls);
        ctx.render("urls/urls.jte", Collections.singletonMap("page", page));
    }
//    public static void showUrl(Context ctx) throws SQLException {
//        Long id = Long.valueOf(ctx.pathParamAsClass("id", Integer.class).getOrDefault(null));
//
//        Url url = UrlRepository.find(id);
//
//        if (url == null) {
//            throw new NotFoundResponse();
//        }
//        List<UrlCheck> urlChecks = UrlCheckRepository.getEntitiesByUrlId(id);
//
//        ctx.attribute("url", url);
//        ctx.attribute("checks", urlChecks);
//        ctx.render("urls/show.jte");
//    }
    public static void showUrl(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(()->new NotFoundResponse("Entity with id " + id + " is not defined"));
        var page = new UrlPage(url);
        ctx.render("urls/show.jte", Collections.singletonMap("page", page));

    }
//    public static void checks(Context ctx) throws SQLException {
//        Long id = Long.valueOf(ctx.pathParamAsClass("id", Integer.class).getOrDefault(null));
//        Url url = UrlRepository.find(id);
//
//        if (url == null) {
//            throw new NotFoundResponse();
//        }
//
//        UrlCheckRepository.save(getCheck(url, ctx));
//        ctx.attribute("url", url);
//        ctx.redirect("/urls/" + id);
//    }

//    public static UrlCheck getCheck(Url url, Context ctx) {
//        String checkedUrlName = url.getName();
//        HttpResponse<String> urlResponse = null;
//        String urlH1Value = "";
//        String urlTitle = "";
//        String urlDescription = "";
//        int urlStatusCode = 0;
//
//        try {
//            urlResponse = Unirest
//                    .get(checkedUrlName)
//                    .asString();
//            urlStatusCode = urlResponse.getStatus();
//            Document urlDoc = Jsoup.parse(urlResponse.getBody());
//
//            if (urlDoc.select("h1").first() != null) {
//                urlH1Value = urlDoc.select("h1").first().text();
//            }
//
//            if (urlDoc.select("title").first() != null) {
//                urlTitle = urlDoc.select("title").first().text();
//            }
//
//            if (!urlDoc.select("meta[name=description]").isEmpty()) {
//                urlDescription = urlDoc.select("meta[name=description]")
//                        .get(0)
//                        .attr("content");
//            }
//
//            ctx.sessionAttribute("flash", "Проверка добавлена");
//            ctx.sessionAttribute("flash-type", "success");
//            ctx.redirect("/");
//
//            return new UrlCheck(urlStatusCode, urlTitle, urlH1Value, urlDescription, url.getId());
//        } catch (Exception e) {
//
//            ctx.sessionAttribute("flash", "Ошибка при проверке URL");
//            ctx.sessionAttribute("flash-type", "danger");
//            ctx.redirect("/");
//
//            return new UrlCheck(urlStatusCode, urlTitle, urlH1Value, urlDescription, url.getId());
//        }
//    }
}

