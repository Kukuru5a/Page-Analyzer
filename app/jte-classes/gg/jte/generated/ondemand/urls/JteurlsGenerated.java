package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlsPg;
public final class JteurlsGenerated {
	public static final String JTE_NAME = "urls/urls.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,6,6,18,18,20,20,23,23,23,26,26,26,26,26,26,26,29,29,30,30,30,31,31,34,34,35,35,35,36,36,39,39,41,41,45,45,45};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPg page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container-lg mt-5\">\n        <h1>Сайты</h1>\n        <table class=\"table table-bordered table-hover mt-3\">\n            <thead>\n            <tr>\n                <th class=\"col-1\">ID</th>\n                <th>Имя</th>\n                <th class=\"col-2\">Последняя проверка</th>\n                <th class=\"col-1\">Код ответа</th>\n            </tr>\n            </thead>\n            ");
				if (!page.getUrlList().isEmpty()) {
					jteOutput.writeContent("\n                <tbody>\n                ");
					for (var url : page.getUrlList()) {
						jteOutput.writeContent("\n                    <tr>\n                        <td>\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getId());
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            <a href=\"urls/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(url.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(url.getName());
						jteOutput.writeContent("</a>\n                        </td>\n                        <td>\n                            ");
						if (url.getLastCheck() != null) {
							jteOutput.writeContent("\n                                ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(String.valueOf(url.getLastCheck().getCreatedAt()));
							jteOutput.writeContent("\n                            ");
						}
						jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
						if (url.getLastCheck() != null) {
							jteOutput.writeContent("\n                                ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(url.getLastCheck().getStatusCode());
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
