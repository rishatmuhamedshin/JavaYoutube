package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.kiulian.downloader.OnYoutubeDownloadListener;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SaveButton;

    @FXML
    private Button DownloadButton;

    @FXML
    private TextField TextURL;

    @FXML
    private ComboBox<String> Quality;

    List<AudioVideoFormat> videoWithAudioFormat;

    YoutubeVideo video;

    String videoId;
    String fileAbsPath;

    ObservableList<String> langs = FXCollections.observableArrayList("720hd", "medium", "minimal");



    @FXML
    void initialize() {
        assert SaveButton != null : "fx:id=\"SaveButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert DownloadButton != null : "fx:id=\"DownloadButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert TextURL != null : "fx:id=\"TextURL\" was not injected: check your FXML file 'sample.fxml'.";
        assert Quality != null : "fx:id=\"Quality\" was not injected: check your FXML file 'sample.fxml'.";

        
    }

    public void Download(String id) throws YoutubeException, IOException, InterruptedException {
            YoutubeDownloader youtubeDownloader = new YoutubeDownloader();
            video = youtubeDownloader.getVideo(videoId);

            videoWithAudioFormat = video.videoWithAudioFormats();

            File outputDirVideoWithAudio = new File(fileAbsPath);

            int index = 0;
            for (int i = 0; i < videoWithAudioFormat.size() ; i++) {
                for (AudioVideoFormat audioVideoFormat : videoWithAudioFormat) {
                    if (audioVideoFormat.videoQuality().toString() == Quality.getValue()) {
                        index = i;
                    }
                }
            }


            video.downloadAsync(videoWithAudioFormat.get(index), outputDirVideoWithAudio, new OnYoutubeDownloadListener() {
                @Override
                public void onDownloading(int progress) {
                    System.out.printf("\b\b\b\b%d%%", progress);
                }

                @Override
                public void onFinished(File file) {
                    System.out.printf("\nFinish video: %s",file);
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("Error: "+ throwable.getMessage());
                }
            });
            Thread.currentThread().join();
    }

    @FXML
    void setActionDownload() throws IOException, YoutubeException, InterruptedException {
        try{
        if(TextURL.getText() != null){
            StringBuilder wantId = new StringBuilder(TextURL.getText());
            videoId = wantId.substring(32,43);
            Download(videoId);
            System.out.println(videoId);
        }else{
            System.out.println("Не введён URL");
        }} catch (FileNotFoundException fileNotFoundException){
            System.out.println("Не найден файл");
        } catch (Exception e){
            System.out.println("Введите URL");
        }
    }
    @FXML
    void setActionSave() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedFile = directoryChooser.showDialog(null);
        if(selectedFile != null){
            fileAbsPath = selectedFile.getAbsolutePath();
        }else {
            System.out.println("Не найден файл");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Quality.setItems(langs);

    }
}

