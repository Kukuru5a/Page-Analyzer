package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import hexlet.code.dto.BasePage;
import hexlet.code.routes.NamedRoutes;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,31,31,31,31,31,31,31,31,31,31,32,32,32,32,32,32,32,32,32,41,41,42,42,42,42,43,43,43,45,45,46,46,46,57,57,57,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, BasePage page) {
		jteOutput.writeContent("<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\"\n          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n    <title>Page Analizer</title>\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n          integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">\n    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\"\n            integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\"\n            crossorigin=\"anonymous\"></script>\n</head>\n<body class=\"d-flex flex-column min-vh-100\">\n    <header>\n        <div class=\"content\">\n            <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n                <div class=\"container-fluid\">\n                    <a class=\"navbar-brand\" href=\"/\">Анализатор страниц</a>\n                    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\"\n                            aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n                        <span class=\"navbar-toggler-icon\"></span>\n                    </button>\n                    <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n                        <div class=\"navbar-nav\">\n                            <a class=\"nav-link\"");
		var __jte_html_attribute_0 = NamedRoutes.homePagePath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Главная</a>\n                            <a class=\"nav-link\"");
		var __jte_html_attribute_1 = NamedRoutes.sitesPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Сайты</a>\n                        </div>\n                    </div>\n                </div>\n            </nav>\n        </div>\n    </header>\n\n<main class=\"flex-grow-1\">\n    ");
		if (page != null && page.getFlash() != null && page.getFlash().contains("Некорректный URL")) {
			jteOutput.writeContent("\n        <div class=\"alert alert-");
			jteOutput.setContext("div", "class");
			jteOutput.writeUserContent(page.getFlashType());
			jteOutput.setContext("div", null);
			jteOutput.writeContent("\" role=\"alert\">\n            ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(page.getFlash());
			jteOutput.writeContent("\n        </div>\n    ");
		}
		jteOutput.writeContent("\n    ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n</main>\n    <footer class=\"footer border-top py-3 mt-5 bg-light\">\n        <div class=\"container-xl\">\n            <div class=\"text-center\">\n                created by\n                <a href=\"https://github.com/Kukuru5a\" target=\"_blank\">Kukuru5a</a>\n            </div>\n        </div>\n    </footer>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, page);
	}
}
