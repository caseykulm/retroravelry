package com.caseykulm.retroravelry;

import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import com.caseykulm.retroravelry.responses.patterns.ShowPatternResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroRavelryService {

  // region Patterns

  @GET("patterns/search.json")
  Call<SearchPatternsResponse> searchPatterns(
      @Query("query") String query,
      @Query("page") int page,
      @Query("page_size") int pageSize,
      @Query("personal_attributes") boolean personal_attributes);

  @GET("patterns/{id}.json")
  Call<ShowPatternResponse> showPattern(@Query("id") int id);

  // endregion

}
