@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

<title>URL</title>

@template.layout.page(
    page = page,
    content = @`
    @if(page != null && page.getFlash() != null)
        <div class="alert alert-${page.getFlashType()}" role="alert">
            ${page.getFlash()}
        </div>
    @endif
    <div class="container-lg mt-5">
        <h1>Сайт: ${page.getUrl().getName()}</h1>

        <table class="table table-bordered table-hover mt-3">
            <tbody>
            <tr>
                <td>ID</td>
                <td>${page.getUrl().getId()}</td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>${page.getUrl().getName()}</td>
            </tr>
            <tr>
                <td>Дата создания</td>
                <td>${String.valueOf(page.getUrl().getCreatedAt())}</td>
            </tr>
            </tbody>
        </table>

        <h2 class="mt-5">Проверки</h2>
        <form method="post" action="/urls/${page.getUrl().getId()}/checks">
            <button type="submit" class="btn btn-primary">Запустить проверку</button>
        </form>
        <table>
        <thead>
        <tr>
            <th class="col-1">ID</th>
            <th class="col-1">Код ответа</th>
            <th>title</th>
            <th>h1</th>
            <th>description</th>
            <th class="col-2">Дата проверки</th>
        </tr>
        </thead>
        <tbody>
        @for(var url : page.getCheck())
            <tr>
                <td>${url.getId()}</td>
                <td>${url.getStatusCode()}</td>
                <td>${url.getTitle()}</td>
                <td>${url.getH1()}</td>
                <td>${url.getDescription()}</td>
                <td>${url.getCreatedAt().toString()}</td>
            </tr>
        @endfor
        </tbody>
        </table>
    </div>
`
    )
прописать логику getCheck() внутри класса UrlPage
если не получится, уточнить о необходимости реализации темплейтинга