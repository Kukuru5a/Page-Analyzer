package gg.jte.generated.ondemand;
import hexlet.code.routes.NamedRoutes;
import hexlet.code.dto.BasePage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,7,7,8,8,10,10,10,12,12,16,16,16,16,16,16,16,16,16,32,32,32,32,32,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BasePage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				if (page != null && page.getFlashType().equals("danger")) {
					jteOutput.writeContent("\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-danger\" role=\"alert\" >\n            ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("\n        </div>\n    ");
				}
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
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BasePage page = (BasePage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
