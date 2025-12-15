package gergert.com.task2.composite;

public class CharacterLeaf implements TextComponent {
    private char value;
    private TextType type;

    public CharacterLeaf(char value, TextType type) {
        this.value = value;
        this.type = type;
    }

    public CharacterLeaf(char c) {
        this.value = c;
        this.type = TextType.SYMBOL;
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
