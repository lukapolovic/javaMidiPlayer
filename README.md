# MiniMiniMusicApp 🎵  
**Networked MIDI Beat Sequencer (Java)**

A multi-client **client–server music sequencer** built in Java.  
The application allows users to create rhythmic patterns, play them using MIDI, and share them live with other connected clients via a central server.

This project demonstrates **core Java engineering skills** including GUI development, networking, multithreading, object serialization, and real-time audio playback.

---

## 🚀 Project Overview

- Java Swing–based GUI for creating 16×16 beat patterns
- Real-time MIDI playback using Java’s `javax.sound.midi` API
- Client–server architecture using TCP sockets
- Concurrent handling of multiple clients via thread pools
- Object serialization for network communication and persistence

Multiple clients can connect simultaneously, exchange beat patterns, and instantly play shared sequences.

---

## 🧱 Architecture

**Client (MiniMiniMusicApp)**
- Handles GUI, MIDI sequencing, and user interaction
- Sends beat data and messages to the server
- Receives and plays beat patterns from other users

**Server (MusicServer)**
- Accepts multiple client connections
- Receives serialized objects from clients
- Broadcasts messages and beat sequences to all connected clients

---

## 🛠️ Technologies & Concepts

### Core Java
- Object-oriented design
- Inner classes
- Collections (`ArrayList`, `HashMap`, `Vector`)

### GUI Development
- Java Swing (`JFrame`, `JButton`, `JCheckBox`, `JList`, `JTextArea`)
- Event-driven programming
- UI state management

### Multithreading & Concurrency
- `Runnable` and `ExecutorService`
- Thread pools for scalable client handling
- Background threads to prevent UI blocking

### Networking
- TCP sockets (`Socket`, `ServerSocket`)
- Client–server communication model
- Concurrent multi-client support

### Serialization & I/O
- `ObjectInputStream` / `ObjectOutputStream`
- Transmission of complex objects (`boolean[]`, `String`)
- Local persistence using serialized files

### Audio & MIDI
- MIDI sequencing with `Sequencer`, `Sequence`, and `Track`
- Programmatic creation of MIDI events
- Real-time tempo manipulation

---

## 💡 Key Engineering Challenges Solved

- Prevented UI freezing by moving network I/O to background threads
- Coordinated real-time audio playback with user-driven input
- Safely managed multiple client connections on the server
- Designed a simple but effective object-based communication protocol
- Mapped UI state (checkbox grid) directly to MIDI note events

---

## ▶️ How to Run

### Start the Server and one or more Clients
```bash
javac MusicServer.java
java MusicServer

javac MiniMiniMusicApp.java
java MiniMiniMusicApp <username>