package ua.triple.game.grid;

import ua.triple.game.elements.Element;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Event implements MouseListener {
    private Element element;
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];

    public Event( Element el){
        this.element = el;
    }
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Test mouseClicked");

    }

    public void mousePressed(MouseEvent e) {
        System.out.println( "Get element" + cells[0][0].getElement().toString() );
        System.out.println( e.getX() + " " + e.getY() );
        //cells[0][0].setElement( this.element );


        //System.out.println( "Get element" + cells[e.getX()][e.getY()].getElement().toString() );
        System.out.println("Test mousePressed");
       // cells[1][1].setElement( new Element(ElementTypesCollection.getTypeById("system", "inventory")) );
    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("Test mouseReleased");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Test mouseEntered");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Test mouseExited");
    }

}
