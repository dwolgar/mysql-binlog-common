package com.github.mysqlbinlog.common.model.event.extra;

import java.io.Serializable;

public final class Metadata implements Serializable {
  private static final long serialVersionUID = 4634414541769527837L;

  private final byte[] type;
  private final int[] metadata;

  public Metadata(byte[] type, int[] metadata) {
    this.type = type;
    this.metadata = metadata;
  }

  public byte getType(int column) {
    return this.type[column];
  }
  public int getMetadata(int column) {
    return this.metadata[column];
  }
}
