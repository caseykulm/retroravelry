package com.caseykulm.retroravelry.models.patterncategory;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class PatternCategory {

  public abstract int id();
  public abstract String name();
  public abstract PatternCategory parent();
  public abstract String permalink();

}
