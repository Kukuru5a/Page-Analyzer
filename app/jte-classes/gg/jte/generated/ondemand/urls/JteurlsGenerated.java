package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlsPg;
public final class JteurlsGenerated {
	public static final String JTE_NAME = "urls/urls.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,3,5,5,6,6,7,7,7,7,8,8,8,10,10,24,24,27,27,27,28,28,28,28,28,28,28,29,29,29,30,30,30,33,33,36,36,36,36,36,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPg page) {
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				if (page != null && page.getFlash() != null) {
					jteOutput.writeContent("\n        <div class=\"alert alert-");
					jteOutput.setContext("div", "class");
					jteOutput.writeUserContent(page.getFlashType());
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\" role=\"alert\">\n            ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("\n        </div>\n    ");
				}
				jteOutput.writeContent("\n\n    <div class=\"container-lg mt-5\">\n        <h1>Сайты</h1>\n\n        <table class=\"table table-bordered table-hover mt-3\">\n            <thead>\n            <tr>\n                <th class=\"col-1\">ID</th>\n                <th>Имя</th>\n                <th class=\"col-2\">Последняя проверка</th>\n                <th class=\"col-1\">Код ответа</th>\n            </tr>\n            </thead>\n            ");
				for (var url : page.getUrlList()) {
					jteOutput.writeContent("\n            <tbody>\n            <tr>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</td>\n                <td><a href=\"urls/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(url.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a></td>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(page.getCheckMap().isEmpty() || page.getCheckMap().get(url.getId()) == null ? "" : String.valueOf(page.getCheckMap().get(url.getId()).getCreatedAt()));
					jteOutput.writeContent("</td>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(page.getCheckMap().isEmpty() || page.getCheckMap().get(url.getId()) == null ? "" : String.valueOf(page.getCheckMap().get(url.getId()).getStatusCode()));
					jteOutput.writeContent("</td>\n            </tr>\n            </tbody>\n            ");
				}
				jteOutput.writeContent("\n        </table>\n    </div>\n");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPg page = (UrlsPg)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
