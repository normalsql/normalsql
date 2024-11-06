package normalsql;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class DirectoryLister {
    public static void main(String[] args) {
//        Path startPath = Paths.get("");
        Path startPath = Paths.get("doc/example");
//        Path startPath = Paths.get("/Users/jasonosgood/Projects/normalsql/doc/example");

//        FileTreeWalker fileTreeWalker;

        try (Stream<Path> stream = Files.walk(startPath)) {
            stream.filter(Files::isRegularFile)
                  .filter(path -> {
                      try {
                          return !Files.isHidden(path);
                      } catch (IOException e) {
                          return false; // If hidden status can't be determined, skip it
                      }
                  })
                  .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
