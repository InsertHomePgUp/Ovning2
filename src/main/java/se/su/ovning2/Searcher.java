package se.su.ovning2;

import java.util.*;

public class Searcher implements SearchOperations{

  Set<String> uniqueArtists = new HashSet<>();
  Set<String> uniqueGenres = new HashSet<>();
  Set<String> uniqueTitles = new HashSet<>();
  Map<String, Recording> titleToRecordingMap = new HashMap<>();
  SortedMap<Integer, Set<Recording>> yearToRecordingMap = new TreeMap<>();
  Map<String, Set<Recording>> genreToRecordingMap = new HashMap<>();
  private final Collection<Recording> recordings;


  public Searcher(Collection<Recording> data) {

    this.recordings = data;

    for (Recording recording : recordings) {
      uniqueArtists.add(recording.getArtist());
    }
    for (Recording recording : recordings) {
      uniqueGenres.addAll(recording.getGenre());
    }
    for (Recording recording : recordings) {
      uniqueTitles.add(recording.getTitle());
    }
    for (Recording recording : recordings) {
      titleToRecordingMap.put(recording.getTitle(), recording);
    }
    for (Recording recording : recordings) {
      // Kontrollera om året redan finns i mappen
      if (!yearToRecordingMap.containsKey(recording.getYear())) {
        // Om året inte finns, skapa en ny Set och lägg till den i mappen
        yearToRecordingMap.put(recording.getYear(), new HashSet<>());
      }
      // Lägg till Recording i den Set som hör till det aktuella året
      yearToRecordingMap.get(recording.getYear()).add(recording);
    }

    for (Recording recording : recordings) {
      for (String genre : recording.getGenre()) {
        if (!genreToRecordingMap.containsKey(genre)) {
          genreToRecordingMap.put(genre, new HashSet<>());
        }
        genreToRecordingMap.get(genre).add(recording);
      }
    }

  }

  @Override
  public long numberOfArtists() {
    return uniqueArtists.size();
  }

  @Override
  public long numberOfGenres() {
    return uniqueGenres.size();
  }

  @Override
  public long numberOfTitles() {
    return uniqueTitles.size();
  }

  @Override
  public boolean doesArtistExist(String name) {
    return uniqueArtists.contains(name);
  }

  @Override
  public Collection<String> getGenres() {
    Set<String> unmodableGenres = new TreeSet<>();
    unmodableGenres.addAll(uniqueGenres);
    Collections.unmodifiableSet(unmodableGenres);
    return unmodableGenres;
  }

  @Override
  public Recording getRecordingByName(String title) {
    return titleToRecordingMap.get(title);
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) {
    TreeSet<Recording> foundRecordings = new TreeSet<>();
    yearToRecordingMap.forEach((recordingYear, recordings) -> {
      if (recordingYear >= year) {
        foundRecordings.addAll(recordings);
      }
  });
    return Collections.unmodifiableSortedSet(foundRecordings);
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    SortedSet<Recording> artistRecordings = new TreeSet<>((r1, r2) -> Integer.compare(r1.getYear(), r2.getYear()));

    yearToRecordingMap.forEach((year, recordings) -> {
      for (Recording recording : recordings) {
        if (recording.getArtist().equals(artist)) {
          artistRecordings.add(recording);
        }
      }
    });
    return Collections.unmodifiableSortedSet(artistRecordings);
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {
    Set<Recording> recordings = genreToRecordingMap.get(genre);
    if (recordings == null) {
      return Collections.emptySet();
    }
    TreeSet<Recording> genreRecordings = new TreeSet<>(recordings);
    return Collections.unmodifiableSortedSet(genreRecordings);
  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
    TreeSet<Recording> foundRecordings = new TreeSet<>();

    SortedMap<Integer, Set<Recording>> headMap = yearToRecordingMap.subMap(yearFrom, yearTo + 1);

    headMap.forEach((year, recordings) -> {

      recordings.forEach(recording -> {
        if (recording.getGenre().contains(genre)) {
          foundRecordings.add(recording);
        }
      });
    });
    return Collections.unmodifiableSortedSet(foundRecordings);
  }

  @Override
  public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
    HashSet<Recording> newRecordings = new HashSet<>();

    newRecordings.addAll(offered);
    newRecordings.addAll(recordings);

    newRecordings.removeAll(recordings);

    return Collections.unmodifiableSet(newRecordings);
  }
}
