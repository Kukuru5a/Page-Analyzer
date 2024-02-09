package hexlet.code.controllers;

import hexlet.code.dto.BasePage;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.Collections;

public class RootController {
    public static void page(Context ctx) throws SQLException {
        var page = new BasePage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setColor(ctx.consumeSessionAttribute("color"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
}

