package se.su.ovning2;

import java.util.*;

public class Searcher implements SearchOperations {

  Set<String> uniqueArtists = new HashSet<>();
  Set<String> uniqueGenres = new HashSet<>();
  Set<String> uniqueTitles = new HashSet<>();

  public Searcher(Collection<Recording> data) {

    Collection<Recording> recordings = data;

    for (Recording recording : recordings) {
      uniqueArtists.add(recording.getArtist());
    }
    for (Recording recording : recordings) {
      uniqueGenres.addAll(recording.getGenre());
    }
    for (Recording recording : recordings) {
      uniqueTitles.add(recording.getTitle());
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingByName'");
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsAfter'");
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'getRecordingsByArtistOrderedByYearAsc'");
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenre'");
  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenreAndYear'");
  }

  @Override
  public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'offerHasNewRecordings'");
  }
}
