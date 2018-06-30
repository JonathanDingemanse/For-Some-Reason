package saving;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

import static java.nio.file.FileVisitOption.FOLLOW_LINKS;

public class GameSaveLoader {

    public GameSaveLoader(){

    }

    public static void load(Path path){
        System.out.println("A game has been loaded.");
        GameSave save = new GameSave(path);
        Saver.gameSaves.add(save);
    }

    public static void searchAndLoadGameSaves(){
        Path dir = Paths.get("./Saves");
        EnumSet<FileVisitOption> opts = EnumSet.of(FOLLOW_LINKS);
        GameSaveFinder finder = new GameSaveFinder();

        try {
            Files.walkFileTree(dir, opts, 1, finder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
