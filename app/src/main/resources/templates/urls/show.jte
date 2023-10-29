@import hexlet.code.models.Url
@import hexlet.code.routes.NamedRoutes
@param Url pg
@template.layout.page(
content = @`
    @if(pg != null)
        <h1>Site: ${pg.getName()}</h1>
    @endif
`
)