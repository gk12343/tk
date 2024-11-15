package com.example.hotel_app;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.file.*;
import java.io.IOException;

@Service
public class FileChangeService {

    private final String pathToWatch = "/path/to/watch"; // Replace with your path

    @Scheduled(fixedRate = 1000)
    public void watchFileChanges() throws IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(pathToWatch);
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (InterruptedException ex) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("File modified: " + event.context());
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
