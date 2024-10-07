package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ParsingFilesInsideZipTest {

    private ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void verifyFilesInsideZipArchiveTest() throws Exception {
        ZipFile zipFile = new ZipFile(new File(Objects.requireNonNull(classLoader
                .getResource("zip_Test.zip")).toURI()));

        //CSV
        ZipEntry csvEntry = zipFile.getEntry("CSV_Test.csv");
        try (InputStream stream = zipFile.getInputStream(csvEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(4)
                    .contains(
                            new String[]{"Username", "Id", "First name", "Last name"},
                            new String[]{"dariaS", "9232", "Daria", "Test"},
                            new String[]{"ivan9", "6732", "Ivan", "Ivanov"},
                            new String[]{"natasha12", "8912", "Natasha", "Ivanova"}
                    );
        }

        //XLS
        ZipEntry xlsEntry = zipFile.getEntry("XLSX_Test.xlsx");
        try (InputStream stream = zipFile.getInputStream(xlsEntry)) {

            XLS parsed = new XLS(stream);
           assertThat(parsed.excel.getSheetAt(0).getRow(2).getCell(3).getStringCellValue())
                   .isEqualTo("Женский");
            assertThat(parsed.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue())
                   .isEqualTo("Исакова");
        }

        //PDF
        ZipEntry pdfEntry = zipFile.getEntry("PDF_Test.pdf");
        try (InputStream stream = zipFile.getInputStream(pdfEntry)) {
            PDF pdf = new PDF(stream);
            assertThat(pdf.text).contains("Об объекте");
        }
    }
}
