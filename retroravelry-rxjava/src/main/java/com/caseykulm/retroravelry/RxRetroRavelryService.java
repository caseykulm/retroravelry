package com.caseykulm.retroravelry;

import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import com.caseykulm.retroravelry.responses.patterns.ShowPatternResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RxRetroRavelryService {

  // region Patterns

  @GET("patterns/search.json")
  Single<SearchPatternsResponse> searchPatterns(
      @Query("query") String query,
      @Query("page") int page,
      @Query("page_size") int pageSize,
      @Query("personal_attributes") boolean personal_attributes);

  @GET("patterns/{id}.json")
  Single<ShowPatternResponse> showPattern(@Path("id") int id);

  // endregion

}
