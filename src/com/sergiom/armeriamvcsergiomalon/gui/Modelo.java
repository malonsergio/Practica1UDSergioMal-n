package com.sergiom.armeriamvcsergiomalon.gui;

import com.sergiom.armeriamvcsergiomalon.base.ArmaADistancia;
import com.sergiom.armeriamvcsergiomalon.base.ArmaCuerpoACuerpo;
import com.sergiom.armeriamvcsergiomalon.base.Armas;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {
    ArrayList<Armas> listaArmas;

    public Modelo() {
        this.listaArmas = new ArrayList<>();
    }

    public ArrayList<Armas> getListaArmas() {
        return listaArmas;
    }

    public Armas buscarArma(String nombre) {
        for (Armas a : listaArmas) {
            if (a.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                return a;
            }
        }
        return null;
    }

    public boolean altaArmaCaC(String nombre, String modelo, String lugarDeFabricacion, String nombreFabricante,
                               String materiales, double precioEnEscudos,
                               LocalDate fechaFabricacion, String descripcion,
                               boolean unFilo, double longitudFilo, int estiloDeUso) {
        ArmaCuerpoACuerpo armaCuerpoACuerpo = new ArmaCuerpoACuerpo(nombre, modelo, lugarDeFabricacion, nombreFabricante,
                materiales, precioEnEscudos, fechaFabricacion, descripcion, unFilo, longitudFilo, estiloDeUso);
        if (buscarArma(nombre) == null) {
            listaArmas.add(armaCuerpoACuerpo);
            return true;
        }
        return false;
    }

    public boolean altaArmaADistancia(String nombre, String modelo, String lugarDeFabricacion, String nombreFabricante,
                                      String materiales, double precioEnEscudos,
                                      LocalDate fechaFabricacion, String descripcion, int municion, double alcanceEfectivo, boolean usaPolvora) {
        ArmaADistancia armaADistancia = new ArmaADistancia(nombre, modelo, lugarDeFabricacion, nombreFabricante,
                materiales, precioEnEscudos, fechaFabricacion, descripcion, municion, alcanceEfectivo, usaPolvora);

        if (buscarArma(nombre) == null) {
            listaArmas.add(armaADistancia);
            return true;
        }
        return false;
    }


    public boolean exportarXML(File fichero) {
        Document dom;
        Element hijo = null;
        Element datoHijo = null;
        Element datoHijo1 = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
            Element padre = dom.createElement("Armas");
            dom.appendChild(padre);

            for (Armas a : listaArmas) {
                if (a instanceof ArmaCuerpoACuerpo) {
                    hijo = dom.createElement("ArmaCuerpoACuerpo");
                    padre.appendChild(hijo);
                } else if (a instanceof ArmaADistancia) {

                    hijo = dom.createElement("ArmaADistancia");
                    padre.appendChild(hijo);
                }

                datoHijo = dom.createElement("nombre");
                datoHijo.appendChild(dom.createTextNode(a.getNombre()));
                hijo.appendChild(datoHijo);

                datoHijo = dom.createElement("modelo");
                datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                hijo.appendChild(datoHijo);

                datoHijo = dom.createElement("lugarFabricacion");
                datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                hijo.appendChild(datoHijo);

                datoHijo = dom.createElement("nombreFabricante");
                datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                hijo.appendChild(datoHijo);

                datoHijo = dom.createElement("material");
                datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                hijo.appendChild(datoHijo);

                datoHijo = dom.createElement("precioEnEscudos");
                datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                hijo.appendChild(datoHijo);

                datoHijo = dom.createElement("fechaFabricacion");
                datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                hijo.appendChild(datoHijo);

                datoHijo = dom.createElement("descripcion");
                datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                hijo.appendChild(datoHijo);

                if (a instanceof ArmaCuerpoACuerpo) {
                    datoHijo = dom.createElement("numFilos");
                    datoHijo.appendChild(dom.createTextNode((((ArmaCuerpoACuerpo) a).isUnFilo()) ? "Un solo filo" : "Dos filos"));
                    hijo.appendChild(datoHijo);

                    datoHijo = dom.createElement("longitudFilo");
                    datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                    hijo.appendChild(datoHijo);

                    datoHijo = dom.createElement("estiloDeUso");
                    datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                    hijo.appendChild(datoHijo);


                } else if (a instanceof ArmaADistancia) {

                    datoHijo = dom.createElement("municion");
                    datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                    hijo.appendChild(datoHijo);

                    datoHijo = dom.createElement("alcance");
                    datoHijo.appendChild(dom.createTextNode(String.valueOf(a.getModelo())));
                    hijo.appendChild(datoHijo);

                    datoHijo = dom.createElement("usaPolvora");
                    datoHijo.appendChild(dom.createTextNode((((ArmaADistancia) a).isUsaPolvora()) ? "Arma de fuego" : "Arma tradicional"));
                    hijo.appendChild(datoHijo);
                }
            }
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // send DOM to file
            tr.transform(new DOMSource(dom),
                    new StreamResult(new FileOutputStream("productos.xml")));
            return true;


        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void importarXML(File fichero) {
        ArmaCuerpoACuerpo armaCuerpoACuerpo = null;
        ArmaADistancia armaADistancia = null;
        DocumentBuilderFactory dbm = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbm.newDocumentBuilder();
            Document document = db.parse(fichero);
            NodeList listElementos = document.getElementsByTagName("*");
            for (int i = 0; i < listElementos.getLength(); i++) {
                Element nodoArma = (Element) listElementos.item(i);
                if (nodoArma.getTagName().equals("ArmaCuerpoACuerpo")) {

                    armaCuerpoACuerpo = new ArmaCuerpoACuerpo();
                    armaCuerpoACuerpo.setNombre(nodoArma.getChildNodes().item(0).getTextContent());
                    armaCuerpoACuerpo.setModelo(nodoArma.getChildNodes().item(1).getTextContent());
                    armaCuerpoACuerpo.setLugarDeFabricacion(nodoArma.getChildNodes().item(2).getTextContent());
                    armaCuerpoACuerpo.setNombreFabricante(nodoArma.getChildNodes().item(3).getTextContent());
                    armaCuerpoACuerpo.setMateriales(nodoArma.getChildNodes().item(4).getTextContent());
                    armaCuerpoACuerpo.setPrecioEnEscudos(Double.parseDouble(nodoArma.getChildNodes().item(5).getTextContent()));
                    armaCuerpoACuerpo.setFechaFabricacion(LocalDate.parse(nodoArma.getChildNodes().item(6).getTextContent()));
                    armaCuerpoACuerpo.setDescripcion(nodoArma.getChildNodes().item(7).getTextContent());
                    armaCuerpoACuerpo.setUnFilo(nodoArma.getChildNodes().item(8).getTextContent());
                    armaCuerpoACuerpo.setLongitudFilo(Integer.parseInt(nodoArma.getChildNodes().item(9).getTextContent()));
                    armaCuerpoACuerpo.setEstiloDeUso(Integer.parseInt(nodoArma.getChildNodes().item(10).getTextContent()));

                    listaArmas.add(armaCuerpoACuerpo);
                } else if (nodoArma.getTagName().equals("ArmaADistancia")) {
                    armaADistancia = new ArmaADistancia();

                    armaADistancia.setNombre(nodoArma.getChildNodes().item(0).getTextContent());
                    armaADistancia.setModelo(nodoArma.getChildNodes().item(1).getTextContent());
                    armaADistancia.setLugarDeFabricacion(nodoArma.getChildNodes().item(2).getTextContent());
                    armaADistancia.setNombreFabricante(nodoArma.getChildNodes().item(3).getTextContent());
                    armaADistancia.setMateriales(nodoArma.getChildNodes().item(4).getTextContent());
                    armaADistancia.setPrecioEnEscudos(Double.parseDouble(nodoArma.getChildNodes().item(5).getTextContent()));
                    armaADistancia.setFechaFabricacion(LocalDate.parse(nodoArma.getChildNodes().item(6).getTextContent()));
                    armaADistancia.setDescripcion(nodoArma.getChildNodes().item(7).getTextContent());
                    armaADistancia.setMunicion(Integer.parseInt(nodoArma.getChildNodes().item(8).getTextContent()));
                    armaADistancia.setAlcanceEfectivo(Double.parseDouble(nodoArma.getChildNodes().item(9).getTextContent()));
                    armaADistancia.setUsaPolvora(nodoArma.getChildNodes().item(10).getTextContent());

                    listaArmas.add(armaADistancia);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

