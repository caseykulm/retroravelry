package com.caseykulm.retroravelry;

import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface RxRetroRavelryAuthService {

  // region Library

  @GET("people/{username}/library/search.json")
  Single<LibraryResponse> searchLibrary(
      @Path("username") String username,
      @Query("query") String query,
      @Query("query_type") String queryType,
      @Query("type") String type,
      @Query("sort") String sort,
      @Query("page") int page,
      @Query("page_size") int pageSize);

  // endregion

}
