package hexlet.code.controllers;

import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPg;
import hexlet.code.models.Url;
import hexlet.code.models.UrlCheck;
import hexlet.code.repositories.UrlCheckRepository;
import hexlet.code.repositories.UrlRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UrlController {

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

    //adding list OK
    public static void urlList(Context ctx) throws SQLException {
        List<Url> urls = UrlRepository.getEntities();
        var page = new UrlsPg(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/urls.jte", Collections.singletonMap("page", page));
    }


    public static void showUrl(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id " + id + " is not defined"));
        var page = new UrlPage(url);
        ctx.render("urls/show.jte", Collections.singletonMap("page", page));
    }
//    public static void checks(Context ctx) throws SQLException {
//        Long id = Long.valueOf(ctx.pathParamAsClass("id", Integer.class).getOrDefault(null));
//        Optional<Url> url = UrlRepository.find(id);
//
//        if (url == null) {
//            throw new NotFoundResponse();
//        }
//
//        UrlCheckRepository.save(getCheck(url, ctx));
//        ctx.attribute("url", url);
//        ctx.redirect("/urls/" + id);
//    }
//    public static UrlCheck getCheck(Optional<Url> url, Context ctx) {
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
