package com.caseykulm.retroravelry.models.pattern;

import com.caseykulm.retroravelry.models.patternauthor.PatternAuthor;
import com.caseykulm.retroravelry.models.photo.Photo;
import java.util.Map;

public class ListPattern {

  private PatternAuthor designer;
  private Photo first_photo;
  private boolean free;
  private int id;
  private String name;
  private PatternAuthor pattern_author;
  private String permalink;
  private Map<String, String> personal_attributes;

}
