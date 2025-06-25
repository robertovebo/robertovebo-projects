package controladores;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import modelos.Venta;
import vistas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ControladorPrincipal implements ActionListener {
    private VistaPrincipal vistaPrincipal;
    private ArrayList<Venta> ventas;

    public ControladorPrincipal(VistaPrincipal vistaPrincipal){
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setListeners(this);

        ventas = new ArrayList<Venta>();
        ventas = Venta.obtenerRegistroVentas();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "Ventas":
                abrirVentas();
                break;

            case "Inventario":
                abrirInventario();
                break;

            case "Clientes":
                abrirClientes();
                break;

            case "Informes":
                abrirInformes();
                break;

            case "Ajustes":
                abrirAjustes();
                break;
        }
    }

    public void abrirVentas(){
        VistaVentas vistaVentas = new VistaVentas();
        vistaPrincipal.agregarPanel(vistaVentas);

        ControladorVentas controladorVentas = new ControladorVentas(vistaVentas);
    }

    public void abrirInventario(){
        VistaInventario vistaInventario = new VistaInventario();
        vistaPrincipal.agregarPanel(vistaInventario);

        ControladorInventario controladorInventario = new ControladorInventario(vistaInventario);
    }

    public void abrirClientes(){
        VistaClientes vistaClientes = new VistaClientes();
        vistaPrincipal.agregarPanel(vistaClientes);

        ControladorClientes controladorClientes = new ControladorClientes(vistaClientes);
    }

    public void abrirInformes(){
        generarPDF();
    }

    public void abrirAjustes(){
        VistaAjustes vistaAjustes = new VistaAjustes();
        vistaPrincipal.agregarPanel(vistaAjustes);

        ControladorAjustes controladorAjustes = new ControladorAjustes(vistaAjustes);
    }

    public void generarPDF() {
        JFileChooser fileChooserPDF = vistaPrincipal.getFileChooserPDF();

        int respuesta = fileChooserPDF.showDialog(vistaPrincipal, "Generar PDF");

        if (respuesta == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Se canceló la exportación");
            return;
        }

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(fileChooserPDF.getSelectedFile()));
             Document doc = new Document(pdfDoc, PageSize.LETTER.rotate());) {
            InputStream is = getClass().getClassLoader().getResourceAsStream("img/logoQuetzal.png");
            System.out.println(is);
            if (is != null) {
                ImageData data = ImageDataFactory.create(is.readAllBytes());
                Image img = new Image(data).scaleAbsolute(84, 20);

                float altoPagina = PageSize.LETTER.rotate().getHeight();
                float margen = 40;
                img.setFixedPosition(margen, altoPagina - margen - 50);

                doc.add(img);
            }

            doc.add(new Paragraph("Registro de ventas").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER));

            doc.add(new Paragraph("").setMarginTop(30));

            float[] anchoColumnas = {1, 3, 2, 3, 2};
            Table tabla = new Table(UnitValue.createPercentArray(anchoColumnas)).useAllAvailableWidth();

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);


            Cell cell = new Cell(1, 5)
                    .add(new Paragraph("Ventas del día"))
                    .setFont(font)
                    .setFontSize(14)
                    .setFontColor(DeviceGray.WHITE)
                    .setBackgroundColor(new DeviceRgb(45, 111, 164))
                    .setTextAlignment(TextAlignment.CENTER);
            tabla.addHeaderCell(cell);


            for (int i = 0; i < 2; i++) {
                Cell[] headerFooter = new Cell[]{
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("# Ventas")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Nombre del producto")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Unidades vendidas")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Precio por producto")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Ingresos totales")),
                };

                for (Cell celda : headerFooter) {
                    if (i == 0) {
                        tabla.addHeaderCell(celda);
                    }
                }
            }


            int indice = 1;

            for (Venta v : ventas) {
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(indice))));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(v.getNombre())));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(String.valueOf(v.getUnidades()))));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(String.valueOf(v.getPrecio()))));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(String.valueOf(v.getUnidades() * v.getPrecio()))));
                indice++;
            }

            doc.add(tabla);

            doc.close();
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(fileChooserPDF.getSelectedFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(vistaPrincipal, "No se pudo abrir el archivo");
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vistaPrincipal, "No se realizó la exportación del PDF");
        }
    }
}
