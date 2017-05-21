package com.example.retroravelry.library;

import com.caseykulm.retroravelry.models.volume.ListVolume;
import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import java.util.List;

public abstract class BaseSearchLibrary implements Runnable {

  protected static final String USERNAME = "";
  protected static final String QUERY = "";
  protected static final String QUERY_TYPE = "patterns";
  protected static final String TYPE = "pattern";
  protected static final String SORT = "title";
  protected static final int PAGE = 1;
  protected static final int PAGE_SIZE = 10;

  public void run() {
    searchLibrary();
  }

  abstract void searchLibrary();

  protected void printLibrary(LibraryResponse libraryResponse) {
    System.out.println("Found volumes:");
    List<ListVolume> volumes = libraryResponse.getVolumes();
    for (ListVolume listVolume : volumes) {
      System.out.println(listVolume.getTitle());
    }
  }

  protected void printErrors(Throwable throwable) {
    System.out.println(throwable.getMessage());
  }

}
