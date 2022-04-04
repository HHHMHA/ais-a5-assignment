package org.thekiddos.a5.gui;

import javafx.event.ActionEvent;
import org.thekiddos.a5.sound.A5SoundRecorder;
import org.thekiddos.a5.sound.ApplicationProperties;
import org.thekiddos.a5.sound.WaveDataUtil;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;

public class Controller {
    private final A5SoundRecorder soundRecorder = new A5SoundRecorder();
    private boolean isRecording = false;

    public void initialize() {
        AudioFormat format = buildAudioFormatInstance();
        soundRecorder.build( format );
    }

    public void toggleRecording( ActionEvent actionEvent ) {
        if ( !isRecording ) {
            soundRecorder.start();
            isRecording = true;
            return;
        }

        soundRecorder.stop();
        WaveDataUtil wd = new WaveDataUtil();
        wd.saveToFile( "SoundClip", AudioFileFormat.Type.WAVE, soundRecorder.getAudioInputStream() );
        isRecording = false;
    }

    public static AudioFormat buildAudioFormatInstance() {
        ApplicationProperties aConstants = new ApplicationProperties();
        AudioFormat.Encoding encoding = aConstants.ENCODING;
        float rate = aConstants.RATE;
        int channels = aConstants.CHANNELS;
        int sampleSize = aConstants.SAMPLE_SIZE;
        boolean bigEndian = aConstants.BIG_ENDIAN;

        return new AudioFormat( encoding, rate, sampleSize, channels, ( sampleSize / 8 ) * channels, rate, bigEndian );
    }
}
