package gergert.task2.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private final List<TextComponent> components = new ArrayList<>();
    private final TextType type;

    public TextComposite(TextType type) {
        this.type = type;
    }

    public TextType getType() {
        return type;
    }

    public void add(TextComponent component) {
        components.add(component);
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public String toString(){
        StringBuilder text = new StringBuilder();



        return text.toString();
    }

    @Override
    public int count() {
        int counter = 0;

        for (TextComponent component : components) {
            counter += component.count();
        }

        return counter;
    }
}
