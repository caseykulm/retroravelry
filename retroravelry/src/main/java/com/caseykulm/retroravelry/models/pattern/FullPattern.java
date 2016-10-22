package com.caseykulm.retroravelry.models.pattern;

import com.caseykulm.retroravelry.models.craft.ListCraft;
import com.caseykulm.retroravelry.models.pack.FullPack;
import com.caseykulm.retroravelry.models.patternattribute.PatternAttribute;
import com.caseykulm.retroravelry.models.patternauthor.PatternAuthor;
import com.caseykulm.retroravelry.models.patterncategory.PatternCategory;
import com.caseykulm.retroravelry.models.photo.Photo;
import com.caseykulm.retroravelry.models.printing.ListPrinting;
import com.caseykulm.retroravelry.models.yarnweight.ListYarnWeight;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FullPattern {

  private int comments_count;
  private ListCraft craft;
  private String currency;
  private String currency_symbol;
  private float difficulty_average;
  private int difficulty_count;
  private boolean downloadable;
  private int favorites_count;
  private boolean free;
  private String gauge;
  private String gauge_description;
  private String gauge_divisor;
  private String gauge_pattern;
  private int id;
  private String name;
  private String notes_html;
  private String notes;
  private FullPack packs;
  private List<PatternAttribute> pattern_attributes;
  private PatternAuthor pattern_author;
  private List<PatternCategory> pattern_categories;
  private String pattern_needle_sizes;
  private boolean pdf_in_library;
  private String pdf_url;
  private String permalink;
  private Map<String, String> personal_attributes;
  private List<Photo> photos;
  private String price;
  private List<ListPrinting> printings;
  private int product_id;
  private int projects_count;
  private Date published;
  private int queued_projects_count;
  private float rating_average;
  private int rating_count;
  private boolean ravelry_download;
  private String row_gauge;
  private String sizes_available;
  private String url;
  private List<Integer> volumes_in_library;
  private int yardage;
  private String yardage_description;
  private int yardage_max;
  private ListYarnWeight yarn_weight;
  private String yarn_weight_description;

}
