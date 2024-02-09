package gg.jte.generated.ondemand.urls;
import hexlet.code.AppUtils;
import hexlet.code.dto.urls.UrlsPg;
import hexlet.code.routes.NamedRoutes;
public final class JteurlsGenerated {
	public static final String JTE_NAME = "urls/urls.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,5,5,8,8,20,20,22,22,25,25,25,28,28,28,28,28,28,28,28,29,29,29,32,32,33,33,33,34,34,37,37,38,38,38,39,39,42,42,44,44,48,48,48};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPg page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container-lg mt-5\">\n        <h1>Сайты</h1>\n        <table class=\"table table-bordered table-hover mt-3\">\n            <thead>\n            <tr>\n                <th class=\"col-1\">ID</th>\n                <th>Имя</th>\n                <th class=\"col-2\">Последняя проверка</th>\n                <th class=\"col-1\">Код ответа</th>\n            </tr>\n            </thead>\n            ");
				if (!page.getCheckMap().isEmpty()) {
					jteOutput.writeContent("\n                <tbody>\n                ");
					for (var urlWithCheck : page.getCheckMap().entrySet()) {
						jteOutput.writeContent("\n                    <tr>\n                        <td>\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlWithCheck.getKey().getId());
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            <a");
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(NamedRoutes.urlPath(urlWithCheck.getKey().getId()))) {
							jteOutput.writeContent(" href=\"");
							jteOutput.setContext("a", "href");
							jteOutput.writeUserContent(NamedRoutes.urlPath(urlWithCheck.getKey().getId()));
							jteOutput.setContext("a", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                ");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(urlWithCheck.getKey().getName());
						jteOutput.writeContent("</a>\n                        </td>\n                        <td>\n                            ");
						if (urlWithCheck.getValue() != null) {
							jteOutput.writeContent("\n                                ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(String.valueOf(urlWithCheck.getValue().getCreatedAt()));
							jteOutput.writeContent("\n                            ");
						}
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
						if (urlWithCheck.getValue() != null) {
							jteOutput.writeContent("\n                                ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(urlWithCheck.getValue().getStatusCode());
							jteOutput.writeContent("\n                            ");
						}
						jteOutput.writeContent("\n                        </td>\n                    </tr>\n                ");
					}
					jteOutput.writeContent("\n                </tbody>\n            ");
				}
				jteOutput.writeContent("\n        </table>\n    </div>\n\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPg page = (UrlsPg)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
