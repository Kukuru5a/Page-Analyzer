package gg.jte.generated.ondemand.urls;
import hexlet.code.AppUtils;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.routes.NamedRoutes;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,5,5,8,8,10,10,10,15,15,15,19,19,19,23,23,23,28,28,28,28,40,40,42,42,45,45,45,48,48,48,51,51,51,54,54,54,57,57,57,60,60,60,63,63,65,65,68,68,68};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container-lg mt-5\">\n        <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\n        <table class=\"table table-bordered table-hover mt-3\">\n            <tbody>\n            <tr>\n                <td>ID</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\n            </tr>\n            <tr>\n                <td>Имя</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\n            </tr>\n            <tr>\n                <td>Дата создания</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(String.valueOf(page.getUrl().getCreatedAt()));
				jteOutput.writeContent("</td>\n            </tr>\n            </tbody>\n        </table>\n        <h2 class=\"mt-5\">Проверки</h2>\n        <form method=\"post\" action=\"");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(NamedRoutes.urlPath(page.getUrl().getId()));
				jteOutput.setContext("form", null);
				jteOutput.writeContent("/checks\">\n            <button type=\"submit\" class=\"btn btn-primary\">Запустить проверку</button>\n        </form>\n        <table class=\"table table-bordered table-hover mt-3\">\n            <thead>\n            <th class=\"col-1\">ID</th>\n            <th class=\"col-1\">Код ответа</th>\n            <th>title</th>\n            <th>h1</th>\n            <th>description</th>\n            <th class=\"col-2\">Дата проверки</th>\n            </thead>\n            ");
				if (!page.getCheck().isEmpty()) {
					jteOutput.writeContent("\n                <tbody>\n                ");
					for (var urlCheck : page.getCheck()) {
						jteOutput.writeContent("\n                    <tr>\n                        <td>\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getId());
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getStatusCode());
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getTitle());
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getH1());
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getDescription());
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(String.valueOf(urlCheck.getCreatedAt()));
						jteOutput.writeContent("\n                        </td>\n                    </tr>\n                ");
					}
					jteOutput.writeContent("\n                </tbody>\n            ");
				}
				jteOutput.writeContent("\n        </table>\n    </div>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
