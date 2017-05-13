package com.caseykulm.retroravelry;

import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
