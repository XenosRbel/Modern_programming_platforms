package course.work.services;

import course.work.models.Project;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import course.work.models.TaskStatus;

public class PdfExporter {
    private List<Project> projectList;
    private String before;
    private String after;

    public PdfExporter(List<Project> projectList, String before, String after) {
        this.projectList = projectList;
        this.before = before;
        this.after = after;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Project Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Tasks count", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Not Started", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("In Progress", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Submitted", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Closed", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Hours spent", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) throws IOException {
        for (Project project : projectList) {
            String path = "src/main";

            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            BaseFont base = BaseFont.createFont(absolutePath + "/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font font = new Font(base, 14);
            Phrase name = new Phrase(project.getName(), font);
            Phrase desc = new Phrase(project.getDescription(), font);
            table.addCell(name);
            table.addCell(desc);
            table.addCell(String.valueOf(project.getTasks().size()));
            table.addCell(String.valueOf(project.getTasks().stream().filter(
                    task -> task.getStatus() == TaskStatus.NotStarted
            ).count()));
            table.addCell(String.valueOf(project.getTasks().stream().filter(
                    task -> task.getStatus() == TaskStatus.InProgress
            ).count()));
            table.addCell(String.valueOf(project.getTasks().stream().filter(
                    task -> task.getStatus() == TaskStatus.Submitted
            ).count()));
            table.addCell(String.valueOf(project.getTasks().stream().filter(
                    task -> task.getStatus() == TaskStatus.Closed
            ).count()));
            final float[] worktime = {0};
            project.getTasks().stream().forEach((a) ->
                    {
                        worktime[0] += a.getWorkTime();
                    }
            );
            table.addCell(String.valueOf(worktime[0]));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        String path = "src/main";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        BaseFont base = BaseFont.createFont(absolutePath + "/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font font = new Font(base, 14);
        font.setSize(18);
        font.setColor(Color.BLUE);
        Paragraph p = new Paragraph("Projects report [" + this.before + " - " + this.after + "]", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3f, 3.5f, 2f, 2f, 2f, 2f, 2f, 2f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
