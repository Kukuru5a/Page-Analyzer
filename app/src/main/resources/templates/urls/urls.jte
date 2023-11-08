@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.dto.urls.UrlsPg

@param UrlsPg page
@param UrlPage urlPage
@template.layout.page(
content = @`
    @if(page != null && page.getFlash() != null)
        <div class="alert alert-${page.getFlashType()}" role="alert">
            ${page.getFlash()}
        </div>
    @endif

    <div class="container-lg mt-5">
        <h1>Сайты</h1>

        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th class="col-1">ID</th>
                <th>Имя</th>
                <th class="col-2">Последняя проверка</th>
                <th class="col-1">Код ответа</th>
            </tr>
            </thead>
            @for(var url : page.getUrlList())
            <tbody>
            <tr>
                <td>${url.getId()}</td>
                <td><a href="urls/${url.getId()}">${url.getName()}</a></td>
                <td>${page.getCheckMap().isEmpty() || page.getCheckMap().get(url.getId()) == null ? "" : String.valueOf(page.getCheckMap().get(url.getId()).getCreatedAt())}</td>
                <td>${page.getCheckMap().isEmpty() || page.getCheckMap().get(url.getId()) == null ? "" : String.valueOf(page.getCheckMap().get(url.getId()).getStatusCode())}</td>
            </tr>
            </tbody>
            @endfor
        </table>
    </div>
`
)