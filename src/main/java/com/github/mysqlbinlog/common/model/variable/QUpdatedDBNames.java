package com.github.mysqlbinlog.common.model.variable;

import java.util.Arrays;

public class QUpdatedDBNames extends StatusVariable {
	private static final long serialVersionUID = -3391093668152198594L;
	private final int accessedDbCount;
	private final String[] accessedDbs;

	public QUpdatedDBNames(int accessedDbCount, String[] accessedDbs) {
		this.accessedDbCount = accessedDbCount;
		this.accessedDbs = accessedDbs;
	}

	public int getAccessedDbCount() {
		return accessedDbCount;
	}

	public String[] getAccessedDbs() {
		return accessedDbs;
	}

	@Override
	public String toString() {
		return "QUpdatedDBNames [accessedDbCount=" + accessedDbCount
				+ ", accessedDbs=" + Arrays.toString(accessedDbs) + "]";
	}
}
