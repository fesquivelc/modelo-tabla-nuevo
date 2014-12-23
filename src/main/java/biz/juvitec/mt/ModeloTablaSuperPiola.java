/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.mt;

import biz.juvitec.pruebatabla.FichaSalud;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;

/**
 *
 * @author fesquivelc
 */
public class ModeloTablaSuperPiola extends AbstractTableModel implements ObservableListListener {

    private List<FichaSalud> datos;
    private String[] columnas = {"DNI", "Nombre y apellidos", "Edad", "Talla", "Peso", "Tiene alergias", "Índice de masa corporal"};

    public ModeloTablaSuperPiola(List<FichaSalud> datos) {
        this.datos = datos;

        if (datos instanceof ObservableList) {
            ((ObservableList) datos).addObservableListListener(this);
        }
    }
    private static final Logger LOG = Logger.getLogger(ModeloTablaSuperPiola.class.getName());

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        FichaSalud seleccionada = this.datos.get(rowIndex);

        switch (columnIndex) {
            case 2:
                int edad = Integer.parseInt(aValue.toString());
                seleccionada.setEdad(edad);
                break;
            case 3:
                double talla = Double.parseDouble(aValue.toString());
                seleccionada.setTalla(talla);
                break;
            case 4:
                double peso = Double.parseDouble(aValue.toString());
                seleccionada.setPeso(peso);
                break;
            case 5:
                boolean alergia = Boolean.parseBoolean(aValue.toString());
                seleccionada.setTieneAlergias(alergia);
                break;
        }

        this.fireTableCellUpdated(rowIndex, columnIndex);
        this.fireTableCellUpdated(rowIndex, 6); //PARA QUE SE ACTUALICE EL INDICE DE MASA CORPORAL
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 2 && columnIndex <= 5;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public int getRowCount() {
        return this.datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 5) {
            return Boolean.class;
        } else if ( columnIndex == 3 || columnIndex == 4 || columnIndex == 6) {
            return Double.class;
        } else if (columnIndex == 2) {
            return Integer.class;
        } else {
            return Object.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FichaSalud seleccionada = this.datos.get(rowIndex);
        try {
            switch (columnIndex) {
                case 0:
                    return seleccionada.getPersona().getDni();
                case 1:
                    return seleccionada.getPersona().getNombre() + " " + seleccionada.getPersona().getApellidos();
                case 2:
                    if (seleccionada.getEdad() == 0) {
                        return null;
                    } else {
                        return seleccionada.getEdad();
                    }
                case 3:
                    if (seleccionada.getTalla() == 0) {
                        return null;
                    } else {
                        return seleccionada.getTalla();
                    }
                case 4:
                    if (seleccionada.getPeso() == 0) {
                        return null;
                    } else {
                        return seleccionada.getPeso();
                    }
                case 5:
                    return seleccionada.isTieneAlergias();
                case 6:
                    double imc = 0;
                    if(seleccionada.getTalla() != 0){
                        imc = seleccionada.getPeso() / Math.pow(seleccionada.getTalla(), 2);
                    }                    
                    if (imc == 0) {
                        return null;
                    } else {
                        seleccionada.setIndiceMasaCorporal(imc);
                        return imc;
                    }
                default:
                    return null;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void listElementsAdded(ObservableList ol, int i, int i1) {
        LOG.info("AGREGADO: " + i + " " + i1);
        this.fireTableRowsInserted(i1, i1);
    }

    @Override
    public void listElementsRemoved(ObservableList ol, int i, List list) {
        LOG.info(i + "");
        this.fireTableRowsDeleted(i, i);
    }

    @Override
    public void listElementReplaced(ObservableList ol, int i, Object o) {
        this.fireTableRowsUpdated(i, i);
    }

    @Override
    public void listElementPropertyChanged(ObservableList ol, int i) {
        this.fireTableRowsUpdated(i, i);
    }

}
