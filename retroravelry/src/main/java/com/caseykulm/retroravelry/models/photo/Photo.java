package com.caseykulm.retroravelry.models.photo;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class Photo {

  public abstract String id();
  public abstract String medium_url();
  public abstract String small_url();
  public abstract String square_url();
  public abstract String thumbnail_url();
  public abstract String sort_order();
  public abstract int x_offset();
  public abstract int y_offset();

}
