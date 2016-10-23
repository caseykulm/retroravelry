
package com.caseykulm.retroravelry.models.patterncategory;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_PatternCategory extends PatternCategory {

  private final int id;
  private final String name;
  private final PatternCategory parent;
  private final String permalink;

  AutoValue_PatternCategory(
      int id,
      String name,
      PatternCategory parent,
      String permalink) {
    this.id = id;
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
    if (parent == null) {
      throw new NullPointerException("Null parent");
    }
    this.parent = parent;
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
  public String name() {
    return name;
  }

  @Override
  public PatternCategory parent() {
    return parent;
  }

  @Override
  public String permalink() {
    return permalink;
  }

  @Override
  public String toString() {
    return "PatternCategory{"
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "parent=" + parent + ", "
        + "permalink=" + permalink
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PatternCategory) {
      PatternCategory that = (PatternCategory) o;
      return (this.id == that.id())
           && (this.name.equals(that.name()))
           && (this.parent.equals(that.parent()))
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
    h ^= this.name.hashCode();
    h *= 1000003;
    h ^= this.parent.hashCode();
    h *= 1000003;
    h ^= this.permalink.hashCode();
    return h;
  }

}
