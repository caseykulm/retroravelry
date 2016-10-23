package com.caseykulm.retroravelry.responses.patterns;

import com.caseykulm.retroravelry.models.pattern.ListPattern;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue public abstract class SearchPatternsResponse {

  public abstract List<ListPattern> patterns();

}
