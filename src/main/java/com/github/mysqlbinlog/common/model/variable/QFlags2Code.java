package com.github.mysqlbinlog.common.model.variable;


public class QFlags2Code extends StatusVariable {
	private static final long serialVersionUID = 7796764699784318411L;
	private final int flags;

	public QFlags2Code(int flags) {
		this.flags = flags;
	}

	public int getFlags() {
		return flags;
	}

	@Override
	public String toString() {
		return "QFlags2Code [flags=" + flags + "]";
	}
}
