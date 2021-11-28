package wcsdata.xmen.controller.crud_html;

public abstract class AbstractCrudHtmlIntegerController<E>
        extends AbstractCrudHtmlController<E, Integer> {
    @Override
    protected Integer parseId(String id) {
        return Integer.parseInt(id);
    }
}
