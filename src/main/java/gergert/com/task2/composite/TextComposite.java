package gergert.com.task2.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private final List<TextComponent> components = new ArrayList<>();
    private final TextType type;

    private static final String FIRST_PARAGRAPH_DELIMITER = "\t";
    private static final String NEXT_PARAGRAPH_DELIMITER = "\n\n\t";
    private static final String SPACE_DELIMITER = " ";

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

        boolean isFirst = true;

        for (TextComponent component : components) {
            switch (type){
                case TEXT -> {
                    if (component instanceof TextComposite &&
                            ((TextComposite) component).getType() == TextType.PARAGRAPH){

                        if (isFirst) {
                            text.append(FIRST_PARAGRAPH_DELIMITER);
                        } else {
                            text.append(NEXT_PARAGRAPH_DELIMITER);
                        }
                    }
                }
                case PARAGRAPH, SENTENCE -> {
                    if (!isFirst) {
                        text.append(SPACE_DELIMITER);
                    }
                }
            }

            text.append(component.toString());
            isFirst = false;
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
