package com.caseykulm.retroravelry.models.pack;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class FullPack {

  public abstract String color_family_id();
  public abstract String colorway();
  public abstract String dye_lot();
  public abstract int id();
  public abstract String personal_name();
  public abstract int project_id();
  public abstract int shop_id();
  public abstract String shop_name();
  public abstract String skeins();
  public abstract int stash_id();
  public abstract int yarn_id();
  public abstract String yarn_name();

}
