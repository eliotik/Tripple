package ua.triple.game.grid;

import ua.triple.game.elements.Element;
import ua.triple.game.elements.ElementTypesCollection;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Event implements MouseListener {
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];
    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Test mouseClicked");

    }

    public void mousePressed(MouseEvent e) {
        System.out.println( e.getX() + " " + e.getY() );
        cells[0][0].setElement(new Element(ElementTypesCollection.getRandomByType("base")));

        //System.out.println(cells[20][20].getElement().toString());
        System.out.println("Test mousePressed");
       // cells[1][1].setElement( new Element(ElementTypesCollection.getTypeById("system", "inventory")) );
    }


    public void mouseReleased(MouseEvent e) {
        //System.out.println("Test mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Test mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Test mouseExited");
    }

}
