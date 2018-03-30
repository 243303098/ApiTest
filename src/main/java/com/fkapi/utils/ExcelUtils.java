/**
 * 
 */
package com.fkapi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ExcelUtils {
	public static XSSFSheet ExcelSheet;
	public static XSSFWorkbook ExcelBook;
	public static XSSFRow Row;
	public static XSSFCell Cell;

	public static void setExcelFile(String Path, String SheetName)
			throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelBook = new XSSFWorkbook(ExcelFile);
		ExcelSheet = ExcelBook.getSheet(SheetName);
	}

	// 获取特定行的总列数
	public static Integer getAllColNum(String Path, String SheetName, int rowNum)
			throws IOException {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelBook = new XSSFWorkbook(ExcelFile);
		ExcelSheet = ExcelBook.getSheet(SheetName);
		return ExcelSheet.getRow(rowNum).getPhysicalNumberOfCells();
	}

	// 获取总行数
	public static Integer getAllRowNum(String Path, String SheetName)
			throws IOException {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelBook = new XSSFWorkbook(ExcelFile);
		ExcelSheet = ExcelBook.getSheet(SheetName);
		return ExcelSheet.getPhysicalNumberOfRows();
	}

	@SuppressWarnings("static-access")
	public static void setCellData(String Result, int RowNum, int ColNum,
			String Path, String SheetName) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelBook = new XSSFWorkbook(ExcelFile);
		ExcelSheet = ExcelBook.getSheet(SheetName);
		Row = ExcelSheet.getRow(RowNum);
		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
		if (Cell == null) {
			Cell = Row.createCell(ColNum);
			Cell.setCellValue(Result);
		} else {
			Cell.setCellValue(Result);
		}
		FileOutputStream fileOut = new FileOutputStream(Path);
		ExcelBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	/**
	 * 根据行列获取对应的值
	 * 
	 * @param RowNum
	 * @param CloNum
	 * @return
	 */
	public static String getCellDate(String path, String sheetName, int RowNum, int CloNum) {
		try {
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelBook = new XSSFWorkbook(ExcelFile);
		}catch (Exception e){
			e.printStackTrace();
		}
		ExcelSheet = ExcelBook.getSheet(sheetName);
		String cellData = "";
		DecimalFormat df = new DecimalFormat("#");
		Cell = ExcelSheet.getRow(RowNum).getCell(CloNum);
		if (Cell != null) {
			switch (Cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				cellData = Cell.getStringCellValue();
				break;

			case HSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(Cell)) {
					Date date = HSSFDateUtil.getJavaDate(Cell
							.getNumericCellValue());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellData = sdf.format(date).toString();
				} else {
					cellData = df.format(Cell.getNumericCellValue());
				}
			default:
				break;
			}
		}
		return cellData;
	}

	/**
	 * 将Excel中的值转成二维数组提供给dataprovider
	 * 
	 * @param path
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public static Object[][] excelToDateMap(String path, String sheetName)
			throws Exception {
		int isRunCol ;
		List<String> allList = new ArrayList<>();
		List<String> runList = new ArrayList<>();
		File file = new File(path);
		if (!file.exists()) {
			return null;
		} else {
			int allRowNum = ExcelUtils.getAllRowNum(path, sheetName);
			int allColNum = ExcelUtils.getAllColNum(path, sheetName, 0);
			isRunCol = ExcelUtils.getColNoByValue(path, sheetName, "isRun");
			// 将所有的isRun状态加入到list中
			for (int i = 0; i < allRowNum - 1; i++) {
				allList.add(ExcelUtils.getCellDate(path, sheetName, i+1, isRunCol));
			}
			// 排除其中的状态不为1的case
			Iterator<String> it = allList.iterator();
			while (it.hasNext()) {
				Object elemet = it.next();
				if(!elemet.equals("1")){
					it.remove();
				}else {
					runList.add((String) elemet);
				}
			}
			int mapNum = runList.size();
			Object dateMap[][] = new Object[mapNum][allColNum-2];
			int k = 0;
			for (int i = 0; i < allRowNum - 1; i++) {
				if (ExcelUtils.getCellDate(path, sheetName, i + 1, isRunCol).equals("1")) {
					for (int j = 0; j < allColNum-2; j++) {
						dateMap[k][j] = ExcelUtils.getCellDate(path, sheetName, i + 1, j);
					}
					k++;
				}
			}
			return dateMap;
		}
	}

	/**
	 * 根据单元格中的值获取对应的行数
	 * 
	 * @param Path
	 * @param SheetName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static int getRowNoByValue(String Path, String SheetName,
			String value)  {
		String cellData = "";
		DecimalFormat df = new DecimalFormat("#");
		int cell = 0;
		FileInputStream ExcelFile = null;
		try {
			ExcelFile = new FileInputStream(Path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ExcelBook = new XSSFWorkbook(ExcelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelSheet = ExcelBook.getSheet(SheetName);
		int allRowNum = ExcelSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < allRowNum; i++) {
			Cell = ExcelSheet.getRow(i).getCell(0);
			if (Cell != null) {
				switch (Cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					cellData = Cell.getStringCellValue();
					break;

				case HSSFCell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(Cell)) {
						Date date = HSSFDateUtil.getJavaDate(Cell
								.getNumericCellValue());
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						cellData = sdf.format(date).toString();
					} else {
						cellData = df.format(Cell.getNumericCellValue());
					}
				default:
					break;
				}
			}
			if (cellData.equals(value)) {
				cell = i;
				break;
			}
		}
		if (cell == 0) {
			Reporter.log("根据value：" + value + "未找到相应的用户数据，请确认用户信息");
		}
		return cell;
	}

	/**
	 * 根据单元格中的值获取对应的列数
	 * 
	 * @param Path
	 * @param SheetName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static int getColNoByValue(String Path, String SheetName,
			String value) throws Exception {
		String cellData = "";
		DecimalFormat df = new DecimalFormat("#");
		int col = 0;
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelBook = new XSSFWorkbook(ExcelFile);
		ExcelSheet = ExcelBook.getSheet(SheetName);
		int allColNum = ExcelSheet.getRow(0).getPhysicalNumberOfCells();
		for (int i = 0; i < allColNum; i++) {
			Cell = ExcelSheet.getRow(0).getCell(i);
			if (Cell != null) {
				switch (Cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					cellData = Cell.getStringCellValue();
					break;

				case HSSFCell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(Cell)) {
						Date date = HSSFDateUtil.getJavaDate(Cell
								.getNumericCellValue());
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						cellData = sdf.format(date).toString();
					} else {
						cellData = df.format(Cell.getNumericCellValue());
					}
				default:
					break;
				}
			}
			if (cellData.equals(value)) {
				col = i;
				break;
			}
		}
		if (col == 0) {
			Reporter.log("根据value：" + value + "未找到相应的用户数据，请确认用户信息");
		}
		ExcelBook.close();
		return col;
	}

	@Test
	public void test() throws Exception {
		//excelToDateMap(System.getProperty("user.dir") + "\\testcase.xlsx", "yc_case");
		getRowNoByValue(System.getProperty("user.dir") + "\\testcase.xlsx", "yc_case", "1");
	}

}