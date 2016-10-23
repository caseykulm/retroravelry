
package com.caseykulm.retroravelry.responses.patterns;

import com.caseykulm.retroravelry.models.pattern.ListPattern;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_SearchPatternsResponse extends SearchPatternsResponse {

  private final List<ListPattern> patterns;

  AutoValue_SearchPatternsResponse(
      List<ListPattern> patterns) {
    if (patterns == null) {
      throw new NullPointerException("Null patterns");
    }
    this.patterns = patterns;
  }

  @Override
  public List<ListPattern> patterns() {
    return patterns;
  }

  @Override
  public String toString() {
    return "SearchPatternsResponse{"
        + "patterns=" + patterns
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SearchPatternsResponse) {
      SearchPatternsResponse that = (SearchPatternsResponse) o;
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
