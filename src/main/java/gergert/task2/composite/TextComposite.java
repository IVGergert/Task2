package gergert.task2.composite;

import java.net.Proxy;
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

        for (TextComponent component : components) {
            text.append(component.toString());

            switch (type){
                case TEXT -> {
                    if (component instanceof TextComposite &&
                            ((TextComposite) component).getType() == TextType.PARAGRAPH){
                        text.append("\n\n");
                    }
                }
                case PARAGRAPH -> {
                    if (component instanceof TextComposite &&
                            ((TextComposite) component).getType() == TextType.SENTENCE){
                        text.append("\n\n");
                    }
                }
                case SENTENCE -> {
                    if (component instanceof TextComposite &&
                            ((TextComposite) component).getType() == TextType.LEXEME){
                        text.append(" ");
                    }
                }
            }
        }

        if (text.length() > 0 && text.charAt(text.length() - 1) == ' '){
            text.deleteCharAt(text.length() - 1);
        }

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
