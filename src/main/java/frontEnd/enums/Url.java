package frontEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Url {
  WEBTABLE_HOMEPAGE_URL("https://www.way2automation.com/angularjs-protractor/webtables/"),
  BANKING_HOMEPAGE_URL("https://www.way2automation.com/angularjs-protractor/banking/#/login"),
  JSON_API_URL("https://jsonplaceholder.typicode.com/"),
  ALBUM_API_URL("https://albums-collection-service.herokuapp.com/api-docs");

  private final String url;
}
