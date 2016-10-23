
package com.caseykulm.retroravelry.models.photo;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_Photo extends Photo {

  private final String id;
  private final String medium_url;
  private final String small_url;
  private final String square_url;
  private final String thumbnail_url;
  private final String sort_order;
  private final int x_offset;
  private final int y_offset;

  AutoValue_Photo(
      String id,
      String medium_url,
      String small_url,
      String square_url,
      String thumbnail_url,
      String sort_order,
      int x_offset,
      int y_offset) {
    if (id == null) {
      throw new NullPointerException("Null id");
    }
    this.id = id;
    if (medium_url == null) {
      throw new NullPointerException("Null medium_url");
    }
    this.medium_url = medium_url;
    if (small_url == null) {
      throw new NullPointerException("Null small_url");
    }
    this.small_url = small_url;
    if (square_url == null) {
      throw new NullPointerException("Null square_url");
    }
    this.square_url = square_url;
    if (thumbnail_url == null) {
      throw new NullPointerException("Null thumbnail_url");
    }
    this.thumbnail_url = thumbnail_url;
    if (sort_order == null) {
      throw new NullPointerException("Null sort_order");
    }
    this.sort_order = sort_order;
    this.x_offset = x_offset;
    this.y_offset = y_offset;
  }

  @Override
  public String id() {
    return id;
  }

  @Override
  public String medium_url() {
    return medium_url;
  }

  @Override
  public String small_url() {
    return small_url;
  }

  @Override
  public String square_url() {
    return square_url;
  }

  @Override
  public String thumbnail_url() {
    return thumbnail_url;
  }

  @Override
  public String sort_order() {
    return sort_order;
  }

  @Override
  public int x_offset() {
    return x_offset;
  }

  @Override
  public int y_offset() {
    return y_offset;
  }

  @Override
  public String toString() {
    return "Photo{"
        + "id=" + id + ", "
        + "medium_url=" + medium_url + ", "
        + "small_url=" + small_url + ", "
        + "square_url=" + square_url + ", "
        + "thumbnail_url=" + thumbnail_url + ", "
        + "sort_order=" + sort_order + ", "
        + "x_offset=" + x_offset + ", "
        + "y_offset=" + y_offset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Photo) {
      Photo that = (Photo) o;
      return (this.id.equals(that.id()))
           && (this.medium_url.equals(that.medium_url()))
           && (this.small_url.equals(that.small_url()))
           && (this.square_url.equals(that.square_url()))
           && (this.thumbnail_url.equals(that.thumbnail_url()))
           && (this.sort_order.equals(that.sort_order()))
           && (this.x_offset == that.x_offset())
           && (this.y_offset == that.y_offset());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.id.hashCode();
    h *= 1000003;
    h ^= this.medium_url.hashCode();
    h *= 1000003;
    h ^= this.small_url.hashCode();
    h *= 1000003;
    h ^= this.square_url.hashCode();
    h *= 1000003;
    h ^= this.thumbnail_url.hashCode();
    h *= 1000003;
    h ^= this.sort_order.hashCode();
    h *= 1000003;
    h ^= this.x_offset;
    h *= 1000003;
    h ^= this.y_offset;
    return h;
  }

}
