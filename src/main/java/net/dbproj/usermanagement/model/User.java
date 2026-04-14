package net.dbproj.usermanagement.model;

public class User {
    protected int id;
    protected String name;
    protected String artist;
    protected String genre;
    protected String album; // New field for album
    protected Boolean liked; // New field for liked

    // Default constructor
    public User() {}

    // Constructor without ID
    public User(String name, String artist, String genre, String album, Boolean liked) {
        super();
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.liked = liked;
    }

    // Constructor with ID
    public User(int id, String name, String artist, String genre, String album, Boolean liked) {
        super();
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.liked = liked;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
