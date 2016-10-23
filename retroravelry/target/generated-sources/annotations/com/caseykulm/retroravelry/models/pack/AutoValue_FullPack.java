
package com.caseykulm.retroravelry.models.pack;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_FullPack extends FullPack {

  private final String color_family_id;
  private final String colorway;
  private final String dye_lot;
  private final int id;
  private final String personal_name;
  private final int project_id;
  private final int shop_id;
  private final String shop_name;
  private final String skeins;
  private final int stash_id;
  private final int yarn_id;
  private final String yarn_name;

  AutoValue_FullPack(
      String color_family_id,
      String colorway,
      String dye_lot,
      int id,
      String personal_name,
      int project_id,
      int shop_id,
      String shop_name,
      String skeins,
      int stash_id,
      int yarn_id,
      String yarn_name) {
    if (color_family_id == null) {
      throw new NullPointerException("Null color_family_id");
    }
    this.color_family_id = color_family_id;
    if (colorway == null) {
      throw new NullPointerException("Null colorway");
    }
    this.colorway = colorway;
    if (dye_lot == null) {
      throw new NullPointerException("Null dye_lot");
    }
    this.dye_lot = dye_lot;
    this.id = id;
    if (personal_name == null) {
      throw new NullPointerException("Null personal_name");
    }
    this.personal_name = personal_name;
    this.project_id = project_id;
    this.shop_id = shop_id;
    if (shop_name == null) {
      throw new NullPointerException("Null shop_name");
    }
    this.shop_name = shop_name;
    if (skeins == null) {
      throw new NullPointerException("Null skeins");
    }
    this.skeins = skeins;
    this.stash_id = stash_id;
    this.yarn_id = yarn_id;
    if (yarn_name == null) {
      throw new NullPointerException("Null yarn_name");
    }
    this.yarn_name = yarn_name;
  }

  @Override
  public String color_family_id() {
    return color_family_id;
  }

  @Override
  public String colorway() {
    return colorway;
  }

  @Override
  public String dye_lot() {
    return dye_lot;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public String personal_name() {
    return personal_name;
  }

  @Override
  public int project_id() {
    return project_id;
  }

  @Override
  public int shop_id() {
    return shop_id;
  }

  @Override
  public String shop_name() {
    return shop_name;
  }

  @Override
  public String skeins() {
    return skeins;
  }

  @Override
  public int stash_id() {
    return stash_id;
  }

  @Override
  public int yarn_id() {
    return yarn_id;
  }

  @Override
  public String yarn_name() {
    return yarn_name;
  }

  @Override
  public String toString() {
    return "FullPack{"
        + "color_family_id=" + color_family_id + ", "
        + "colorway=" + colorway + ", "
        + "dye_lot=" + dye_lot + ", "
        + "id=" + id + ", "
        + "personal_name=" + personal_name + ", "
        + "project_id=" + project_id + ", "
        + "shop_id=" + shop_id + ", "
        + "shop_name=" + shop_name + ", "
        + "skeins=" + skeins + ", "
        + "stash_id=" + stash_id + ", "
        + "yarn_id=" + yarn_id + ", "
        + "yarn_name=" + yarn_name
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof FullPack) {
      FullPack that = (FullPack) o;
      return (this.color_family_id.equals(that.color_family_id()))
           && (this.colorway.equals(that.colorway()))
           && (this.dye_lot.equals(that.dye_lot()))
           && (this.id == that.id())
           && (this.personal_name.equals(that.personal_name()))
           && (this.project_id == that.project_id())
           && (this.shop_id == that.shop_id())
           && (this.shop_name.equals(that.shop_name()))
           && (this.skeins.equals(that.skeins()))
           && (this.stash_id == that.stash_id())
           && (this.yarn_id == that.yarn_id())
           && (this.yarn_name.equals(that.yarn_name()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.color_family_id.hashCode();
    h *= 1000003;
    h ^= this.colorway.hashCode();
    h *= 1000003;
    h ^= this.dye_lot.hashCode();
    h *= 1000003;
    h ^= this.id;
    h *= 1000003;
    h ^= this.personal_name.hashCode();
    h *= 1000003;
    h ^= this.project_id;
    h *= 1000003;
    h ^= this.shop_id;
    h *= 1000003;
    h ^= this.shop_name.hashCode();
    h *= 1000003;
    h ^= this.skeins.hashCode();
    h *= 1000003;
    h ^= this.stash_id;
    h *= 1000003;
    h ^= this.yarn_id;
    h *= 1000003;
    h ^= this.yarn_name.hashCode();
    return h;
  }

}
