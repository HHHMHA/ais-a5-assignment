package org.thekiddos.a5;

// Source http://www.java2s.com/example/java-utility-method/bitset/increment-bitset-bits-int-size-9fd84.html
//package com.java2s;
//License from project: Open Source License

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.BitSet;

public class Utils {
    public static boolean increment( BitSet bits, int size ) {
        int i = size - 1;
        while ( i >= 0 && bits.get( i ) ) {
            bits.set( i--, false );/*from w w w  . j a  v a  2s  .c o  m*/
        }
        if ( i < 0 ) {
            return false;
        }
        bits.set( i, true );
        return true;
    }

    // source: https://stackoverflow.com/a/6934403
    public static byte[] readAudioFileData(final String filePath) {
        byte[] data = null;
        try {
            final ByteArrayOutputStream baout = new ByteArrayOutputStream();
            final File file = new File(filePath);
            final AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(file);

            byte[] buffer = new byte[4096];
            int c;
            while ((c = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
                baout.write(buffer, 0, c);
            }
            audioInputStream.close();
            baout.close();
            data = baout.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
