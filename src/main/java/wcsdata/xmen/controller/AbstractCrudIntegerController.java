package wcsdata.xmen.controller;

public abstract class AbstractCrudIntegerController<E> extends AbstractCrudController<E, Integer> {
    @Override
    protected Integer parseId(String id) {
        return Integer.parseInt(id);
    }
}
