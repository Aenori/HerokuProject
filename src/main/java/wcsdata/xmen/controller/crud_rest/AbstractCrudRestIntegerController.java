package wcsdata.xmen.controller.crud_rest;

public abstract class AbstractCrudRestIntegerController<E>
        extends AbstractCrudRestController<E, Integer> {
    @Override
    protected Integer parseId(String id) {
        return Integer.parseInt(id);
    }
}
