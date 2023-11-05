package gg.jte.generated.ondemand;
import hexlet.code.routes.NamedRoutes;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,6,6,10,10,10,10,10,10,10,10,10,26,26,26,26,26,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, NamedRoutes page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"col-md-10 col-lg-8 mx-auto text-muted\">\n        <h1 class=\"display-3 mb-0\">Анализатор страниц</h1>\n        <p class=\"lead\">Бесплатно проверяйте сайты на SEO пригодность</p>\n        <form");
				var __jte_html_attribute_0 = NamedRoutes.sitesPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\" class=\"rss-form text-body\">\n            <div class=\"row\">\n                <div class=\"col\">\n                    <div class=\"form-floating\">\n                        <input id=\"url-input\" autofocus type=\"text\" required name=\"url\" aria-label=\"url\"\n                               class=\"form-control w-100\" placeholder=\"ссылка\" autocomplete=\"off\">\n                        <label for=\"url-input\">Ссылка</label>\n                    </div>\n                </div>\n                <div class=\"col-auto\">\n                    <button type=\"submit\" class=\"h-100 btn btn-lg btn-primary px-sm-5\">Проверить</button>\n                </div>\n            </div>\n        </form>\n        <p class=\"mt-2 mb-0 text-muted\">Пример: https://www.example.com</p>\n    </div>\n");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		NamedRoutes page = (NamedRoutes)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
