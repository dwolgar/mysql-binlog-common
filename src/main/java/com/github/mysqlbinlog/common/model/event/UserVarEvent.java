package com.github.mysqlbinlog.common.model.event;

import com.github.mysqlbinlog.common.model.variable.UserVariable;



/**
 * USER_VAR_EVENT
 * 
 * Written every time a statement uses a user variable; precedes other events for the statement. 
 * Indicates the value to use for the user variable in the next statement. 
 * This is written only before a QUERY_EVENT and is not used with row-based logging.
 */
public final class UserVarEvent extends BinlogEvent {
	private static final long serialVersionUID = -8924783249806728611L;

	private String varName;
	private int isNull;
	private int varType;
	private int varCollation;
	private int varValueLength;
	private UserVariable varValue;

	public UserVarEvent() {}

	public UserVarEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}

	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}

	public int getIsNull() {
		return isNull;
	}
	public void setIsNull(int isNull) {
		this.isNull = isNull;
	}

	public int getVarType() {
		return varType;
	}
	public void setVarType(int variableType) {
		this.varType = variableType;
	}

	public int getVarCollation() {
		return varCollation;
	}
	public void setVarCollation(int varCollation) {
		this.varCollation = varCollation;
	}

	public int getVarValueLength() {
		return varValueLength;
	}
	public void setVarValueLength(int varValueLength) {
		this.varValueLength = varValueLength;
	}

	public UserVariable getVarValue() {
		return varValue;
	}
	public void setVarValue(UserVariable varValue) {
		this.varValue = varValue;
	}
	
	@Override
	public String toString() {
		return "UserVarEvent [varName=" + varName + ", isNull=" + isNull
				+ ", varType=" + varType + ", varCollation=" + varCollation
				+ ", varValueLength=" + varValueLength + ", varValue="
				+ varValue + "]";
	}

}
