
package com.caseykulm.retroravelry.models.printing;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_ListPrinting extends ListPrinting {

  private final String pattern_source;
  private final String primary_source;

  AutoValue_ListPrinting(
      String pattern_source,
      String primary_source) {
    if (pattern_source == null) {
      throw new NullPointerException("Null pattern_source");
    }
    this.pattern_source = pattern_source;
    if (primary_source == null) {
      throw new NullPointerException("Null primary_source");
    }
    this.primary_source = primary_source;
  }

  @Override
  public String pattern_source() {
    return pattern_source;
  }

  @Override
  public String primary_source() {
    return primary_source;
  }

  @Override
  public String toString() {
    return "ListPrinting{"
        + "pattern_source=" + pattern_source + ", "
        + "primary_source=" + primary_source
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ListPrinting) {
      ListPrinting that = (ListPrinting) o;
      return (this.pattern_source.equals(that.pattern_source()))
           && (this.primary_source.equals(that.primary_source()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.pattern_source.hashCode();
    h *= 1000003;
    h ^= this.primary_source.hashCode();
    return h;
  }

}
