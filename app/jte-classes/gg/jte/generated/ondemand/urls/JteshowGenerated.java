package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlPage;
import gg.jte.Content;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {1,1,2,3,3,3,6,6,9,9,11,11,11,17,17,17,21,21,21,25,25,25,45,45,45,49,49,49,49,49,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page, Content content) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container-lg mt-5\">\n        <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\n\n        <table class=\"table table-bordered table-hover mt-3\">\n            <tbody>\n            <tr>\n                <td>ID</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\n            </tr>\n            <tr>\n                <td>Имя</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\n            </tr>\n            <tr>\n                <td>Дата создания</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(String.valueOf(page.getUrl().getCreatedAt()));
				jteOutput.writeContent("</td>\n            </tr>\n            </tbody>\n        </table>\n\n        <h2 class=\"mt-5\">Проверки</h2>\n        <form method=\"post\" action=\"/urls/1/checks\">\n            <button type=\"submit\" class=\"btn btn-primary\">Запустить проверку</button>\n        </form>\n\n        <table class=\"table table-bordered table-hover mt-3\">\n            <thead>\n            <th class=\"col-1\">ID</th>\n            <th class=\"col-1\">Код ответа</th>\n            <th>title</th>\n            <th>h1</th>\n            <th>description</th>\n            <th class=\"col-2\">Дата проверки</th>\n            </thead>\n            <tbody>\n            ");
				jteOutput.setContext("tbody", null);
				jteOutput.writeUserContent(content);
				jteOutput.writeContent("\n            </tbody>\n        </table>\n    </div>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		Content content = (Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, page, content);
	}
}
