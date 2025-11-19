import com.anime.model.AnimeWrapped;
import com.anime.view.AnimeWrappedPanel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Anime Wrapped Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 1000);

            AnimeWrappedPanel wrappedPanel = new AnimeWrappedPanel();
            frame.add(wrappedPanel);

            // Load sample data
            wrappedPanel.loadWrappedData(createSampleData());

            frame.setLocationRelativeTo(null); // center window
            frame.setVisible(true);
        });
    }
    
    private static AnimeWrapped createSampleData() {
        AnimeWrapped wrapped = new AnimeWrapped();
        wrapped.setUsername("neo_ki");
        wrapped.setYear(2025);
        wrapped.setTotalEpisodesWatched(120);
        wrapped.setTopGenre("Action");

        // Top 5 series (seriesId, title, genre, seriesPhoto, episodesWatched)
        List<AnimeWrapped.SeriesStats> seriesList = new ArrayList<>();
        seriesList.add(new AnimeWrapped.SeriesStats(1, "Attack on Titan", "Action", "aot.jpg", 25));
        seriesList.add(new AnimeWrapped.SeriesStats(2, "Demon Slayer", "Fantasy", "ds.jpg", 24));
        seriesList.add(new AnimeWrapped.SeriesStats(3, "One Piece", "Adventure", "op.jpg", 50));
        seriesList.add(new AnimeWrapped.SeriesStats(4, "My Hero Academia", "Action", "mha.jpg", 30));
        seriesList.add(new AnimeWrapped.SeriesStats(5, "Jujutsu Kaisen", "Supernatural", "jk.jpg", 20));
        wrapped.setTop5Series(seriesList);

        // Top 3 actors (actorId, firstName, lastName, agency, episodesAppearedIn)
        List<AnimeWrapped.ActorStats> actors = new ArrayList<>();
        actors.add(new AnimeWrapped.ActorStats(1, "Mamoru", "Miyano", "Sigma Seven", 50));
        actors.add(new AnimeWrapped.ActorStats(2, "Kana", "Hanazawa", "Office Osawa", 40));
        actors.add(new AnimeWrapped.ActorStats(3, "Yuuki", "Kaji", "Haikyou", 35));
        wrapped.setTop3VoiceActors(actors);

        // Actor roles (actorId, seriesId, seriesTitle, characterName, episodesWatched)
        List<AnimeWrapped.ActorRole> roles = new ArrayList<>();
        roles.add(new AnimeWrapped.ActorRole(1, 1, "Death Note", "Light Yagami", 37));
        roles.add(new AnimeWrapped.ActorRole(1, 2, "Steins;Gate", "Rintarou Okabe", 24));
        roles.add(new AnimeWrapped.ActorRole(2, 3, "Angel Beats!", "Kanade Tachibana", 13));
        roles.add(new AnimeWrapped.ActorRole(3, 1, "Attack on Titan", "Eren Yeager", 25));
        roles.add(new AnimeWrapped.ActorRole(3, 4, "My Hero Academia", "Shoto Todoroki", 30));
        wrapped.setActorRoles(roles);

        return wrapped;
    }
}
