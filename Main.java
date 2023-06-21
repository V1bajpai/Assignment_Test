import java.util.*;

class RecentlyPlayedStore {
    private final int capacity;
    private Map<String, LinkedList<String>> store;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.store = new HashMap<>();
    }

    public void addSong(String user, String song) {
        LinkedList<String> playlist = store.getOrDefault(user, new LinkedList<>());

        // Check if the song is already in the playlist
        if (playlist.contains(song)) {
            // Move the song to the end (most recently played)
            playlist.remove(song);
        } else if (playlist.size() == capacity) {
            // Remove the least recently played song if the playlist is full
            playlist.removeFirst();
        }

        // Add the new song to the end (most recently played)
        playlist.addLast(song);

        // Update the playlist in the store
        store.put(user, playlist);
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        return store.getOrDefault(user, new LinkedList<>());
    }
}

public class Main {
    public static void main(String[] args) {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        store.addSong("User1", "S1");
        store.addSong("User1", "S2");
        store.addSong("User1", "S3");
        System.out.println(store.getRecentlyPlayedSongs("User1"));  // Output: [S1, S2, S3]

        store.addSong("User1", "S4");
        System.out.println(store.getRecentlyPlayedSongs("User1"));  // Output: [S2, S3, S4]

        store.addSong("User1", "S2");
        System.out.println(store.getRecentlyPlayedSongs("User1"));  // Output: [S3, S4, S2]

        store.addSong("User1", "S1");
        System.out.println(store.getRecentlyPlayedSongs("User1"));  // Output: [S4, S2, S1]
    }
}
