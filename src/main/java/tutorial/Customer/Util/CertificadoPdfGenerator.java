package tutorial.Customer.Util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import jakarta.servlet.http.HttpServletResponse;
import tutorial.Customer.Entity.Certificado;

import java.awt.Color;

public class CertificadoPdfGenerator {

    public void generatePdf(Certificado certificado, HttpServletResponse response) throws Exception {

        Document document = new Document(PageSize.LETTER.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Fuentes, tamaños y colores
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26, Color.BLACK);
        Font fuenteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.DARK_GRAY);
        Font fuenteTexto = FontFactory.getFont(FontFactory.TIMES, 14, Color.BLACK);
        Font fuenteData = FontFactory.getFont(FontFactory.COURIER, 12, Color.BLACK);
        Font fuenteEtiqueta = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
        Font fuenteFinal = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Color.black);

        // Título principal del PDF
        Paragraph tituloPrincipal = new Paragraph("TATA CONSULTING", fuenteTitulo);
        tituloPrincipal.setAlignment(Element.ALIGN_CENTER);
        tituloPrincipal.setSpacingAfter(20);
        document.add(tituloPrincipal);

        // Subtítulo
        Paragraph subtitulo = new Paragraph(
                String.format("Se certifica que \"%s\" ha completado el siguiente curso de \"%s\".",
                        certificado.getParticipante(), certificado.getCapacitacion()),
                fuenteSubtitulo);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        subtitulo.setSpacingAfter(30);
        document.add(subtitulo);

        // Tabla para mostrar datos del certificado
        PdfPTable tablaCertificado = new PdfPTable(2);
        tablaCertificado.setWidths(new float[]{1f, 2f});
        tablaCertificado.setSpacingBefore(20);
        tablaCertificado.setWidthPercentage(80);

        // Diseño de las celdas
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("DNI:", fuenteEtiqueta));
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase(certificado.getDni(), fuenteData));
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase("Participante:", fuenteEtiqueta));
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase(certificado.getParticipante(), fuenteData));
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase("Capacitación:", fuenteEtiqueta));
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase(certificado.getCapacitacion(), fuenteData));
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha Inicio:", fuenteEtiqueta));
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase(certificado.getFechaInicio(), fuenteData));
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha Fin:", fuenteEtiqueta));
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase(certificado.getFechaFin(), fuenteData));
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase("Código:", fuenteEtiqueta));
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase(certificado.getCodigoCertificado(), fuenteData));
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha Emisión:", fuenteEtiqueta));
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        cell = new PdfPCell(new Phrase(certificado.getFechaEmision(), fuenteData));
        cell.setPadding(10);
        tablaCertificado.addCell(cell);

        // Agregar la tabla al documento
        document.add(tablaCertificado);

        // Separador de línea
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(Color.BLACK);
        lineSeparator.setLineWidth(1);
        lineSeparator.setAlignment(Element.ALIGN_CENTER);
        lineSeparator.setPercentage(80);
        document.add(new Chunk(lineSeparator));

        // Texto final con el año y el país
        Paragraph finalText = new Paragraph("2024 - PERÚ", fuenteFinal);
        finalText.setAlignment(Element.ALIGN_CENTER);
        finalText.setSpacingBefore(30);
        document.add(finalText);

        // Cerrar el documento
        document.close();
    }
}
