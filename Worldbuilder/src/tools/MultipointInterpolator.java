package tools;

import java.util.Collections;
import java.util.Vector;

public class MultipointInterpolator<T> {
	
	private class DoublePair<V> implements Comparable<DoublePair<V>>{
		public V val;
		public double dVal;
		
		public DoublePair(double doubleVal,V vVal) {
			this.dVal = doubleVal;
			this.val  = vVal;
		}

		@Override
		public int compareTo(MultipointInterpolator<T>.DoublePair<V> o) {
			return Double.compare(this.dVal, o.dVal);
		}
	}
	
	private Interpolator<T> interpolator;
	private Vector<DoublePair<T>> vals;
	
	
	public MultipointInterpolator(Interpolator<T> interp) {
		this.interpolator = interp;
		this.vals = new Vector<>();
	}
	
	public void addDatapoint(double position,T value) {
		this.vals.addElement(new DoublePair<>(position, value));
		Collections.sort(this.vals);
	}
	
	public T getInterpolation(double pos) {
		
		for(int i=0;i<this.vals.size();i++) {
			DoublePair<T> currentPair = this.vals.get(i);
			if(currentPair.dVal>=pos) {
				if(i>0) {
					DoublePair<T> prevPair = this.vals.get(i-1);
					double stepPos = (pos-prevPair.dVal)/(currentPair.dVal-prevPair.dVal);
					//Interpolation prevPair currentPair
					return this.interpolator.interpolate(prevPair.val, currentPair.val, stepPos);
				}
				//No Interpolation
				return currentPair.val;
			}
		}
		return this.vals.lastElement().val;
	}
}
