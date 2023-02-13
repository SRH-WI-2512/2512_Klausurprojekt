package view;

import model.Buch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;

public class AlleBücherDesAutorsView extends View {

    private JList<Buch> bücherList;

    public AlleBücherDesAutorsView() {
        super("Liste aller Autoren");
        getRootPane().setBorder( new EmptyBorder(5,5,5,5) );

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        bücherList = new JList<>();
        bücherList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        scrollPane.setViewportView( bücherList );

        setVisible(true);
    }

    public void setBücherListMouseListener(MouseAdapter adapter) {
        bücherList.addMouseListener(adapter);
    }

    public void setBücherListDefaultModel(DefaultListModel<Buch> defaultModel) {
        bücherList.setModel( defaultModel );
    }

    public Buch getSelectedBuch() {
        return bücherList.getSelectedValue();
    }
}
