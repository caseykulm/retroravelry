package com.caseykulm.retroravelry.responses.library;

import com.caseykulm.retroravelry.models.Paginator;
import com.caseykulm.retroravelry.models.volume.ListVolume;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue public abstract class LibraryResponse {

  public abstract Paginator paginator();
  public abstract List<ListVolume> volumes();

}
