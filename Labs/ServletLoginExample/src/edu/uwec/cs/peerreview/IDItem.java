package edu.uwec.cs.peerreview;

public class IDItem implements Comparable<IDItem> {
	public int id;
	public String name;
	
	public IDItem(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public IDItem() {
		this(-1,"");
	}
	
	public String toString() {
		return name;
	}
	public boolean equals(Object o) {
		if (o instanceof IDItem) {
			IDItem i = (IDItem)o;
			if (i.id == this.id) {
				return true;
			}
		}
		return false;
	}
	
	public int compareTo(IDItem o) {
		if (this.name.compareTo(o.name) == 0) {
			if (this.id < o.id) {
				return -1;
			} else if (this.id > o.id) {
				return 1;
			} else {
				return 0;
			}
		}
		return this.name.compareTo(o.name);
	}
}
