package fr.treemanager.utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class FileWatcherService {
    private Thread watcherThread;

    private final HashMap<String, List<FileChangeListener>> listeners = new HashMap<String, List<FileChangeListener>>();
    private final Path directoryPath;

    public FileWatcherService(String directoryPath) {
        this.directoryPath = Paths.get(directoryPath);
    }

    public void addListener(String fileName, FileChangeListener listener) {
        if (!listeners.containsKey(fileName)) {
            listeners.put(fileName, new ArrayList<FileChangeListener>());
        }
        listeners.get(fileName).add(listener);
    }

    public void start() {
         watcherThread = new Thread(() -> {
            try {
                watchDirectory(directoryPath, this.listeners);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        watcherThread.start();
    }

    public void stop() {
        if (watcherThread != null) {
            watcherThread.interrupt();
        }
    }

    private static void watchDirectory(Path dir, HashMap<String, List<FileChangeListener>> listeners ) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

        System.out.println("Surveillance de : " + dir);
        try {
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    Path fileName = (Path) event.context();

                    List<FileChangeListener> tempListeners = listeners.get(fileName.toString());

                    if(tempListeners != null) {
                        for(FileChangeListener listener : tempListeners) {
                            listener.onFileChange();
                        }

                    }

                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Fermeture du service de surveillance");
        }
    }
}
