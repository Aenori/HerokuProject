package wcsdata.xmen.controller.crud_rest;

import wcsdata.xmen.controller.crud_html.AbstractCrudHtmlController;

public abstract class AbstractCrudRestIntegerController<E>
        extends AbstractCrudRestController<E, Integer> {
    @Override
    protected Integer parseId(String id) {
        return Integer.parseInt(id);
    }
}
