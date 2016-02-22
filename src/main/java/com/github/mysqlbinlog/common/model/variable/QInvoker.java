package com.github.mysqlbinlog.common.model.variable;

public class QInvoker extends StatusVariable {
	private static final long serialVersionUID = 2983386044794889984L;
	private final String user;
	private final String host;

	public QInvoker(String user, String host) {
		this.user = user;
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public String getHost() {
		return host;
	}

	@Override
	public String toString() {
		return "QInvoker [user=" + user + ", host=" + host + "]";
	}
}
