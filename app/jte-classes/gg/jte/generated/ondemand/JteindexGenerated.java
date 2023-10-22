package gg.jte.generated.ondemand;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {31,31,31,31,31,31,31,31,31,31,31};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\"\n          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n    <title>Main Page</title>\n</head>\n<body>\n<div class=\"container-xl\">\n    <div>\n        <h1>Hello there!</h1>\n        <ul class=\"list-unstyled\">\n            <li>The list is provided for testin the bootstrap</li>\n            <li>It appears completely unstyled.</li>\n            <li>Structurally, it's still a list.</li>\n            <li>However, this style only applies to immediate child elements.</li>\n            <li>Nested lists:\n                <ul>\n                    <li>are unaffected by this style</li>\n                    <li>will still show a bullet</li>\n                    <li>and have appropriate left margin</li>\n                </ul>\n            </li>\n            <li>This may still come in handy in some situations.</li>\n        </ul>\n    </div>\n</div>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
