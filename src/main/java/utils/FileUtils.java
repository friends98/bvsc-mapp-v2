package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.entity.ShareHolder;

@Stateless
public class FileUtils {
	private static final Logger logger = Logger.getLogger(FileUtils.class);
	
	public  List<ShareHolder> readExcelFile(InputStream ins){
		//File fileExcel = new File(ins);
		List<ShareHolder> listData =new ArrayList<>();
		try {
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(ins);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			int rowNumber = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				ShareHolder shareholder = new ShareHolder();
				int cellIndex=0;
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch(cellIndex) {
					case 0:
						shareholder.setFullname(cell.getStringCellValue());
						break;
					case 1:
						shareholder.setIdentityCard(String.valueOf((int)cell.getNumericCellValue()));
						break;
					case 2:
						shareholder.setEmail(cell.getStringCellValue());
						break;
					case 3:
						shareholder.setAddress(cell.getStringCellValue());
						break;
					case 4:
						shareholder.setPhoneNumber(String.valueOf((int)cell.getNumericCellValue()));
						break;
					case 5:
						shareholder.setNationality(cell.getStringCellValue());
						break;
					case 6:
						shareholder.setUsername(cell.getStringCellValue());
						break;
					case 7:
						double numbershare = cell.getNumericCellValue();
						shareholder.setNumberShares((int)numbershare);
						break;
					case 8:
						double numbershareAuth = cell.getNumericCellValue();
						shareholder.setNumberSharesAuth((int)numbershareAuth);
						break;
					case 9:
						double role = cell.getNumericCellValue();
						shareholder.setRole((int)role);
						break;
					case 10:
						shareholder.setShareHolderCode(cell.getStringCellValue());
						break;
					}
					cellIndex++;
				}
				listData.add(shareholder);
				rowNumber++;
			}
			//fis.close();
			workbook.close();
		} catch (Exception e) {
			logger.error("ERROR READ FILE EXCEL : "+e.getMessage());
		}
		return listData;
	}
	
	public String uploadImage(String filePath) {
		try {
			byte[] fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath));
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			//logger.info("encode: " + encodedString);
			return encodedString;
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	

}