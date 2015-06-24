package com.wonders.ic.export;



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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

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
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	/*public void exportExcel2(String title, List<String[]> exportFields,
			Collection<T> dataset, OutputStream out, String pattern ) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
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
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
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

//		// 声明一个画图的顶级管理器
//		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//		// 定义注释的大小和位置,详见文档
//		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
//				0, 0, 0, (short) 4, 2, (short) 6, 5));
//		// 设置注释内容
//		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//		comment.setAuthor("leno");

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < exportFields.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(exportFields.get(i)[0]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		String fieldName = "";
		String getMethodName = "";
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			for(int j=0;j<exportFields.size();j++){
				T t = (T) it.next();
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				Field[] fields = t.getClass().getDeclaredFields();
				for (short i = 0; i < fields.length; i++) {
					Field field = fields[i];
					fieldName = field.getName();
					if(fieldName.equals(exportFields.get(j)[1])){
						HSSFCell cell = row.createCell(i);
						cell.setCellStyle(style2);
						getMethodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
						try {
							Class tCls = t.getClass();
							Method getMethod = tCls.getMethod(getMethodName,
									new Class[] {});
							Object value = getMethod.invoke(t, new Object[] {});
							// 判断值的类型后进行强制类型转换
							String textValue = null;
							 if (value instanceof Integer) {
								 int intValue = (Integer) value;
								 cell.setCellValue(intValue);
							 } else if (value instanceof Float) {
								 float fValue = (Float) value;
								 cell.setCellValue(fValue);
							 } else if (value instanceof Double) {
								 double dValue = (Double) value;
								 cell.setCellValue(dValue);
							 } else if (value instanceof Long) {
								 long longValue = (Long) value;
								 cell.setCellValue(longValue);
							 }
							if (value instanceof Date) {
								Date date = (Date) value;
								SimpleDateFormat sdf = new SimpleDateFormat(pattern);
								textValue = sdf.format(date);
							} 
							else {
								// 其它数据类型都当作字符串简单处理
								textValue = value.toString();
							}
							// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
							if (textValue != null) {
								//Pattern p = Pattern.compile("^//d+(//.//d+)?{1}quot;);
								//Matcher matcher = p.matcher(textValue);
	//							if (matcher.matches()) {
	//								// 是数字当作double处理
	//								cell.setCellValue(Double.parseDouble(textValue));
	//							} else {
									HSSFRichTextString richString = new HSSFRichTextString(
											textValue);
									HSSFFont font3 = workbook.createFont();
									font3.setColor(HSSFColor.BLUE.index);
									richString.applyFont(font3);
									cell.setCellValue(richString);
								//}
							}
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							// 清理资源
						}
					}
					
				}
			}
			

		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	
	
	
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
			List<Object[]> dataset, OutputStream out, short[] width) {
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
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	public void exportExcel(String headline,String title, String[] headers,
			List<Object[]> dataset, OutputStream out, short[] width) {
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
		if(row!=null){
			HSSFCell cell = row.createCell(0);
			HSSFFont f = workbook.createFont();
			f.setColor(HSSFColor.DARK_BLUE.index);
			f.setFontHeightInPoints((short) 24);
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			// 把字体应用到当前的样式
			HSSFCellStyle s = workbook.createCellStyle();
			s.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
			s.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			s.setFont(f);
			cell.setCellStyle(s);
			cell.setCellValue(headline);
		}
		sheet.addMergedRegion(new Region(0,(short)0,0,(short)(headers.length-1))); 
		
		row = sheet.createRow(1);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		for(int j=0;j<dataset.size();j++){
			row = sheet.createRow(j+2);
			
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
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	public void exportJgjExcel(String title,List<String[]> header,List<Object[]> dataset, OutputStream out, short[] width,List<int[]> merge){
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
		for(int i=0;i<header.size();i++){
			row = sheet.createRow(i);
			for(int m=0;m<header.get(i).length;m++){
				HSSFCell cell = row.createCell(m);
				cell.setCellStyle(style);
				String headValue = header.get(i)[m];
				//HSSFRichTextString text = new HSSFRichTextString(header.get(i)[m]);
				cell.setCellValue(headValue);
			}
			
		}
		
		if(merge!=null && merge.size()>0){
			for(int m=0;m<merge.size();m++){
				sheet.addMergedRegion(new CellRangeAddress(merge.get(m)[0],merge.get(m)[1],merge.get(m)[2],merge.get(m)[3]));		//合并行
			}
		}
		
		
		//sheet.addMergedRegion(new CellRangeAddress(0,0,9,10));
//		sheet.addMergedRegion(new CellRangeAddress(0,0,11,12));
		
		
		for(int j=0;j<dataset.size();j++){
			row = sheet.createRow(j+header.size());
			
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
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

