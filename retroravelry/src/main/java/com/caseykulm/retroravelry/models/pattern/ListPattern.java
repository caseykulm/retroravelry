package com.caseykulm.retroravelry.models.pattern;

import com.caseykulm.retroravelry.models.patternauthor.PatternAuthor;
import com.caseykulm.retroravelry.models.photo.Photo;
import com.google.auto.value.AutoValue;
import java.util.Map;

@AutoValue public abstract class ListPattern {

  public abstract PatternAuthor designer();
  public abstract Photo first_photo();
  public abstract boolean free();
  public abstract int id();
  public abstract String name();
  public abstract PatternAuthor pattern_author();
  public abstract String permalink();
  public abstract Map<String, String> personal_attributes();

}
