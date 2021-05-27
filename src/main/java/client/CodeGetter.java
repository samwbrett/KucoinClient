package client;

@FunctionalInterface
public interface CodeGetter<T> {

    Long getCode(T obj);
}
