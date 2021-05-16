//package org.example;
//
//import com.github.kiulian.downloader.OnYoutubeDownloadListener;
//import com.github.kiulian.downloader.YoutubeDownloader;
//import com.github.kiulian.downloader.YoutubeException;
//import com.github.kiulian.downloader.model.VideoDetails;
//import com.github.kiulian.downloader.model.YoutubeVideo;
//import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//public class Youtube {
//    public static void main(String[] args) throws YoutubeException, IOException, InterruptedException {
//        String videoId = "-cZOdWjFwXw";
//        YoutubeDownloader downloader = new YoutubeDownloader();
//        YoutubeVideo video = downloader.getVideo(videoId);
//        VideoDetails details = video.details();
//
//
//        List<AudioVideoFormat> videoWithAudioFormat = video.videoWithAudioFormats();
//        File outputFile;
//        videoWithAudioFormat.forEach(it -> {
//            System.out.println("Качество " + it.videoQuality() + ":" + it.url());}
//        File outputFile = new File("output_file");
//        video.downloadAsync(videoWithAudioFormat.get(0),outputFile, new OnYoutubeDownloadListener() {
//
//            @Override
//            public void onDownloading(int progress) {
//                System.out.printf("\b\b\b\b\b%d%%",progress);
//            }
//
//            @Override
//            public void onFinished(File file) {
//                System.out.println("Finish Video");
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                System.out.println("Error" + throwable.getMessage());
//            }
//        });
//
//        Thread.currentThread().join();
//    }
//}
//
