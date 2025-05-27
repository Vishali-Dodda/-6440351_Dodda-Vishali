interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() {
        System.out.println("Playing the guitar.");
    }
}

class Piano implements Playable {
    public void play() {
        System.out.println("Playing the piano.");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        Guitar guitar = new Guitar();
        Piano piano = new Piano();

        guitar.play();
        piano.play();
    }
}
