package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlsPg;
import hexlet.code.routes.NamedRoutes;
public final class JteurlsGenerated {
	public static final String JTE_NAME = "urls/urls.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,3,5,5,17,17,18,18,20,20,20,21,21,21,21,21,21,21,21,21,21,21,21,25,25,26,26,29,29,29,29,29,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPg pg) {
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <h1>All urls</h1>\n    <table class=\"table table-striped\">\n        <thead>\n            <tr>\n                <th class=\"col-1\">ID</th>\n                <th class=\"col-1\">URL</th>\n                <th class=\"col-1\">Latest check</th>\n                <th class=\"col-1\">Response code</th>\n            </tr>\n        </thead>\n            <tbody>\n                ");
				if (pg != null) {
					jteOutput.writeContent("\n                    ");
					for (var url : pg.getUrlList()) {
						jteOutput.writeContent("\n                        <tr>\n                            <th class=\"col-1\">");
						jteOutput.setContext("th", null);
						jteOutput.writeUserContent(url.getId());
						jteOutput.writeContent("</th>\n                            <th class=\"col-1\"><a");
						var __jte_html_attribute_0 = NamedRoutes.sitePagePath(url.getId());
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
							jteOutput.writeContent(" href=\"");
							jteOutput.setContext("a", "href");
							jteOutput.writeUserContent(__jte_html_attribute_0);
							jteOutput.setContext("a", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(url.getName());
						jteOutput.writeContent("</a></th>\n                            <th class=\"col-1\">any data</th>\n                            <th class=\"col-1\">any data</th>\n                        </tr>\n                    ");
					}
					jteOutput.writeContent("\n                ");
				}
				jteOutput.writeContent("\n            </tbody>\n    </table>\n");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPg pg = (UrlsPg)params.get("pg");
		render(jteOutput, jteHtmlInterceptor, pg);
	}
}
