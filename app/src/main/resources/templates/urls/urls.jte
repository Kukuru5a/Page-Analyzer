@import hexlet.code.dto.urls.UrlsPg
@import hexlet.code.routes.NamedRoutes
@param UrlsPg pg
@template.layout.page(
content = @`
    <h1>All urls</h1>
    <table class="table table-striped">
        <thead>
            <tr>
                <th class="col-1">ID</th>
                <th class="col-1">URL</th>
                <th class="col-1">Latest check</th>
                <th class="col-1">Response code</th>
            </tr>
        </thead>
            <tbody>
                @if(pg != null)
                    @for(var url : pg.getUrlList())
                        <tr>
                            <th class="col-1">${url.getId()}</th>
                            <th class="col-1"><a href="${NamedRoutes.sitePagePath(url.getId())}">${url.getName()}</a></th>
                            <th class="col-1">any data</th>
                            <th class="col-1">any data</th>
                        </tr>
                    @endfor
                @endif
            </tbody>
    </table>
`
)