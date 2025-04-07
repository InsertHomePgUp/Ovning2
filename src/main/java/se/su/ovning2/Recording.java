package se.su.ovning2;

import java.util.Collection;
import java.util.Set;

//Klassen Recording representerar en inspelning (antingen CD eller LP). En skillnad mot motsvarande klass i Ovningsuppgift 1 ¨ ¨ar att Recording h¨ar ¨aven inneh˚aller en Set<String> genre, som inneh˚aller en m¨angd namn p˚a genrer (kan
//vara flera), som denna inspelning representerar. Dessutom representeras LP respektive CD endast genom ett String-attribut, inte som olika subklasser.
//Din uppgift ¨ar att modifiera klassen s˚a att dess objekt kan fungera v¨al som
//nycklar i en avbildingstabell eller m¨angd (b˚ade hashade och tr¨adbaserade).
//I samband med det ska likhet (equality) vara definierad s˚a att tv˚a olika objekt
//av klassen ska betraktas som lika om de har samma titel, artist och ˚ar. Typ och
//genre skall inte spela n˚agon roll. Egentligen har objekt av typen Recording ingen
//naturlig sorteringsordning, men det ¨ar ok att definiera en sorteringsordning om
//det kr¨avs f¨or att l¨osa n˚agon del av uppgiften. Det ¨ar ocks˚a ok att ist¨allet definiera
//ordningen i varje ordnad datastruktur man vill anv¨anda, eller med en blandning
//av de tv˚a s¨atten.

public class Recording {
  private final int year;
  private final String artist;
  private final String title;
  private final String type;
  private final Set<String> genre;

  public Recording(String title, String artist, int year, String type, Set<String> genre) {
    this.title = title;
    this.year = year;
    this.artist = artist;
    this.type = type;
    this.genre = genre;
  }

  public String getArtist() {
    return artist;
  }

  public Collection<String> getGenre() {
    return genre;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public int getYear() {
    return year;
  }

  @Override
  public String toString() {
    return String.format("{ %s | %s | %s | %d | %s }", artist, title, genre, year, type);
  }
}
