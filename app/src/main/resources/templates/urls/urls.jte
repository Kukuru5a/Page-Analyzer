@import hexlet.code.pages.BasePage
@import hexlet.code.sites.SitesPage
@import hexlet.code.routes.NamedRoutes
@param SitesPage page
@param NamedRoutes routes
@param BasePage pg

@template.layout.page(

content = @`
        <div class="rounded-0 m-0 alert alert-dismissible fade show alert-success" role="alert">
            <p>${pg.getFlash()}</p>
            <p class="m-0">Страница успешно добавлена</p>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>

        <section>

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
                    <tbody>
                    @for(var site : page.getSitesPage())
                        <tr>
                            <td>
                                ${site.getName()}
                            </td>
                            <td>
                                <a href="${NamedRoutes.sitePagePath(site.getId())}">${site.getName()}</a>
                            </td>
                            <td>
                                ${String.valueOf(site.getCreatedAt())}
                            </td>
                        </tr>
                    @endfor
                    </tbody>
                </table>
            </div>

        </section>
`
)