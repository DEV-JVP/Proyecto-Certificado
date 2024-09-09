package tutorial.Customer.Util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import tutorial.Customer.Entity.Certificado;

import java.awt.Color;
import java.util.List;
import java.util.Map;

@Component("/index")
public class ListarCertificadoPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
									HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<Certificado> listadoCertificado = (List<Certificado>) model.get("certificados");

		// Fuentes
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLUE);
		Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLUE);
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER, 10, Color.BLACK);

		// Configuración del documento
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 30, 20);
		document.open();

		// Crear tabla para el título del PDF
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celdaTitulo = new PdfPCell(new Phrase("LISTADO GENERAL DE CERTIFICADOS", fuenteTitulo));
		celdaTitulo.setBorder(0);
		celdaTitulo.setBackgroundColor(new Color(40, 190, 138));
		celdaTitulo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celdaTitulo.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celdaTitulo.setPadding(30);
		tablaTitulo.addCell(celdaTitulo);
		tablaTitulo.setSpacingAfter(30);

		// Crear tabla para mostrar listado de certificados
		PdfPTable tablaCertificados = new PdfPTable(8); // Cambié a 8 columnas según los datos
		tablaCertificados.setWidths(new float[]{0.8f, 2f, 2f, 1.5f, 3.5f, 1.5f, 1.5f, 2f}); // Ajusta las proporciones de las columnas

		// Encabezados de las columnas
		addTableHeader(tablaCertificados, fuenteTituloColumnas);

		// Datos de los certificados
		addCertificateRows(tablaCertificados, listadoCertificado, fuenteDataCeldas);

		// Agregar tablas al documento
		document.add(tablaTitulo);
		document.add(tablaCertificados);
	}

	private void addTableHeader(PdfPTable table, Font font) {
		String[] headers = {"ID", "DNI", "PARTICIPANTE", "CAPACITACION", "Inicio", "FIN", "CÓDIGO", "EMISION"};
		for (String header : headers) {
			PdfPCell cell = new PdfPCell(new Phrase(header, font));
			cell.setBackgroundColor(Color.lightGray);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(8);
			table.addCell(cell);
		}
	}

	private void addCertificateRows(PdfPTable table, List<Certificado> certificados, Font font) {
		for (Certificado certificado : certificados) {
			table.addCell(new PdfPCell(new Phrase(certificado.getIdCertificado().toString(), font)));
			table.addCell(new PdfPCell(new Phrase(certificado.getDni(), font)));
			table.addCell(new PdfPCell(new Phrase(certificado.getParticipante(), font)));
			table.addCell(new PdfPCell(new Phrase(certificado.getCapacitacion(), font)));
			table.addCell(new PdfPCell(new Phrase(certificado.getFechaInicio(), font)));
			table.addCell(new PdfPCell(new Phrase(certificado.getFechaFin(), font)));
			table.addCell(new PdfPCell(new Phrase(certificado.getCodigoCertificado(), font)));
			table.addCell(new PdfPCell(new Phrase(certificado.getFechaEmision(), font)));
		}
	}
}
