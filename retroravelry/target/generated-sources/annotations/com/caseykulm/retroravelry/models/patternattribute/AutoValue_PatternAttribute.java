
package com.caseykulm.retroravelry.models.patternattribute;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_PatternAttribute extends PatternAttribute {

  private final int id;
  private final String permalink;

  AutoValue_PatternAttribute(
      int id,
      String permalink) {
    this.id = id;
    if (permalink == null) {
      throw new NullPointerException("Null permalink");
    }
    this.permalink = permalink;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public String permalink() {
    return permalink;
  }

  @Override
  public String toString() {
    return "PatternAttribute{"
        + "id=" + id + ", "
        + "permalink=" + permalink
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PatternAttribute) {
      PatternAttribute that = (PatternAttribute) o;
      return (this.id == that.id())
           && (this.permalink.equals(that.permalink()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.id;
    h *= 1000003;
    h ^= this.permalink.hashCode();
    return h;
  }

}
