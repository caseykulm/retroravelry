
package com.caseykulm.retroravelry.models.patternauthor;

import com.caseykulm.retroravelry.models.user.SmallUser;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_PatternAuthor extends PatternAuthor {

  private final int favorites_count;
  private final int id;
  private final String name;
  private final int patterns_count;
  private final String permalink;
  private final List<SmallUser> users;

  AutoValue_PatternAuthor(
      int favorites_count,
      int id,
      String name,
      int patterns_count,
      String permalink,
      List<SmallUser> users) {
    this.favorites_count = favorites_count;
    this.id = id;
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
    this.patterns_count = patterns_count;
    if (permalink == null) {
      throw new NullPointerException("Null permalink");
    }
    this.permalink = permalink;
    if (users == null) {
      throw new NullPointerException("Null users");
    }
    this.users = users;
  }

  @Override
  public int favorites_count() {
    return favorites_count;
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
  public int patterns_count() {
    return patterns_count;
  }

  @Override
  public String permalink() {
    return permalink;
  }

  @Override
  public List<SmallUser> users() {
    return users;
  }

  @Override
  public String toString() {
    return "PatternAuthor{"
        + "favorites_count=" + favorites_count + ", "
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "patterns_count=" + patterns_count + ", "
        + "permalink=" + permalink + ", "
        + "users=" + users
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PatternAuthor) {
      PatternAuthor that = (PatternAuthor) o;
      return (this.favorites_count == that.favorites_count())
           && (this.id == that.id())
           && (this.name.equals(that.name()))
           && (this.patterns_count == that.patterns_count())
           && (this.permalink.equals(that.permalink()))
           && (this.users.equals(that.users()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.favorites_count;
    h *= 1000003;
    h ^= this.id;
    h *= 1000003;
    h ^= this.name.hashCode();
    h *= 1000003;
    h ^= this.patterns_count;
    h *= 1000003;
    h ^= this.permalink.hashCode();
    h *= 1000003;
    h ^= this.users.hashCode();
    return h;
  }

}
