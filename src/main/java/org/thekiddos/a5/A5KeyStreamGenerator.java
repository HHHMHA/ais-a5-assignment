package org.thekiddos.a5;

import java.util.BitSet;

// TODO: raise exceptions for improper use
public class A5KeyStreamGenerator extends CompositeLFSR {
    private BitSet frameCounter;
    private BitSet sessionKey;
    private static final int INITIAL_CLOCKING_CYCLES = 100;
    private static final int KEY_STREAM_LENGTH = 228; // 28.5 bytes so we need to pad bytes or something

    @Override
    public void initialize( BitSet sessionKey, BitSet frameCounter ) {
        this.sessionKey = sessionKey;
        this.frameCounter = frameCounter;
        registers.clear();
        LFSR lfsr1 = new LFSR( 19, 8, new int[]{ 13, 16, 17, 18 } );
        LFSR lfsr2 = new LFSR( 22, 10, new int[]{ 20, 21 } );
        LFSR lfsr3 = new LFSR( 23, 10, new int[]{ 7, 20, 21, 22 } );
        registers.add( lfsr1 );
        registers.add( lfsr2 );
        registers.add( lfsr3 );
        registers.forEach( lfsr -> lfsr.initialize( sessionKey, frameCounter ) );
    }

    public BitSet getNextKeyStream() {
        for ( int cycle = 1; cycle <= INITIAL_CLOCKING_CYCLES; ++cycle )
            registers.forEach( LFSR::clock );

        BitSet result = new BitSet( KEY_STREAM_LENGTH );
        for ( int cycle = 1; cycle <= KEY_STREAM_LENGTH; ++cycle ) {
            boolean outputBit = registers.stream().map( LFSR::clock ).reduce( false, ( first, second ) -> first ^ second );
            result.set( cycle - 1, outputBit );
        }

        reInitializeRegisters();
        return result;
    }

    private void reInitializeRegisters() {
        incrementFrameCounter();
        registers.forEach( lfsr -> lfsr.initialize( sessionKey, frameCounter ) );
    }

    private void incrementFrameCounter() {
        Utils.increment( frameCounter, FRAME_COUNTER_LENGTH );
    }
}