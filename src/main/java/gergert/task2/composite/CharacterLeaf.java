package gergert.task2.composite;

public class CharacterLeaf implements  TextComponent{
    private final char value;
    private final TextType type;

    public CharacterLeaf(char value, TextType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int count() {
        return 1;
    }
}
