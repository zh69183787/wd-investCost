package com.wonders.ic.fund.export;



import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档 转载时请保留以下信息，注明出处！
 * 
 * @author leno
 * @version v1.0
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcel<T> {

	/**
	 * 数据导出excel
	 * @author sunjiawei
	 * @param title:生成工作表的标题
	 * @param headers:每个字段的名称
	 * @param dataset:数据集
	 * @param out:输出流	 
	 * @param width:每个字段所在单元格的宽度，如果用默认宽度，令width=null;	 
	 */
	public void exportExcel(String title, String[] headers,
			List<Object[]> dataset, String[] lineNames, OutputStream out, short[] width) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		for(int j=0;j<dataset.size();j++){
			row = sheet.createRow(j+1);
			
			for (short i = 0; i < dataset.get(j).length; i++) {
				HSSFCell cell = row.createCell(i);				
				cell.setCellStyle(style2);
				HSSFRichTextString richString = new HSSFRichTextString(
				String.valueOf( dataset.get(j)[i]).replace("null", ""));
//							HSSFFont font3 = workbook.createFont();
//							font3.setColor(HSSFColor.BLACK.index);
//							richString.applyFont(font3);
				cell.setCellValue(richString);
			}
		}
		
		if(width!=null){
			for(short i=0;i<width.length;i++){
				sheet.setColumnWidth(i,width[i]);
			}
		}
		
		sheet.addValidationData(setDataValidation(sheet,lineNames,1, dataset.size(), 5, 5));

		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
    public static DataValidation setDataValidation(Sheet sheet,String[] textList, int firstRow, int endRow, int firstCol, int endCol) {    
        
        DataValidationHelper helper = sheet.getDataValidationHelper();  
        // 加载下拉列表内容    
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);    
        // DVConstraint constraint = new DVConstraint();    
        constraint.setExplicitListValues(textList);    
        // 设置数据有效性加载在哪个单元格上。    
        // 四个参数分别是：起始行、终止行、起始列、终止列    
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);    
        
        // 数据有效性对象    
        DataValidation data_validation = helper.createValidation(constraint, regions);  
        //DataValidation data_validation = new DataValidation(regions, constraint);    
        
        return data_validation;    
    }   
	
	
}

