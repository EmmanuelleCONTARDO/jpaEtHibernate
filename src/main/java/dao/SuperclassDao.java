package dao;

public interface SuperclassDao<T> {

	public T create(T object);

	public T getById(Long id);

	public T update(T object);

	public void deleteById(Long id);

}