package com.norra.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CsvFileReader {
	//String path = AppMsgReaderUtil.getTextValue(Constants.VEHICLE_CSV_FILE_PATH);
	String path = "https://assets.bounce.bike/pool-assets-stage/2019+Nov+-+List+of+vehicle+details.xlsx";

	@PostConstruct
	public void read() {
		try {
			log.info("Reading vehicle data from csv!!!!-->"+path);
			HashMap<Integer, List<String>> vehicleHashMap = new HashMap<>();
			URL url = new URL(path);
			InputStream inputStream = url.openStream();
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {
				// next row iteration
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				//BrandModel brandModel = new BrandModel();
				boolean isValid = false;
				while (cellIterator.hasNext()) {
					// column wise iteration of current row
					Cell currentCell = cellIterator.next();
					int columnIndex = currentCell.getColumnIndex();
					String value = null;
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						value = currentCell.getStringCellValue().trim();
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						value = new DecimalFormat("#").format(currentCell.getNumericCellValue()).trim();
					}
					if (currentRow.getRowNum() != 0 && value != null) {
						isValid = true;
						switch (columnIndex) {
						case 0:
							//brandModel.setType(value);
							break;
						case 1:
							//brandModel.setBrand(value);
							break;
						case 2:
							//brandModel.setModel(value);
							break;
						case 3:
							//brandModel.setCategory(value);
							break;
						case 4:
							//brandModel.setSeats(Integer.parseInt(value));
							break;
						}
					}
				}
				if (isValid) {
					try {
						//vehicleService.saveBrandModelFromCsv(brandModel);
					} catch (Exception e) {
						log.info("Failed to save data to db " + e.getMessage());
					}
				}
			}
		} catch (FileNotFoundException e) {
			log.info("CSV file not found " + e.getMessage());
		} catch (IOException e) {
			log.info("Failed to read csv file " + e.getMessage());
		}
	}
}
