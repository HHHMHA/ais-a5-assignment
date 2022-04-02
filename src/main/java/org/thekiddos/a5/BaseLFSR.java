package org.thekiddos.a5;

import java.util.BitSet;

public interface BaseLFSR {
    void initialize(BitSet sessionKey, BitSet frameCounter);
    boolean clock();
    // TODO: make dynamic
    int SESSION_KEY_LENGTH = 64;
    int FRAME_COUNTER_LENGTH = 22;
}
