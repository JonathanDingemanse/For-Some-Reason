package saving;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.*;


public class GameSaveFinder implements FileVisitor<Path> {
    PathMatcher matcher;

    public GameSaveFinder(){

    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            System.out.format("Regular file: %s ", file);
            System.out.println("(" + attr.size() + "bytes)");

            if(file.getFileName().toString().endsWith(".fsrsg") && attr.size() > 200){
                if(!(file.getFileName().toString().endsWith("currentGame.fsrsg"))){
                    GameSaveLoader.load(file);
                }
            }
        }

        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir,
                                              IOException exc) {
        System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
                                           IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }
}
