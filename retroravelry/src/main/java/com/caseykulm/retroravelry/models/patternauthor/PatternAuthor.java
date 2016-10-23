package com.caseykulm.retroravelry.models.patternauthor;

import com.caseykulm.retroravelry.models.user.SmallUser;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue public abstract class PatternAuthor {

  public abstract int favorites_count();
  public abstract int id();
  public abstract String name();
  public abstract int patterns_count();
  public abstract String permalink();
  public abstract List<SmallUser> users();

}
