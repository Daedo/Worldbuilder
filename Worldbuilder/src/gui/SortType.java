package gui;

import java.util.Comparator;

import stars.Star;

public enum SortType {
	NAME_FIRST((f1,f2)->(f1.getNameString()+f1.toString()).compareToIgnoreCase(f2.getNameString()+f2.toString())),
	TYPE_FIRST((f1,f2)->(f1.toString()+f1.getNameString()).compareToIgnoreCase(f2.toString()+f2.getNameString()));
	public Comparator<Star> starSorter;
	private SortType(Comparator<Star> sorter) {
		this.starSorter = sorter;
	}
}
