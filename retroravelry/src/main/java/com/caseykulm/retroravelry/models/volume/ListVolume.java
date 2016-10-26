package com.caseykulm.retroravelry.models.volume;

import com.caseykulm.retroravelry.models.photo.Photo;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class ListVolume {

  public abstract String author_name();
  public abstract int cover_image_size();
  public abstract String cover_image_url();
  public abstract Photo first_photo();
  public abstract boolean has_downloads();
  public abstract int id();
  public abstract int pattern_id();
  public abstract int pattern_source_id();
  public abstract int patterns_count();
  public abstract String small_image_url();
  public abstract String square_image_url();
  public abstract String title();

}
