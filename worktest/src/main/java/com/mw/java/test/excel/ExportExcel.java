package com.mw.java.test.excel;

import org.apache.poi.hssf.usermodel.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExportExcel {
	public static void main(String[] args){
		String content = read("E:\\excel\\src\\main\\resources\\test.txt");
		String[] arr = content.split(" ");
		for(String str:arr){
			System.out.println(str);
		}
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		//声明一个单子并命名
		HSSFSheet sheet = wb.createSheet("data");
		//给单子名称一个长度
		sheet.setDefaultColumnWidth((short)15);
		// 生成一个样式  
		HSSFCellStyle style = wb.createCellStyle();
		//创建第一行（也可以称为表头）
		HSSFRow row = sheet.createRow(0);
		//样式字体居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//给表头第一行一次创建单元格
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("学生编号"); 
		cell.setCellStyle(style);
		cell = row.createCell( (short) 1);  
        cell.setCellValue("学生姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("学生性别");  
        cell.setCellStyle(style); 

       //添加一些数据，这里先写死，大家可以换成自己的集合数据
       List<Student> list = new ArrayList<Student>();
       list.add(new Student(111,"张三","男"));
       list.add(new Student(111,"李四","男"));
       list.add(new Student(111,"王","女"));

       //向单元格里填充数据
       for (short i = 0; i < list.size(); i++) {
	    row = sheet.createRow(i + 1);
	    row.createCell(0).setCellValue(list.get(i).getId());
	    row.createCell(1).setCellValue(list.get(i).getName());
	    row.createCell(2).setCellValue(list.get(i).getSex());
        }
        
       try {
			//默认导出到E盘下
			FileOutputStream out = new FileOutputStream("E://学生表.xls");
			wb.write(out);
			out.close();
			JOptionPane.showMessageDialog(null, "导出成功!");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		}
	}
	
	public static String read(String filePath)
	{
		// 读取txt内容为字符串
		StringBuffer txtContent = new StringBuffer();
		// 每次读取的byte数
		byte[] b = new byte[8 * 1024];
		InputStream in = null;
		try
		{
		// 文件输入流
		in = new FileInputStream(filePath);
		while (in.read(b) != -1)
		{
		// 字符串拼接
		txtContent.append(new String(b));
		}
		// 关闭流
		in.close();
		}
		catch (FileNotFoundException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		catch (IOException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally
		{
		if (in != null)
		{
		try
		{
		in.close();
		}
		catch (IOException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		}
		return txtContent.toString();
		} 
}

