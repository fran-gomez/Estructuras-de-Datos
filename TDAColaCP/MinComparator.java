package TDAColaCP;

import java.util.Comparator;

public class MinComparator<K extends Comparable<K>> implements Comparator<K> {

	public int compare(K o1, K o2) {
		return o2.compareTo(o1);
	}

}
