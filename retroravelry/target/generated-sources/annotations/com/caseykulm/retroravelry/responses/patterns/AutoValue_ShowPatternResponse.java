
package com.caseykulm.retroravelry.responses.patterns;

import com.caseykulm.retroravelry.models.pattern.FullPattern;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_ShowPatternResponse extends ShowPatternResponse {

  private final FullPattern patterns;

  AutoValue_ShowPatternResponse(
      FullPattern patterns) {
    if (patterns == null) {
      throw new NullPointerException("Null patterns");
    }
    this.patterns = patterns;
  }

  @Override
  public FullPattern patterns() {
    return patterns;
  }

  @Override
  public String toString() {
    return "ShowPatternResponse{"
        + "patterns=" + patterns
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ShowPatternResponse) {
      ShowPatternResponse that = (ShowPatternResponse) o;
      return (this.patterns.equals(that.patterns()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.patterns.hashCode();
    return h;
  }

}
