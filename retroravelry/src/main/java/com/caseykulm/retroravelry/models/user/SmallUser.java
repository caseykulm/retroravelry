package com.caseykulm.retroravelry.models.user;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class SmallUser {

  public abstract int id();
  public abstract String large_photo_url();
  public abstract String photo_url();
  public abstract String small_photo_url();
  public abstract String tiny_photo_url();
  public abstract String username();

}
