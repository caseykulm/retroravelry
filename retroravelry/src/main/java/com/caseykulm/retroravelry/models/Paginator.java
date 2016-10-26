package com.caseykulm.retroravelry.models;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class Paginator {

  public abstract int last_page();
  public abstract int page();
  public abstract int page_count();
  public abstract int page_size();
  public abstract int results();

}
