package tools;

public interface Interpolator<T> {
	public T interpolate(T t0, T t1, double v);
}
