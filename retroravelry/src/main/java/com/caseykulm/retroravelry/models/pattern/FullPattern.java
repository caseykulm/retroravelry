package com.caseykulm.retroravelry.models.pattern;

import com.caseykulm.retroravelry.models.craft.ListCraft;
import com.caseykulm.retroravelry.models.pack.FullPack;
import com.caseykulm.retroravelry.models.patternattribute.PatternAttribute;
import com.caseykulm.retroravelry.models.patternauthor.PatternAuthor;
import com.caseykulm.retroravelry.models.patterncategory.PatternCategory;
import com.caseykulm.retroravelry.models.photo.Photo;
import com.caseykulm.retroravelry.models.printing.ListPrinting;
import com.caseykulm.retroravelry.models.yarnweight.ListYarnWeight;
import com.google.auto.value.AutoValue;
import java.util.Date;
import java.util.List;
import java.util.Map;

@AutoValue public abstract class FullPattern {

  public abstract int comments_count();
  public abstract ListCraft craft();
  public abstract String currency();
  public abstract String currency_symbol();
  public abstract float difficulty_average();
  public abstract int difficulty_count();
  public abstract boolean downloadable();
  public abstract int favorites_count();
  public abstract boolean free();
  public abstract String gauge();
  public abstract String gauge_description();
  public abstract String gauge_divisor();
  public abstract String gauge_pattern();
  public abstract int id();
  public abstract String name();
  public abstract String notes_html();
  public abstract String notes();
  public abstract FullPack packs();
  public abstract List<PatternAttribute> pattern_attributes();
  public abstract PatternAuthor pattern_author();
  public abstract List<PatternCategory> pattern_categories();
  public abstract String pattern_needle_sizes();
  public abstract boolean pdf_in_library();
  public abstract String pdf_url();
  public abstract String permalink();
  public abstract Map<String, String> personal_attributes();
  public abstract List<Photo> photos();
  public abstract String price();
  public abstract List<ListPrinting> printings();
  public abstract int product_id();
  public abstract int projects_count();
  public abstract Date published();
  public abstract int queued_projects_count();
  public abstract float rating_average();
  public abstract int rating_count();
  public abstract boolean ravelry_download();
  public abstract String row_gauge();
  public abstract String sizes_available();
  public abstract String url();
  public abstract List<Integer> volumes_in_library();
  public abstract int yardage();
  public abstract String yardage_description();
  public abstract int yardage_max();
  public abstract ListYarnWeight yarn_weight();
  public abstract String yarn_weight_description();

}
