package com.caseykulm.retroravelry;

import com.caseykulm.retroravelry.models.user.SmallUser;
import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import io.reactivex.Single;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RxRetroRavelryAuthService {

  // region Miscellaneous

  @GET("current_user.json")
  Single<SmallUser> getCurrentUser(
      @HeaderMap Map<String, String> authHeaders,
      @QueryMap Map<String, String> authQueryMap
  );

  // endregion

  // region Library

  @GET("people/{username}/library/search.json")
  Single<LibraryResponse> searchLibrary(
      @HeaderMap Map<String, String> authHeaders,
      @Path("username") String username,
      @Query("query") String query,
      @Query("query_type") String queryType,
      @Query("type") String type,
      @Query("sort") String sort,
      @Query("page") int page,
      @Query("page_size") int pageSize);

  // endregion

}
