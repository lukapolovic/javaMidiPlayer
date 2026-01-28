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

	public void buildGui() {
		JFrame frame = new JFrame("A very little music app");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		Box buttonBox = new Box(BoxLayout.Y_AXIS);

		JButton start = new JButton("Start");
		buttonBox.add(start);

		JButton stop = new JButton("Stop");
		buttonBox.add(stop);

		JButton upTempo = new JButton("Tempo Up");
		buttonBox.add(upTempo);

		JButton downTempo = new JButton("Tempo Down");
		buttonBox.add(downTempo);

		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (String instrumentName : instrumentNames) {
			JLabel instrumentLabel = new JLabel(instrumentName);
			instrumentLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
			nameBox.add(instrumentLabel);
		}

		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);

		frame.getContentPane().add(background);

		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);

		JPanel mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);

		checkboxList = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}

		frame.setBounds(50, 50, 300, 300);
		frame.pack();
		frame.setVisible(true);
	}

	private void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void changeTempo(float tempoMultiplier) {
		float tempoFactor = sequencer.getTempoFactor();
		sequencer.setTempoFactor(tempoFactor * tempoMultiplier);
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
