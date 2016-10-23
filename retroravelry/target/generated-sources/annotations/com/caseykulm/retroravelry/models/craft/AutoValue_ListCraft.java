
package com.caseykulm.retroravelry.models.craft;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_ListCraft extends ListCraft {

  private final int id;
  private final String name;
  private final String permalink;

  AutoValue_ListCraft(
      int id,
      String name,
      String permalink) {
    this.id = id;
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
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
  public String permalink() {
    return permalink;
  }

  @Override
  public String toString() {
    return "ListCraft{"
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "permalink=" + permalink
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ListCraft) {
      ListCraft that = (ListCraft) o;
      return (this.id == that.id())
           && (this.name.equals(that.name()))
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
    h ^= this.permalink.hashCode();
    return h;
  }

}
