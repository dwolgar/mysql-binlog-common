package com.github.mysqlbinlog.common.model.variable;

public class QCharsetCode extends StatusVariable {
	private static final long serialVersionUID = 4776026516070806868L;
	private final int characterSetClient;
	private final int collationConnection;
	private final int collationServer;

	public QCharsetCode(int characterSetClient, int collationConnection, int collationServer) {
		this.characterSetClient = characterSetClient;
		this.collationConnection = collationConnection;
		this.collationServer = collationServer;
	}

	public int getCharacterSetClient() {
		return characterSetClient;
	}

	public int getCollationConnection() {
		return collationConnection;
	}

	public int getCollationServer() {
		return collationServer;
	}

	@Override
	public String toString() {
		return "QCharsetCode [characterSetClient=" + characterSetClient
				+ ", collationConnection=" + collationConnection
				+ ", collationServer=" + collationServer + "]";
	}
}
