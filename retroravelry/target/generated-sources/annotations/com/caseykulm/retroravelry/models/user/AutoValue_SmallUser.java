
package com.caseykulm.retroravelry.models.user;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_SmallUser extends SmallUser {

  private final int id;
  private final String large_photo_url;
  private final String photo_url;
  private final String small_photo_url;
  private final String tiny_photo_url;
  private final String username;

  AutoValue_SmallUser(
      int id,
      String large_photo_url,
      String photo_url,
      String small_photo_url,
      String tiny_photo_url,
      String username) {
    this.id = id;
    if (large_photo_url == null) {
      throw new NullPointerException("Null large_photo_url");
    }
    this.large_photo_url = large_photo_url;
    if (photo_url == null) {
      throw new NullPointerException("Null photo_url");
    }
    this.photo_url = photo_url;
    if (small_photo_url == null) {
      throw new NullPointerException("Null small_photo_url");
    }
    this.small_photo_url = small_photo_url;
    if (tiny_photo_url == null) {
      throw new NullPointerException("Null tiny_photo_url");
    }
    this.tiny_photo_url = tiny_photo_url;
    if (username == null) {
      throw new NullPointerException("Null username");
    }
    this.username = username;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public String large_photo_url() {
    return large_photo_url;
  }

  @Override
  public String photo_url() {
    return photo_url;
  }

  @Override
  public String small_photo_url() {
    return small_photo_url;
  }

  @Override
  public String tiny_photo_url() {
    return tiny_photo_url;
  }

  @Override
  public String username() {
    return username;
  }

  @Override
  public String toString() {
    return "SmallUser{"
        + "id=" + id + ", "
        + "large_photo_url=" + large_photo_url + ", "
        + "photo_url=" + photo_url + ", "
        + "small_photo_url=" + small_photo_url + ", "
        + "tiny_photo_url=" + tiny_photo_url + ", "
        + "username=" + username
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SmallUser) {
      SmallUser that = (SmallUser) o;
      return (this.id == that.id())
           && (this.large_photo_url.equals(that.large_photo_url()))
           && (this.photo_url.equals(that.photo_url()))
           && (this.small_photo_url.equals(that.small_photo_url()))
           && (this.tiny_photo_url.equals(that.tiny_photo_url()))
           && (this.username.equals(that.username()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.id;
    h *= 1000003;
    h ^= this.large_photo_url.hashCode();
    h *= 1000003;
    h ^= this.photo_url.hashCode();
    h *= 1000003;
    h ^= this.small_photo_url.hashCode();
    h *= 1000003;
    h ^= this.tiny_photo_url.hashCode();
    h *= 1000003;
    h ^= this.username.hashCode();
    return h;
  }

}
