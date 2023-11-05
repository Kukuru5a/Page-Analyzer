@import hexlet.code.models.UrlCheck
@param UrlCheck page

@template.urls.showjte(
    page = page,
    content = @`
    <tr>
        <td>${page.getUrlId()}</td>
        <td></td>
        <td>${page.getH1()}</td>
        <td>${page.getDescription()}</td>
    </tr>
`
    )
