package Clases;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author shar
 */

//GENERAR PDF 

public class generarpdf {
   //CONFIGURAR LA FUENTE
    private Font fuenteBold = new Font(Font.FontFamily.COURIER, 3, Font.BOLD);
    private Font fuenteNormal = new Font(Font.FontFamily.COURIER, 3, Font.NORMAL);
    private Font fuenteItalic = new Font(Font.FontFamily.COURIER, 3, Font.ITALIC);

    public void generarPDF(String cabecera, String info, String piedepagina, String rutaimagen, String salida) {

        try {

            Document document = new Document(PageSize.A7, 10, 10, 10, 10);
            PdfWriter.getInstance(document, new FileOutputStream(salida));
            document.open();
            document.add(getCabecera(cabecera));
            Image imagen = Image.getInstance(rutaimagen);
            imagen.scaleAbsolute(30, 30);
            imagen.setAlignment(Element.ALIGN_CENTER);
            document.add(imagen);
            document.add(getInfo(info));
            document.add(getInfo(" "));
            document.add(getInfo(" "));
            document.add(getInfo(" "));
            document.add(getPiedepagina(piedepagina));
            document.close();

        } catch (Exception e) {

        }

    }

    private Paragraph getCabecera(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteBold);
        p.add(c);
        return p;
    }

    private Paragraph getInfo(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_LEFT);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }

    private Paragraph getPiedepagina(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_RIGHT);
        c.append(texto);
        c.setFont(fuenteItalic);
        p.add(c);
        return p;
    }
}
