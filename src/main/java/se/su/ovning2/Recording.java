package se.su.ovning2;

import java.util.*;

public class Recording implements Comparable<Recording> {
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


  //Överskuggar equals metoden, inte endast == som skulle jämföra pekarna, detta för att kunna jämföra innehåll av en recording mot en annan
  @Override
  public boolean equals (Object other) {
    if (other == null || this.getClass() != other.getClass())
      return false;

    Recording otherRecording = (Recording) other;
    return this.title.equals(otherRecording.getTitle()) && this.year == otherRecording.getYear() && this.artist.equals(otherRecording.getArtist());
  }
  //Överskuggar hashCode(). eftersom man enl föreläsning måste göra det om man överskuggar equals metoden
  @Override
  public int hashCode() {
    return Objects.hash(title, year, artist); //Använder samtliga värden i equals enl instruktion
  }

  /**@Override
  public int compare(Recording a, Recording b) {
    return Integer.compare(a.getYear(), b.getYear());
  }**/

  @Override
  public int compareTo(Recording other) {
    // Jämför först årtalen
    int yearComparison = Integer.compare(this.year, other.year);
    if (yearComparison != 0) {
      return yearComparison; // Om årtalen inte är lika, returnera jämförelsen
    }

    // Om årtalen är lika, jämför på artist
    int artistComparison = this.artist.compareTo(other.artist);
    if (artistComparison != 0) {
      return artistComparison; // Om artisterna inte är lika, returnera jämförelsen
    }

    // Om både år och artist är lika, jämför på titel
    return this.title.compareTo(other.title); // Lägg till jämförelse på titel
  }

}
