@import hexlet.code.routes.NamedRoutes
@param NamedRoutes page

@template.layout.page(

content = @`
    <form action="/urls" method="post">
        <input type="text" required name="url" placeholder="URL">
        <button type="submit" name="btn">Check</button>
    </form>
`
)