package com.syshotdev;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import net.fabricmc.api.ClientModInitializer;

public class MegalovaniaSoundsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
  public static MegalovaniaSoundsClient self = new MegalovaniaSoundsClient();

  // String: note name, Float: note pitch (based on real life, you should scale from 0 to 1)
  private static final HashMap<String, Float> noteMap = generateNoteHashmap();
  
  private static final String megalovaniaNotesString = """
    D4 D4 D5 A4 G#4 G4 F4 D4 F4 G4 C4 C4 D5 A4 G#4 G4 F4 D4 F4 G4 B4 B4 D5 A4 G#4 
    G4 F4 D4 F4 G4 A#4 A#4 D5 A4 G#4 G4 F4 D4 F4 G4 F4 F4 F4 F4 D4 D4 D4 F4 F4 F4 G4 G#4 G4 F4 D4 F4 G4 F4 F4 F4 G4 G#4 A4 
    C4 A4 D4 D4 D4 A4 D4 C4 A4 A4 A4 A4 G4 G4 G4 A4 A4 A4 A4 G4 A4 C4 A4 G4 D4 A4 G4 F4 C4 G4 F4 E4 D4 D4 D4 D4 F4 C4 F4 D4 F4 G4 G#4 G4 F4""";
  /*
  private static final String megalovaniaNotesString = """
    F4 F4 F4 F4 F4 F6 F6 F6 F6 F6
    """;
    */
  private static final List<String> megalovaniaNotesArray = List.of(megalovaniaNotesString.strip().split("\\s+")); // Get from splitting notes string


  private int currentNote = 0;
  private static int megalovaniaNotesSize = megalovaniaNotesArray.size();
  private String nextNote() {
    if(currentNote >= megalovaniaNotesSize - 1) {
      currentNote = 0;
    }
    String note = megalovaniaNotesArray.get(currentNote);
    currentNote += 1;
    return note;
  }

  private static final float maxNoteValue = megalovaniaNotesArray.stream()
                 .map(noteMap::get)
                 .filter(Objects::nonNull)
                 .max(Float::compareTo)
                 .orElseThrow(() -> new NoSuchElementException("No matching keys found in map"));
  private static final float minNoteValue = megalovaniaNotesArray.stream()
                 .map(noteMap::get)
                 .filter(Objects::nonNull)
                 .min(Float::compareTo)
                 .orElseThrow(() -> new NoSuchElementException("No matching keys found in map"));

  public float getNextNotePitch() {
    String noteString = nextNote();
    float frequency = noteMap.get(noteString);
    // Get semitone instead of frequency
    float semitone = Math.round(12 * Math.log(frequency / 440.0) / Math.log(2));
    // We scale noteValue based on minNoteValue to maxNoteValue, to get a range from 0.1 to 1
    double pitch = Math.pow(2, (semitone/12)); //0.5 + ((noteValue - minNoteValue) / (maxNoteValue - minNoteValue)) * (1.5 - 0.5);
    return (float)pitch;
  }

  // Generate the correlations from notes to frequency values
  private static HashMap<String, Float> generateNoteHashmap() {
    HashMap<String, Float> notesMap = new HashMap<>();
    notesMap.put("C0",  16.35f);
    notesMap.put("C#0", 17.32f);
    notesMap.put("D0",  18.35f );
    notesMap.put("D#0", 19.45f );
    notesMap.put("E0",  20.6f );
    notesMap.put("F0",  21.83f );
    notesMap.put("F#0", 23.12f );
    notesMap.put("G0",  24.5f );
    notesMap.put("G#0", 25.96f );
    notesMap.put("A0",  27.5f );
    notesMap.put("A#0", 29.14f );
    notesMap.put("B0", 30.87f );

    notesMap.put("C1", 32.7f );
    notesMap.put("C#1", 34.65f );
    notesMap.put("D1", 36.71f );
    notesMap.put("D#1", 38.89f );
    notesMap.put("E1", 41.2f );
    notesMap.put("F1", 43.65f );
    notesMap.put("F#1", 46.25f );
    notesMap.put("G1", 49f );
    notesMap.put("G#1", 51.91f );
    notesMap.put("A1", 55f );
    notesMap.put("A#1", 58.27f );
    notesMap.put("B1", 61.74f );

    notesMap.put("C2", 65.41f );
    notesMap.put("C#2", 69.3f );
    notesMap.put("D2", 73.42f );
    notesMap.put("D#2", 77.78f );
    notesMap.put("E2", 82.41f );
    notesMap.put("F2", 87.31f );
    notesMap.put("F#2", 92.5f );
    notesMap.put("G2", 98f );
    notesMap.put("G#2", 103.8f );
    notesMap.put("A2", 110f );
    notesMap.put("A#2", 116.5f );
    notesMap.put("B2", 123.5f );

    notesMap.put("C3", 130.8f );
    notesMap.put("C#3", 138.6f );
    notesMap.put("D3", 146.8f );
    notesMap.put("D#3", 155.6f );
    notesMap.put("E3", 164.8f );
    notesMap.put("F3", 174.6f );
    notesMap.put("F#3", 185.0f );
    notesMap.put("G3", 196.0f );
    notesMap.put("G#3", 207.7f );
    notesMap.put("A3", 220.0f );
    notesMap.put("A#3", 233.1f );
    notesMap.put("B3", 246.9f );

    notesMap.put("C4", 261.6f );
    notesMap.put("C#4", 277.2f );
    notesMap.put("D4", 293.7f );
    notesMap.put("D#4", 311.1f );
    notesMap.put("E4", 329.6f );
    notesMap.put("F4", 349.2f );
    notesMap.put("F#4", 370f );
    notesMap.put("G4", 392f );
    notesMap.put("G#4", 415.3f );
    notesMap.put("A4", 440.0f );
    notesMap.put("A#4", 466.2f );
    notesMap.put("B4", 493.9f );

    notesMap.put("C5", 523.3f );
    notesMap.put("C#5", 554.4f );
    notesMap.put("D5", 587.3f );
    notesMap.put("D#5", 622.3f );
    notesMap.put("E5", 659.3f );
    notesMap.put("F5", 698.5f );
    notesMap.put("F#5", 740.0f );
    notesMap.put("G5", 784.0f );
    notesMap.put("G#5", 830.6f );
    notesMap.put("A5", 880.0f );
    notesMap.put("A#5", 932.3f );
    notesMap.put("B5", 987.8f );

    notesMap.put("C6", 1047.0f );
    notesMap.put("C#6", 1109.0f );
    notesMap.put("D6", 1175.0f );
    notesMap.put("D#6", 1245.0f );
    notesMap.put("E6", 1319.0f );
    notesMap.put("F6", 1397.0f );
    notesMap.put("F#6", 1480.0f );
    notesMap.put("G6", 1568.0f );
    notesMap.put("G#6", 1661.0f );
    notesMap.put("A6", 1760.0f );
    notesMap.put("A#6", 1865.0f );
    notesMap.put("B6", 1976.0f );

    notesMap.put("C7", 2093.0f );
    notesMap.put("C#7", 2217.0f );
    notesMap.put("D7", 2349.0f );
    notesMap.put("D#7", 2489.0f );
    notesMap.put("E7", 2637.0f );
    notesMap.put("F7", 2794.0f );
    notesMap.put("F#7", 2960.0f );
    notesMap.put("G7", 3136.0f );
    notesMap.put("G#7", 3322.0f );
    notesMap.put("A7", 3520.0f );
    notesMap.put("A#7", 3729.0f );
    notesMap.put("B7", 3951.0f );

    notesMap.put("C8", 4186.0f );
    notesMap.put("C#8", 4435.0f );
    notesMap.put("D8", 4699.0f );
    notesMap.put("D#8", 4978.0f );
    notesMap.put("E8", 5274.0f );
    notesMap.put("F8", 5588.0f );
    notesMap.put("F#8", 5920.0f );
    notesMap.put("G8", 6272.0f );
    notesMap.put("G#8", 6645.0f );
    notesMap.put("A8", 7040.0f );
    notesMap.put("A#8", 7459.0f );
    notesMap.put("B8", 7902.0f );
    return notesMap;
  }
}
