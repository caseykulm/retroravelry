package com.caseykulm.retroravelry.responses.patterns;

import com.caseykulm.retroravelry.models.pattern.FullPattern;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class ShowPatternResponse {

  public abstract FullPattern patterns();

}
