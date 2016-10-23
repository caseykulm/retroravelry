
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
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_FullPattern extends FullPattern {

  private final int comments_count;
  private final ListCraft craft;
  private final String currency;
  private final String currency_symbol;
  private final float difficulty_average;
  private final int difficulty_count;
  private final boolean downloadable;
  private final int favorites_count;
  private final boolean free;
  private final String gauge;
  private final String gauge_description;
  private final String gauge_divisor;
  private final String gauge_pattern;
  private final int id;
  private final String name;
  private final String notes_html;
  private final String notes;
  private final FullPack packs;
  private final List<PatternAttribute> pattern_attributes;
  private final PatternAuthor pattern_author;
  private final List<PatternCategory> pattern_categories;
  private final String pattern_needle_sizes;
  private final boolean pdf_in_library;
  private final String pdf_url;
  private final String permalink;
  private final Map<String, String> personal_attributes;
  private final List<Photo> photos;
  private final String price;
  private final List<ListPrinting> printings;
  private final int product_id;
  private final int projects_count;
  private final Date published;
  private final int queued_projects_count;
  private final float rating_average;
  private final int rating_count;
  private final boolean ravelry_download;
  private final String row_gauge;
  private final String sizes_available;
  private final String url;
  private final List<Integer> volumes_in_library;
  private final int yardage;
  private final String yardage_description;
  private final int yardage_max;
  private final ListYarnWeight yarn_weight;
  private final String yarn_weight_description;

  AutoValue_FullPattern(
      int comments_count,
      ListCraft craft,
      String currency,
      String currency_symbol,
      float difficulty_average,
      int difficulty_count,
      boolean downloadable,
      int favorites_count,
      boolean free,
      String gauge,
      String gauge_description,
      String gauge_divisor,
      String gauge_pattern,
      int id,
      String name,
      String notes_html,
      String notes,
      FullPack packs,
      List<PatternAttribute> pattern_attributes,
      PatternAuthor pattern_author,
      List<PatternCategory> pattern_categories,
      String pattern_needle_sizes,
      boolean pdf_in_library,
      String pdf_url,
      String permalink,
      Map<String, String> personal_attributes,
      List<Photo> photos,
      String price,
      List<ListPrinting> printings,
      int product_id,
      int projects_count,
      Date published,
      int queued_projects_count,
      float rating_average,
      int rating_count,
      boolean ravelry_download,
      String row_gauge,
      String sizes_available,
      String url,
      List<Integer> volumes_in_library,
      int yardage,
      String yardage_description,
      int yardage_max,
      ListYarnWeight yarn_weight,
      String yarn_weight_description) {
    this.comments_count = comments_count;
    if (craft == null) {
      throw new NullPointerException("Null craft");
    }
    this.craft = craft;
    if (currency == null) {
      throw new NullPointerException("Null currency");
    }
    this.currency = currency;
    if (currency_symbol == null) {
      throw new NullPointerException("Null currency_symbol");
    }
    this.currency_symbol = currency_symbol;
    this.difficulty_average = difficulty_average;
    this.difficulty_count = difficulty_count;
    this.downloadable = downloadable;
    this.favorites_count = favorites_count;
    this.free = free;
    if (gauge == null) {
      throw new NullPointerException("Null gauge");
    }
    this.gauge = gauge;
    if (gauge_description == null) {
      throw new NullPointerException("Null gauge_description");
    }
    this.gauge_description = gauge_description;
    if (gauge_divisor == null) {
      throw new NullPointerException("Null gauge_divisor");
    }
    this.gauge_divisor = gauge_divisor;
    if (gauge_pattern == null) {
      throw new NullPointerException("Null gauge_pattern");
    }
    this.gauge_pattern = gauge_pattern;
    this.id = id;
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
    if (notes_html == null) {
      throw new NullPointerException("Null notes_html");
    }
    this.notes_html = notes_html;
    if (notes == null) {
      throw new NullPointerException("Null notes");
    }
    this.notes = notes;
    if (packs == null) {
      throw new NullPointerException("Null packs");
    }
    this.packs = packs;
    if (pattern_attributes == null) {
      throw new NullPointerException("Null pattern_attributes");
    }
    this.pattern_attributes = pattern_attributes;
    if (pattern_author == null) {
      throw new NullPointerException("Null pattern_author");
    }
    this.pattern_author = pattern_author;
    if (pattern_categories == null) {
      throw new NullPointerException("Null pattern_categories");
    }
    this.pattern_categories = pattern_categories;
    if (pattern_needle_sizes == null) {
      throw new NullPointerException("Null pattern_needle_sizes");
    }
    this.pattern_needle_sizes = pattern_needle_sizes;
    this.pdf_in_library = pdf_in_library;
    if (pdf_url == null) {
      throw new NullPointerException("Null pdf_url");
    }
    this.pdf_url = pdf_url;
    if (permalink == null) {
      throw new NullPointerException("Null permalink");
    }
    this.permalink = permalink;
    if (personal_attributes == null) {
      throw new NullPointerException("Null personal_attributes");
    }
    this.personal_attributes = personal_attributes;
    if (photos == null) {
      throw new NullPointerException("Null photos");
    }
    this.photos = photos;
    if (price == null) {
      throw new NullPointerException("Null price");
    }
    this.price = price;
    if (printings == null) {
      throw new NullPointerException("Null printings");
    }
    this.printings = printings;
    this.product_id = product_id;
    this.projects_count = projects_count;
    if (published == null) {
      throw new NullPointerException("Null published");
    }
    this.published = published;
    this.queued_projects_count = queued_projects_count;
    this.rating_average = rating_average;
    this.rating_count = rating_count;
    this.ravelry_download = ravelry_download;
    if (row_gauge == null) {
      throw new NullPointerException("Null row_gauge");
    }
    this.row_gauge = row_gauge;
    if (sizes_available == null) {
      throw new NullPointerException("Null sizes_available");
    }
    this.sizes_available = sizes_available;
    if (url == null) {
      throw new NullPointerException("Null url");
    }
    this.url = url;
    if (volumes_in_library == null) {
      throw new NullPointerException("Null volumes_in_library");
    }
    this.volumes_in_library = volumes_in_library;
    this.yardage = yardage;
    if (yardage_description == null) {
      throw new NullPointerException("Null yardage_description");
    }
    this.yardage_description = yardage_description;
    this.yardage_max = yardage_max;
    if (yarn_weight == null) {
      throw new NullPointerException("Null yarn_weight");
    }
    this.yarn_weight = yarn_weight;
    if (yarn_weight_description == null) {
      throw new NullPointerException("Null yarn_weight_description");
    }
    this.yarn_weight_description = yarn_weight_description;
  }

  @Override
  public int comments_count() {
    return comments_count;
  }

  @Override
  public ListCraft craft() {
    return craft;
  }

  @Override
  public String currency() {
    return currency;
  }

  @Override
  public String currency_symbol() {
    return currency_symbol;
  }

  @Override
  public float difficulty_average() {
    return difficulty_average;
  }

  @Override
  public int difficulty_count() {
    return difficulty_count;
  }

  @Override
  public boolean downloadable() {
    return downloadable;
  }

  @Override
  public int favorites_count() {
    return favorites_count;
  }

  @Override
  public boolean free() {
    return free;
  }

  @Override
  public String gauge() {
    return gauge;
  }

  @Override
  public String gauge_description() {
    return gauge_description;
  }

  @Override
  public String gauge_divisor() {
    return gauge_divisor;
  }

  @Override
  public String gauge_pattern() {
    return gauge_pattern;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String notes_html() {
    return notes_html;
  }

  @Override
  public String notes() {
    return notes;
  }

  @Override
  public FullPack packs() {
    return packs;
  }

  @Override
  public List<PatternAttribute> pattern_attributes() {
    return pattern_attributes;
  }

  @Override
  public PatternAuthor pattern_author() {
    return pattern_author;
  }

  @Override
  public List<PatternCategory> pattern_categories() {
    return pattern_categories;
  }

  @Override
  public String pattern_needle_sizes() {
    return pattern_needle_sizes;
  }

  @Override
  public boolean pdf_in_library() {
    return pdf_in_library;
  }

  @Override
  public String pdf_url() {
    return pdf_url;
  }

  @Override
  public String permalink() {
    return permalink;
  }

  @Override
  public Map<String, String> personal_attributes() {
    return personal_attributes;
  }

  @Override
  public List<Photo> photos() {
    return photos;
  }

  @Override
  public String price() {
    return price;
  }

  @Override
  public List<ListPrinting> printings() {
    return printings;
  }

  @Override
  public int product_id() {
    return product_id;
  }

  @Override
  public int projects_count() {
    return projects_count;
  }

  @Override
  public Date published() {
    return published;
  }

  @Override
  public int queued_projects_count() {
    return queued_projects_count;
  }

  @Override
  public float rating_average() {
    return rating_average;
  }

  @Override
  public int rating_count() {
    return rating_count;
  }

  @Override
  public boolean ravelry_download() {
    return ravelry_download;
  }

  @Override
  public String row_gauge() {
    return row_gauge;
  }

  @Override
  public String sizes_available() {
    return sizes_available;
  }

  @Override
  public String url() {
    return url;
  }

  @Override
  public List<Integer> volumes_in_library() {
    return volumes_in_library;
  }

  @Override
  public int yardage() {
    return yardage;
  }

  @Override
  public String yardage_description() {
    return yardage_description;
  }

  @Override
  public int yardage_max() {
    return yardage_max;
  }

  @Override
  public ListYarnWeight yarn_weight() {
    return yarn_weight;
  }

  @Override
  public String yarn_weight_description() {
    return yarn_weight_description;
  }

  @Override
  public String toString() {
    return "FullPattern{"
        + "comments_count=" + comments_count + ", "
        + "craft=" + craft + ", "
        + "currency=" + currency + ", "
        + "currency_symbol=" + currency_symbol + ", "
        + "difficulty_average=" + difficulty_average + ", "
        + "difficulty_count=" + difficulty_count + ", "
        + "downloadable=" + downloadable + ", "
        + "favorites_count=" + favorites_count + ", "
        + "free=" + free + ", "
        + "gauge=" + gauge + ", "
        + "gauge_description=" + gauge_description + ", "
        + "gauge_divisor=" + gauge_divisor + ", "
        + "gauge_pattern=" + gauge_pattern + ", "
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "notes_html=" + notes_html + ", "
        + "notes=" + notes + ", "
        + "packs=" + packs + ", "
        + "pattern_attributes=" + pattern_attributes + ", "
        + "pattern_author=" + pattern_author + ", "
        + "pattern_categories=" + pattern_categories + ", "
        + "pattern_needle_sizes=" + pattern_needle_sizes + ", "
        + "pdf_in_library=" + pdf_in_library + ", "
        + "pdf_url=" + pdf_url + ", "
        + "permalink=" + permalink + ", "
        + "personal_attributes=" + personal_attributes + ", "
        + "photos=" + photos + ", "
        + "price=" + price + ", "
        + "printings=" + printings + ", "
        + "product_id=" + product_id + ", "
        + "projects_count=" + projects_count + ", "
        + "published=" + published + ", "
        + "queued_projects_count=" + queued_projects_count + ", "
        + "rating_average=" + rating_average + ", "
        + "rating_count=" + rating_count + ", "
        + "ravelry_download=" + ravelry_download + ", "
        + "row_gauge=" + row_gauge + ", "
        + "sizes_available=" + sizes_available + ", "
        + "url=" + url + ", "
        + "volumes_in_library=" + volumes_in_library + ", "
        + "yardage=" + yardage + ", "
        + "yardage_description=" + yardage_description + ", "
        + "yardage_max=" + yardage_max + ", "
        + "yarn_weight=" + yarn_weight + ", "
        + "yarn_weight_description=" + yarn_weight_description
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof FullPattern) {
      FullPattern that = (FullPattern) o;
      return (this.comments_count == that.comments_count())
           && (this.craft.equals(that.craft()))
           && (this.currency.equals(that.currency()))
           && (this.currency_symbol.equals(that.currency_symbol()))
           && (Float.floatToIntBits(this.difficulty_average) == Float.floatToIntBits(that.difficulty_average()))
           && (this.difficulty_count == that.difficulty_count())
           && (this.downloadable == that.downloadable())
           && (this.favorites_count == that.favorites_count())
           && (this.free == that.free())
           && (this.gauge.equals(that.gauge()))
           && (this.gauge_description.equals(that.gauge_description()))
           && (this.gauge_divisor.equals(that.gauge_divisor()))
           && (this.gauge_pattern.equals(that.gauge_pattern()))
           && (this.id == that.id())
           && (this.name.equals(that.name()))
           && (this.notes_html.equals(that.notes_html()))
           && (this.notes.equals(that.notes()))
           && (this.packs.equals(that.packs()))
           && (this.pattern_attributes.equals(that.pattern_attributes()))
           && (this.pattern_author.equals(that.pattern_author()))
           && (this.pattern_categories.equals(that.pattern_categories()))
           && (this.pattern_needle_sizes.equals(that.pattern_needle_sizes()))
           && (this.pdf_in_library == that.pdf_in_library())
           && (this.pdf_url.equals(that.pdf_url()))
           && (this.permalink.equals(that.permalink()))
           && (this.personal_attributes.equals(that.personal_attributes()))
           && (this.photos.equals(that.photos()))
           && (this.price.equals(that.price()))
           && (this.printings.equals(that.printings()))
           && (this.product_id == that.product_id())
           && (this.projects_count == that.projects_count())
           && (this.published.equals(that.published()))
           && (this.queued_projects_count == that.queued_projects_count())
           && (Float.floatToIntBits(this.rating_average) == Float.floatToIntBits(that.rating_average()))
           && (this.rating_count == that.rating_count())
           && (this.ravelry_download == that.ravelry_download())
           && (this.row_gauge.equals(that.row_gauge()))
           && (this.sizes_available.equals(that.sizes_available()))
           && (this.url.equals(that.url()))
           && (this.volumes_in_library.equals(that.volumes_in_library()))
           && (this.yardage == that.yardage())
           && (this.yardage_description.equals(that.yardage_description()))
           && (this.yardage_max == that.yardage_max())
           && (this.yarn_weight.equals(that.yarn_weight()))
           && (this.yarn_weight_description.equals(that.yarn_weight_description()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.comments_count;
    h *= 1000003;
    h ^= this.craft.hashCode();
    h *= 1000003;
    h ^= this.currency.hashCode();
    h *= 1000003;
    h ^= this.currency_symbol.hashCode();
    h *= 1000003;
    h ^= Float.floatToIntBits(this.difficulty_average);
    h *= 1000003;
    h ^= this.difficulty_count;
    h *= 1000003;
    h ^= this.downloadable ? 1231 : 1237;
    h *= 1000003;
    h ^= this.favorites_count;
    h *= 1000003;
    h ^= this.free ? 1231 : 1237;
    h *= 1000003;
    h ^= this.gauge.hashCode();
    h *= 1000003;
    h ^= this.gauge_description.hashCode();
    h *= 1000003;
    h ^= this.gauge_divisor.hashCode();
    h *= 1000003;
    h ^= this.gauge_pattern.hashCode();
    h *= 1000003;
    h ^= this.id;
    h *= 1000003;
    h ^= this.name.hashCode();
    h *= 1000003;
    h ^= this.notes_html.hashCode();
    h *= 1000003;
    h ^= this.notes.hashCode();
    h *= 1000003;
    h ^= this.packs.hashCode();
    h *= 1000003;
    h ^= this.pattern_attributes.hashCode();
    h *= 1000003;
    h ^= this.pattern_author.hashCode();
    h *= 1000003;
    h ^= this.pattern_categories.hashCode();
    h *= 1000003;
    h ^= this.pattern_needle_sizes.hashCode();
    h *= 1000003;
    h ^= this.pdf_in_library ? 1231 : 1237;
    h *= 1000003;
    h ^= this.pdf_url.hashCode();
    h *= 1000003;
    h ^= this.permalink.hashCode();
    h *= 1000003;
    h ^= this.personal_attributes.hashCode();
    h *= 1000003;
    h ^= this.photos.hashCode();
    h *= 1000003;
    h ^= this.price.hashCode();
    h *= 1000003;
    h ^= this.printings.hashCode();
    h *= 1000003;
    h ^= this.product_id;
    h *= 1000003;
    h ^= this.projects_count;
    h *= 1000003;
    h ^= this.published.hashCode();
    h *= 1000003;
    h ^= this.queued_projects_count;
    h *= 1000003;
    h ^= Float.floatToIntBits(this.rating_average);
    h *= 1000003;
    h ^= this.rating_count;
    h *= 1000003;
    h ^= this.ravelry_download ? 1231 : 1237;
    h *= 1000003;
    h ^= this.row_gauge.hashCode();
    h *= 1000003;
    h ^= this.sizes_available.hashCode();
    h *= 1000003;
    h ^= this.url.hashCode();
    h *= 1000003;
    h ^= this.volumes_in_library.hashCode();
    h *= 1000003;
    h ^= this.yardage;
    h *= 1000003;
    h ^= this.yardage_description.hashCode();
    h *= 1000003;
    h ^= this.yardage_max;
    h *= 1000003;
    h ^= this.yarn_weight.hashCode();
    h *= 1000003;
    h ^= this.yarn_weight_description.hashCode();
    return h;
  }

}
