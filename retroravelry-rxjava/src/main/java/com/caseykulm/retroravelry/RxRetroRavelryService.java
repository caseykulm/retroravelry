package com.caseykulm.retroravelry;

import com.caseykulm.retroravelry.models.user.SmallUser;
import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import com.caseykulm.retroravelry.responses.patterns.ShowPatternResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RxRetroRavelryService {

  // region Patterns

  @GET("patterns/search.json")
  Single<SearchPatternsResponse> searchPatterns(
      @Header("Authorization") String requestAuth,
      @Query("query") String query,
      @Query("page") int page,
      @Query("page_size") int pageSize,
      @Query("personal_attributes") boolean personal_attributes);

  @GET("patterns/{id}.json")
  Single<ShowPatternResponse> showPattern(
      @Header("Authorization") String requestAuth,
      @Path("id") int id);

  // endregion

  // region Miscellaneous

  @GET("current_user.json")
  Single<SmallUser> getCurrentUser(
      @Header("Authorization") String requestAuth);

  // endregion

  // region Library

  @GET("people/{username}/library/search.json")
  Single<LibraryResponse> searchLibrary(
      @Header("Authorization") String requestAuth,
      @Path("username") String username,
      @Query("query") String query,
      @Query("query_type") String queryType,
      @Query("type") String type,
      @Query("sort") String sort,
      @Query("page") int page,
      @Query("page_size") int pageSize);

  // endregion
}
