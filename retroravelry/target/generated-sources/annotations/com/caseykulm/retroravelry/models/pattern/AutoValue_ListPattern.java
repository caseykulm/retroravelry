
package com.caseykulm.retroravelry.models.pattern;

import com.caseykulm.retroravelry.models.patternauthor.PatternAuthor;
import com.caseykulm.retroravelry.models.photo.Photo;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_ListPattern extends ListPattern {

  private final PatternAuthor designer;
  private final Photo first_photo;
  private final boolean free;
  private final int id;
  private final String name;
  private final PatternAuthor pattern_author;
  private final String permalink;
  private final Map<String, String> personal_attributes;

  AutoValue_ListPattern(
      PatternAuthor designer,
      Photo first_photo,
      boolean free,
      int id,
      String name,
      PatternAuthor pattern_author,
      String permalink,
      Map<String, String> personal_attributes) {
    if (designer == null) {
      throw new NullPointerException("Null designer");
    }
    this.designer = designer;
    if (first_photo == null) {
      throw new NullPointerException("Null first_photo");
    }
    this.first_photo = first_photo;
    this.free = free;
    this.id = id;
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
    if (pattern_author == null) {
      throw new NullPointerException("Null pattern_author");
    }
    this.pattern_author = pattern_author;
    if (permalink == null) {
      throw new NullPointerException("Null permalink");
    }
    this.permalink = permalink;
    if (personal_attributes == null) {
      throw new NullPointerException("Null personal_attributes");
    }
    this.personal_attributes = personal_attributes;
  }

  @Override
  public PatternAuthor designer() {
    return designer;
  }

  @Override
  public Photo first_photo() {
    return first_photo;
  }

  @Override
  public boolean free() {
    return free;
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
  public PatternAuthor pattern_author() {
    return pattern_author;
  }

  @Override
  public String permalink() {
    return permalink;
  }

  @Override
  public Map<String, String> personal_attributes() {
    return personal_attributes;
  }

  @Override
  public String toString() {
    return "ListPattern{"
        + "designer=" + designer + ", "
        + "first_photo=" + first_photo + ", "
        + "free=" + free + ", "
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "pattern_author=" + pattern_author + ", "
        + "permalink=" + permalink + ", "
        + "personal_attributes=" + personal_attributes
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ListPattern) {
      ListPattern that = (ListPattern) o;
      return (this.designer.equals(that.designer()))
           && (this.first_photo.equals(that.first_photo()))
           && (this.free == that.free())
           && (this.id == that.id())
           && (this.name.equals(that.name()))
           && (this.pattern_author.equals(that.pattern_author()))
           && (this.permalink.equals(that.permalink()))
           && (this.personal_attributes.equals(that.personal_attributes()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.designer.hashCode();
    h *= 1000003;
    h ^= this.first_photo.hashCode();
    h *= 1000003;
    h ^= this.free ? 1231 : 1237;
    h *= 1000003;
    h ^= this.id;
    h *= 1000003;
    h ^= this.name.hashCode();
    h *= 1000003;
    h ^= this.pattern_author.hashCode();
    h *= 1000003;
    h ^= this.permalink.hashCode();
    h *= 1000003;
    h ^= this.personal_attributes.hashCode();
    return h;
  }

}
