import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.sound.midi.ShortMessage.*;

public class MiniMiniMusicApp {
	private ArrayList<JCheckBox> checkboxList;
	private Sequencer sequencer;
	private Sequence sequence;
	private Track track;

	String[] instrumentNames = { "Bass drum", "Closed Hi-Hat", "Open-Hi,Hat", "Acoustic Snare",
			"Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas",
			"Whistle", "Low Conga", "Cowbell", "Vibraslap",
			"Low-mid Tom", "High Agogo", "Open Hi Conga" };
	int[] instruments = { 35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63 };

	public static void main(String[] args) {
		new MiniMiniMusicApp().buildGui();
	}

	private void makeTracks(int[] list) {
		for (int i = 0; i < 16; i++) {
			int key = list[i];

			if (key != 0) {
				track.add(makeEvent(NOTE_ON, 9, key, 100, i));
				track.add(makeEvent(NOTE_OFF, 9, key, 100, i + 1));
			}
		}
	}

	public static MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage message = new ShortMessage();
			message.setMessage(command, channel, data1, data2);
			event = new MidiEvent(message, tick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}
}
